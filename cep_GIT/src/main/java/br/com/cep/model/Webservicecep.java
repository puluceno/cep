package br.com.cep.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Webservicecep implements Serializable {
    
    private static final long serialVersionUID = -7637290816156640407L;
    
    private String resultado;
    private String resultado_txt;
    private String uf;
    private String cidade;
    private String bairro;
    private String tpLogradouro;
    private String logradouro;
    public static final String SUCCESS_CODE = "1";
    public static final String ERROR_CODE = "0";
    
    public Webservicecep() {
    }
    
    public Webservicecep(String resultado) {
	this.resultado = resultado;
    }
    
    public Webservicecep(String resultado, String uf, String cidade, String bairro, String tpLogradouro,
	    String logradouro) {
	this.resultado = resultado;
	this.uf = uf;
	this.cidade = cidade;
	this.bairro = bairro;
	this.tpLogradouro = tpLogradouro;
	this.logradouro = logradouro;
    }
    
    public String getResultado() {
	return resultado;
    }
    
    public void setResultado(String resultado) {
	this.resultado = resultado;
    }
    
    public String getResultado_txt() {
	return resultado_txt;
    }
    
    public void setResultado_txt(String resultado_txt) {
	this.resultado_txt = resultado_txt;
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
    
    public String getBairro() {
	return bairro;
    }
    
    public void setBairro(String bairro) {
	this.bairro = bairro;
    }
    
    public String getTpLogradouro() {
	return tpLogradouro;
    }
    
    public void setTpLogradouro(String tpLogradouro) {
	this.tpLogradouro = tpLogradouro;
    }
    
    public String getLogradouro() {
	return logradouro;
    }
    
    public void setLogradouro(String logradouro) {
	this.logradouro = logradouro;
    }
}
