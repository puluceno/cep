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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author pulu
 */
@Entity
@Table(name = "go", schema = "CEP")
@XmlRootElement

public class Go extends Webservicecep implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "cidade", length = 50)
	private String cidade;
	@Column(name = "logradouro", length = 70)
	private String logradouro;
	@Column(name = "bairro", length = 72)
	private String bairro;
	@Basic(optional = false)
	@Column(name = "cep", nullable = false, length = 9)
	private String cep;
	@Column(name = "tp_logradouro", length = 20)
	private String tpLogradouro;

	public Go() {
	}

	public Go(Long id) {
		this.id = id;
	}

	public Go(Long id, String cep) {
		this.id = id;
		this.cep = cep;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getCidade() {
		return cidade;
	}

	@Override
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public String getLogradouro() {
		return logradouro;
	}

	@Override
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Override
	public String getBairro() {
		return bairro;
	}

	@Override
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String getTpLogradouro() {
		return tpLogradouro;
	}

	@Override
	public void setTpLogradouro(String tpLogradouro) {
		this.tpLogradouro = tpLogradouro;
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
		if (!(object instanceof Go))
			return false;
		Go other = (Go) object;
		if ((id == null && other.id != null) || (id != null && !id.equals(other.id)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "geraclasses.Go[ id=" + id + " ]";
	}

}
