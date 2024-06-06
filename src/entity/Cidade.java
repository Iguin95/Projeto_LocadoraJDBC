package entity;

import java.io.Serializable;

public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Estado uf;
	private String CEP;
	private String nome;
	
	public Cidade(Estado uf, String CEP, String nome) {
		this.uf = uf;
		this.CEP = CEP;
		this.nome = nome;
	}

	public Estado getUf() {
		return uf;
	}

	public void setUf(Estado uf) {
		this.uf = uf;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String CEP) {
		this.CEP = CEP;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}


	

}
