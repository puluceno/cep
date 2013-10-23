package br.com.cep.rest;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    
    @SuppressWarnings("unchecked")
    @GET
    @Produces("text/xml")
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
	    
	    String state = (String) em.createNamedQuery(CepLogIndex.FIND_BY_UF).setParameter("prefix", prefix + "%")
		    .getSingleResult();
	    state = state.substring(0, 1).toUpperCase() + state.substring(1).toLowerCase();
	    
	    String query2 = "SELECT address FROM " + state + " address WHERE address.cep = :cep";
	    List<Webservicecep> addressList = new ArrayList<Webservicecep>();
	    
	    addressList = em.createQuery(query2).setParameter("cep", cep).getResultList();
	    
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
}
