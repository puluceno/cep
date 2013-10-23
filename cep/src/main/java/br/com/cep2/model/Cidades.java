package br.com.cep2.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author pulu
 */
@Entity
@Table(name = "cidades", schema = "CEP2")
public class Cidades implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "nome")
	private String nome;
	@Basic(optional = false)
	@Column(name = "uf")
	private String uf;
	@Basic(optional = false)
	@Column(name = "cep2")
	private String cep2;
	@Basic(optional = false)
	@Column(name = "estado_cod")
	private int estadoCod;
	@Basic(optional = false)
	@Column(name = "cep")
	private String cep;

	/**
	 * 
	 * Default Constructor
	 * 
	 * @author pulu - 09/09/2013
	 */
	public Cidades() {
	}

	/**
	 * 
	 * Parametrized Constructor
	 * 
	 * @author pulu - 09/09/2013
	 */
	public Cidades(Integer id, String nome, String uf, String cep2, int estadoCod, String cep) {
		this.id = id;
		this.nome = nome;
		this.uf = uf;
		this.cep2 = cep2;
		this.estadoCod = estadoCod;
		this.cep = cep;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep2() {
		return cep2;
	}

	public void setCep2(String cep2) {
		this.cep2 = cep2;
	}

	public int getEstadoCod() {
		return estadoCod;
	}

	public void setEstadoCod(int estadoCod) {
		this.estadoCod = estadoCod;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cep2 == null) ? 0 : cep2.hashCode());
		result = prime * result + estadoCod;
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
		Cidades other = (Cidades) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cep2 == null) {
			if (other.cep2 != null)
				return false;
		} else if (!cep2.equals(other.cep2))
			return false;
		if (estadoCod != other.estadoCod)
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
		return "Cidades [id=" + id + ", nome=" + nome + ", uf=" + uf + ", cep2=" + cep2 + ", estadoCod=" + estadoCod
				+ ", cep=" + cep + "]";
	}

}
