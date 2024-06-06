package entity;

import java.io.Serializable;

public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cidade cidade;
	
	private String rua;
	private String numero;
	private String logradouro;
	
	public Endereco(Cidade cidade, String rua, String numero, String logradouro) {
		this.cidade = cidade;
		this.rua = rua;
		this.numero = numero;
		this.logradouro = logradouro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Override
	public String toString() {
		return "Endereço [Cidade = " + cidade + ", rua = " + rua + ", "
				+ "número da casa = " + numero + ", "
				+ "logradouro = " + logradouro + "]";
	}
	
	
	
	
	
	
	

}
