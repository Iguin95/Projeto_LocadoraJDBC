package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Objects;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private String CPF; // chave primária
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

	public static boolean validacaoNome(String nome) {
		if (nome.length() < 7) {
			System.out.println("Nome inválido!");
			return false;
		} else {
			return true;
		}
	}

	public static boolean validarCPF(String CPF) {

		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			} else {
				dig10 = (char) (r + 48);
			}
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig11 = '0';
			} else {
				dig11 = (char) (r + 48);
			}

			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	/*
	 * Equals e HashCode é necessário para que o SET verifique corretamente se há
	 * duplicatas
	 */

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
		return "Cliente -> CPF: " + CPF + "\nNome: " + nome + ";\nIdade: " + idade + "; \n\n" + endereco + "\n"
				+ celular + "\n" + "_____________________________________________________________ \n";
	}

	public String clienteFilmeString() {
		return "Cliente: " + nome + " - CPF: " + CPF + "\n";
	}
}
