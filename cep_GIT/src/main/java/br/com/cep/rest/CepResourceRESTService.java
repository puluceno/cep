package br.com.cep.rest;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import br.com.cep.model.CepLogIndex;
import br.com.cep.model.Webservicecep;
import br.com.cep2.model.AddressByCep;

@RequestScoped
@Path("")
public class CepResourceRESTService {
	@Inject
	private EntityManager em;
	@Inject
	Logger log;

	/**
	 * Method responsible for querying and the databases and return the address
	 * to the provided cep.
	 * 
	 * @author pulu - 09/09/2013
	 * @param cep
	 * @param url
	 * @return address
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Produces("application/json;charset=UTF-8")
	public Webservicecep findAddressByCEP(@QueryParam("cep") String cep, @QueryParam("url") URL url) {
		try {

			if (cep == null)
				throw new Exception("cep null");
			cep = cep.replaceAll("[^\\d]", "");

			if (cep.length() != 8)
				throw new Exception("invalid cep");

			String prefix = cep.substring(0, 2);
			String parte1 = cep.substring(0, 5);
			String parte2 = cep.substring(5);
			cep = parte1 + "-" + parte2;

			String state = (String) em.createNamedQuery(CepLogIndex.FIND_BY_UF)
					.setParameter("prefix", prefix + Webservicecep.SQL_WILDCARD).getSingleResult();
			state = state.substring(0, 1).toUpperCase() + state.substring(1).toLowerCase();

			StringBuilder query2 = new StringBuilder();
			query2.append("SELECT address FROM ");
			query2.append(state);
			query2.append(" address WHERE address.cep = :cep");

			List<Webservicecep> addressList = em.createQuery(query2.toString()).setParameter("cep", cep)
					.getResultList();

			Webservicecep completeAddress = null;
			if (!addressList.isEmpty()) {
				completeAddress = new Webservicecep(Webservicecep.SUCCESS_CODE, state, addressList.get(0).getCidade(),
						addressList.get(0).getBairro(), addressList.get(0).getTpLogradouro(), addressList.get(0)
						.getLogradouro());
			} else {
				List<AddressByCep> address2 = em.createNamedQuery(AddressByCep.FIND_CEP2_BY_CEP)
						.setParameter("cep", cep).getResultList();
				if (!address2.isEmpty()) {
					completeAddress = new Webservicecep(Webservicecep.SUCCESS_CODE, state, address2.get(0).getCidade(),
							address2.get(0).getBairro(), "", address2.get(0).getRua());
				} else {
					// Try webService@Correios
					completeAddress = findAddressByCepAtCorreios(url.toString().replace("***", cep));
				}
			}

			return completeAddress;

		} catch (Exception e) {
			return new Webservicecep(Webservicecep.ERROR_CODE);
		}
	}

	/**
	 * 
	 * Method responsible for querying and parsing to correios cep locator
	 * 
	 * @author pulu - 09/09/2013
	 */
	private Webservicecep findAddressByCepAtCorreios(String url) throws IOException {
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		log.log(Level.INFO, "Querying to correios WS...");
		try {
			httpClient.executeMethod(postMethod);

			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				Document doc = Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);
				Elements elements = doc.select("td:not([colspan]):not(:has(*))");

				return new Webservicecep(Webservicecep.SUCCESS_CODE, elements.get(3).ownText(), elements.get(2)
						.ownText(), elements.get(1).ownText(), "", elements.get(0).ownText());

			} else
				return new Webservicecep(Webservicecep.ERROR_CODE);
		} catch (Exception e) {
			log.log(Level.WARNING, "Failed to parse html data. Possible reason: invalid cep.");
			return new Webservicecep(Webservicecep.ERROR_CODE);
		}
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("/neighborhood")
	@Produces("application/json;charset=UTF-8")
	public Map<String, List<String>> registerNeighborhoodsToNewCity(@QueryParam("state") String state,
			@QueryParam("cityName") String cityName) {
		StringBuilder query = new StringBuilder();
		query.append("(SELECT CAST(endereco.bairro AS char) AS b ");
		query.append("FROM ");
		query.append("CEP.");
		query.append(state.toLowerCase());
		query.append(" endereco ");
		query.append("WHERE endereco.cidade = :cityName) ");
		query.append("UNION ");
		query.append("(SELECT CAST(bairro.nome AS char) AS b ");
		query.append("FROM CEP2.bairros bairro ");
		query.append("JOIN CEP2.cidades cidade ON cidade.id = bairro.cidade ");
		query.append("WHERE cidade.nome = :cityName) ");
		query.append("ORDER BY b ASC ");

		List<String> neighborhoodsList = em.createNativeQuery(query.toString()).setParameter("cityName", cityName)
				.getResultList();

		Map<String, List<String>> neighborhoods = new HashMap<String, List<String>>();
		neighborhoods.put("neighborhoods", neighborhoodsList);

		return neighborhoods;
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("/cities")
	@Produces("application/json;charset=UTF-8")
	public List<String> findCitiesByState(@QueryParam("uf") String uf, @QueryParam("exclude") List<String> cidades) {
		try {
			String query = null;
			if (cidades.isEmpty()) {
				query = "SELECT c.nome FROM br.com.cep2.model.Cidades c WHERE c.uf = :uf";
				return em.createQuery(query).setParameter("uf", uf).getResultList();
			} else {
				query = "SELECT c.nome FROM br.com.cep2.model.Cidades c WHERE c.uf = :uf AND nome NOT IN (:cidades)";
				return em.createQuery(query).setParameter("uf", uf).setParameter("cidades", cidades).getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
