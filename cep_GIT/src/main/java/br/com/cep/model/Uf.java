/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cep.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author pulu
 */
@Entity
@Table(name = "uf", schema = "CEP")
@XmlRootElement
public class Uf implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "UF", nullable = false, length = 2)
	private String uf;
	@Basic(optional = false)
	@Column(name = "Nome", nullable = false, length = 72)
	private String nome;
	@Basic(optional = false)
	@Column(name = "Cep1", nullable = false, length = 5)
	private String cep1;
	@Basic(optional = false)
	@Column(name = "Cep2", nullable = false, length = 5)
	private String cep2;

	public Uf() {
	}

	public Uf(String uf) {
		this.uf = uf;
	}

	public Uf(String uf, String nome, String cep1, String cep2) {
		this.uf = uf;
		this.nome = nome;
		this.cep1 = cep1;
		this.cep2 = cep2;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep1() {
		return cep1;
	}

	public void setCep1(String cep1) {
		this.cep1 = cep1;
	}

	public String getCep2() {
		return cep2;
	}

	public void setCep2(String cep2) {
		this.cep2 = cep2;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (uf != null ? uf.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Uf))
			return false;
		Uf other = (Uf) object;
		if ((uf == null && other.uf != null) || (uf != null && !uf.equals(other.uf)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "geraclasses.Uf[ uf=" + uf + " ]";
	}

}
