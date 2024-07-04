package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String CPF; //chave primária
	private String nome;
	private LocalDate idade;
	private Endereco endereco;
	private Celular celular;
	
	
	public Cliente() {
	}

	public Cliente(String cPF, String nome, LocalDate data, Endereco endereco, Celular celular) {
		CPF = cPF;
		this.nome = nome;
		this.idade = data;
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

	public LocalDate getIdade() {
		return idade;
	}

	public void setIdade(LocalDate idade) {
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

	
	/* Equals e HashCode é necessário para que o SET verifique corretamente
	se há duplicatas*/
	
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
		return "Cliente -> CPF: " + CPF + "\nNome: " + nome 
				+ ";\nIdade: " + idade + "; \n\n" + endereco + "\n" + celular + "\n"
				+ "_____________________________________________________________ \n";
	}

	public String clienteFilmeString() {
		return "Cliente: " + nome 
				+ " - CPF: " + CPF 
				+"\n";
	}
}
