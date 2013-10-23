package br.com.cep2.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author pulu - 09/09/2013
 * 
 */
@Entity
@Table(name = "Endereco_by_cep", schema = "CEP2")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = AddressByCep.FIND_CEP2_BY_CEP, query = "SELECT e FROM AddressByCep e WHERE e.cep = :cep") })
public class AddressByCep implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_CEP2_BY_CEP = "FIND_CEP2_BY_CEP";

	@Id
	@Basic(optional = false)
	@Column(name = "cep")
	private String cep;
	@Basic(optional = false)
	@Column(name = "rua")
	private String rua;
	@Basic(optional = false)
	@Column(name = "bairro")
	private String bairro;
	@Basic(optional = false)
	@Column(name = "cidade")
	private String cidade;

	/**
	 * 
	 * Default Constructor pulu - 09/09/2013
	 */
	public AddressByCep() {
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressByCep other = (AddressByCep) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddressByCep [cep=" + cep + ", rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade + "]";
	}

}
