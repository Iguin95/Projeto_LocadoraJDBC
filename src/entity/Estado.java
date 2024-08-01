package entity;

import java.io.Serializable;
import java.util.Objects;

public class Estado implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome; 
	
	public Estado() {
	}

	public Estado(Integer id, String nome) {
		this.nome = nome;
		this.id = id;
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

	@Override
	public String toString() {
		return "UF: " + nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		return Objects.equals(nome, other.nome);
	}
	
}
