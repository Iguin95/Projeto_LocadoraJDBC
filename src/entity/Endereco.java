package entity;

import java.io.Serializable;
import java.util.Objects;

public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cidade cidade;
	
	private Integer id;
	private String rua;
	private String bairro;
	private String numero;
	private String complemento;
	
	public Endereco() {
	}
	
	public Endereco(Integer id, Cidade cidade, String rua, String bairro, String numero, String complemento) {
		this.cidade = cidade;
		this.rua = rua;
		this.bairro = bairro;
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
	

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
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

	public void setComplemento(String complemento) {
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
		return "Endereço:\n" + cidade + ";\nRua/Avenida: " + rua + "; \n"
				+ "Bairro: " + bairro + "; \n"
				+ "Número da casa/apartamento: " + numero + "; \n"
				+ "Complemento: " + complemento + ";\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bairro, cidade, complemento, numero, rua);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cidade, other.cidade)
				&& Objects.equals(complemento, other.complemento) && Objects.equals(numero, other.numero)
				&& Objects.equals(rua, other.rua);
	}
	
	
	
	
	
	
	

}
