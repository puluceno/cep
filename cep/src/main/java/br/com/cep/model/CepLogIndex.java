/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author pulu
 */
@Entity
@Table(name = "cep_log_index", schema = "CEP")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = CepLogIndex.FIND_BY_UF, query = "SELECT DISTINCT c.uf FROM CepLogIndex c WHERE c.cep5 LIKE :prefix") })
public class CepLogIndex implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_UF = "FIND_BY_UF";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Basic(optional = false)
	@Column(name = "cep5", nullable = false, length = 5)
	private String cep5;
	@Basic(optional = false)
	@Column(name = "uf", nullable = false, length = 3)
	private String uf;

	public CepLogIndex() {
	}

	public CepLogIndex(Integer id) {
		this.id = id;
	}

	public CepLogIndex(Integer id, String cep5, String uf) {
		this.id = id;
		this.cep5 = cep5;
		this.uf = uf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep5() {
		return cep5;
	}

	public void setCep5(String cep5) {
		this.cep5 = cep5;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof CepLogIndex))
			return false;
		CepLogIndex other = (CepLogIndex) object;
		if ((id == null && other.id != null) || (id != null && !id.equals(other.id)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "geraclasses.CepLogIndex[ id=" + id + " ]";
	}

}
