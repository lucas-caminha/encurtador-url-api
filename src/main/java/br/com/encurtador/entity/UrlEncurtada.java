package br.com.encurtador.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UrlEncurtada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdUrl;
	private String url;
	private String urlEncurtada;
	private String senha;
	
	public Long getCdUrl() {
		return cdUrl;
	}
	public void setCdUrl(Long cdUrl) {
		this.cdUrl = cdUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlEncurtada() {
		return urlEncurtada;
	}
	public void setUrlEncurtada(String urlEncurtada) {
		this.urlEncurtada = urlEncurtada;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
