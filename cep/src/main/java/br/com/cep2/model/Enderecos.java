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
@Table(name = "enderecos")
public class Enderecos implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "uf")
	private String uf;
	@Basic(optional = false)
	@Column(name = "cidade_id")
	private int cidadeId;
	@Basic(optional = false)
	@Column(name = "nomeslog")
	private String nomeslog;
	@Basic(optional = false)
	@Column(name = "nomeclog")
	private String nomeclog;
	@Basic(optional = false)
	@Column(name = "bairro_id")
	private int bairroId;
	@Basic(optional = false)
	@Column(name = "logradouro")
	private String logradouro;
	@Basic(optional = false)
	@Column(name = "cep")
	private String cep;
	@Basic(optional = false)
	@Column(name = "uf_cod")
	private int ufCod;
	@Basic(optional = false)
	@Column(name = "logracompl")
	private String logracompl;

	/**
	 * 
	 * Default Constructor
	 * 
	 * @author pulu - 09/09/2013
	 */
	public Enderecos() {
	}

	/**
	 * 
	 * Parametrized Constructor
	 * 
	 * @author pulu - 09/09/2013
	 */
	public Enderecos(Integer id, String uf, int cidadeId, String nomeslog, String nomeclog, int bairroId,
			String logradouro, String cep, int ufCod, String logracompl) {
		this.id = id;
		this.uf = uf;
		this.cidadeId = cidadeId;
		this.nomeslog = nomeslog;
		this.nomeclog = nomeclog;
		this.bairroId = bairroId;
		this.logradouro = logradouro;
		this.cep = cep;
		this.ufCod = ufCod;
		this.logracompl = logracompl;
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

	public int getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(int cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getNomeslog() {
		return nomeslog;
	}

	public void setNomeslog(String nomeslog) {
		this.nomeslog = nomeslog;
	}

	public String getNomeclog() {
		return nomeclog;
	}

	public void setNomeclog(String nomeclog) {
		this.nomeclog = nomeclog;
	}

	public int getBairroId() {
		return bairroId;
	}

	public void setBairroId(int bairroId) {
		this.bairroId = bairroId;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getUfCod() {
		return ufCod;
	}

	public void setUfCod(int ufCod) {
		this.ufCod = ufCod;
	}

	public String getLogracompl() {
		return logracompl;
	}

	public void setLogracompl(String logracompl) {
		this.logracompl = logracompl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bairroId;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + cidadeId;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logracompl == null) ? 0 : logracompl.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((nomeclog == null) ? 0 : nomeclog.hashCode());
		result = prime * result + ((nomeslog == null) ? 0 : nomeslog.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		result = prime * result + ufCod;
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
		Enderecos other = (Enderecos) obj;
		if (bairroId != other.bairroId)
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidadeId != other.cidadeId)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logracompl == null) {
			if (other.logracompl != null)
				return false;
		} else if (!logracompl.equals(other.logracompl))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (nomeclog == null) {
			if (other.nomeclog != null)
				return false;
		} else if (!nomeclog.equals(other.nomeclog))
			return false;
		if (nomeslog == null) {
			if (other.nomeslog != null)
				return false;
		} else if (!nomeslog.equals(other.nomeslog))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		if (ufCod != other.ufCod)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Enderecos [id=" + id + ", uf=" + uf + ", cidadeId=" + cidadeId + ", nomeslog=" + nomeslog
				+ ", nomeclog=" + nomeclog + ", bairroId=" + bairroId + ", logradouro=" + logradouro + ", cep=" + cep
				+ ", ufCod=" + ufCod + ", logracompl=" + logracompl + "]";
	}

}
