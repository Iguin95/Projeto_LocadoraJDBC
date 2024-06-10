package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;


public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String CPF; //chave primária
	private String nome;
	private Date idade;
	private Endereco endereco;
	private Celular celular;
	
	
	public Cliente() {
	}

	public Cliente(String cPF, String nome, Date idade, Endereco endereco, Celular celular) {
		CPF = cPF;
		this.nome = nome;
		this.idade = idade;
		this.endereco = endereco;
		this.celular = celular;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getIdade() {
		return idade;
	}

	public void setIdade(Date idade) {
		this.idade = idade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Celular getCelular() {
		return celular;
	}

	public void setCelular(Celular celular) {
		this.celular = celular;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(CPF);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(CPF, other.CPF);
	}

	@Override
	public String toString() {
		return "Cliente -> [CPF = " + CPF + ", Nome = " + nome 
				+ ", Idade = " + idade + "; " + endereco + celular + "]";
	}

}
