package entity;

import java.io.Serializable;

public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cidade cidade;
	
	private Integer id;
	private String rua;
	private String numero;
	private String complemento;
	
	public Endereco() {
	}
	
	public Endereco(Integer id, Cidade cidade, String rua, String numero, String complemento) {
		this.cidade = cidade;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.id = id;
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

	public String getComplemento() {
		return complemento;
	}

	public void setLogradouro(String complemento) {
		this.complemento = complemento;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Endereço [Cidade = " + cidade + ", rua = " + rua + ", "
				+ "número da casa = " + numero + ", "
				+ "logradouro = " + complemento + "]";
	}
	
	
	
	
	
	
	

}