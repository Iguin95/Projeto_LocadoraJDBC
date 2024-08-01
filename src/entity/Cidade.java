package entity;

import java.io.Serializable;
import java.util.Objects;

public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Estado uf;
	private String CEP;
	private String nome;
	
	public Cidade() {
		
	}
	
	public Cidade(Integer id, String nome, String CEP, Estado uf) {
		this.uf = uf;
		this.CEP = CEP;
		this.nome = nome;
		this.id = id;
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

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Cidade: " + nome + " - CEP: " + CEP
				+ "(Estado = " + uf.getNome() + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(CEP, nome, uf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		return Objects.equals(CEP, other.CEP) && Objects.equals(nome, other.nome) && Objects.equals(uf, other.uf);
	}
	
}
