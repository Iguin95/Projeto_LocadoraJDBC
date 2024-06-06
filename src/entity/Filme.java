package entity;

import java.io.Serializable;
import java.util.Objects;

public class Filme implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer classificacao;
	private String nome;
	private Double preco;
	
	public Filme() {
	}
	
	public Filme(Integer classificacao, String nome, Double preco, Integer id) {
		this.classificacao = classificacao;
		this.nome = nome;
		this.preco = preco;
		this.id = id;
	}

	public Integer getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		return Objects.equals(id, other.id);
	}
	


}
