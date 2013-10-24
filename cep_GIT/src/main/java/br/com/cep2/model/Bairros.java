package br.com.cep2.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author pulu - 09/09/2013
 * 
 */
@Entity
@Table(name = "bairros", schema = "CEP2")
public class Bairros implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "uf")
	private String uf;
	@Basic(optional = false)
	@Column(name = "cidade")
	private String cidade;
	@Basic(optional = false)
	@Column(name = "nome")
	private String nome;

	/**
	 * 
	 * Default Constructor
	 * 
	 * @author pulu - 09/09/2013
	 */
	public Bairros() {
	}

	/**
	 * 
	 * Parametrized Constructor
	 * 
	 * @author pulu - 09/09/2013
	 */
	public Bairros(Integer id, String uf, String cidade, String nome) {
		this.id = id;
		this.uf = uf;
		this.cidade = cidade;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		Bairros other = (Bairros) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bairros [id=" + id + ", uf=" + uf + ", cidade=" + cidade + ", nome=" + nome + "]";
	}

}
