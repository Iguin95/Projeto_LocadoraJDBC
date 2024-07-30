package aplication;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Locale;
import java.util.Scanner;

import entity.Celular;
import entity.Cliente;
import entity.Endereco;
import model.DAO.ClienteDAO;
import model.DAO.FabricaDAO;

public class Program {
	
	
	private static final int CADASTRAR_CLIENTE = 1;
	private static final int CADASTRAR_FILME = 2;
	private static final int VENDA_ALUGUEL = 3;
	private static final int CONSULTAR_FILME = 4;
	private static final int LISTAR_CLIENTE = 5;
	private static final int CONSULTAR_ID = 6;
	private static final int SAIR = 0;

	// fazer opções para consultar cidade, estado, celular e endereço

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Cliente cliente = null;

		System.out.println("--Sistema Locadora de Filmes IgaraTexas--");
		System.out.println("______________________________________________");
		int opcaoMenu = 99;
		System.out.println();

		while (opcaoMenu != SAIR) {
			System.out.println("--------Menu--------\n");
			System.out.println(" 1 - Cadastro de cliente;\n " + "2 - Cadastro de filme;\n "
					+ "3 - Venda ou Aluguel de filme;\n " + "4 - Consultar filme;\n " + "5 - Listar clientes;\n "
					+ "6 - Consultar cliente;\n " + "0 - Sair\n");
			System.out.println("Digite a opção desejada: ");
			opcaoMenu = sc.nextInt();
			sc.nextLine();
			switch (opcaoMenu) {
			case CADASTRAR_CLIENTE: {
				System.out.println("--Cadastro de Cliente--\n");

				boolean validarCPF = false;
				String cpf = null;
				while (!validarCPF) {
					System.out.print("Insira o CPF: ");
					cpf = sc.nextLine();
					if (Cliente.validarCPF(cpf) == true) {
						validarCPF = true;
					} else {
						System.out.println("CPF inválido!");
					}
				}

				boolean validarNome = false;
				String nome = null;
				while (!validarNome) {
					System.out.print("Insira o nome: ");
					nome = sc.nextLine();
					if (Cliente.validacaoNome(nome) == true) {
						validarNome = true;
					}
				}

				LocalDate data = null;
				boolean dataValida = false;
				while (!dataValida) {
					try {
						System.out.print("Insira a data de nascimento(dd/MM/yyyy): ");
						data = LocalDate.parse(sc.next(), dmt);
						sc.nextLine();
						dataValida = true;
					} catch (DateTimeParseException e) {
						System.out.println("Formato de data inválido! - " + e.getMessage());
					}
				}

				boolean validarCelular = false;
				String celular = null;
				while (!validarCelular) {
					System.out.print("Insira o número de celular para contato: ");
					celular = sc.nextLine();
					if (Celular.validarNumero(celular) == true) {
						validarCelular = true;
					}
				}

				cadastrarCliente(cpf, nome, data, celular);

			}
			default:
				// throw new IllegalArgumentException("Entrada inesperada: " + op);
			}
		}

		System.out.println(cliente);

		sc.close();
	}
	

	private static void cadastrarCliente(String cpf, String nome, LocalDate dataNascimento, String celular) {

		/*
		 * Period.between: Calcula o período entre a data de nascimento e a data atual.
		 * O método getYears() é usado para extrair a quantidade de anos completos desse
		 * período. LocalDate.now(): Obtém a data atual, necessária para calcular a
		 * diferença em anos entre a data de nascimento e o presente momento.
		 */
		LocalDate dataAtual = LocalDate.now();
		Period periodo = Period.between(dataNascimento, dataAtual);
		int idade = periodo.getYears();
		
		if (idade > 17) {
			
			Endereco end = new Endereco(11, null, null, null, null, null);//teste
			Celular cel = new Celular(2, null);//teste
			
			ClienteDAO clienteDao = FabricaDAO.criarClienteDAO();
			Cliente novoCliente = new Cliente(cpf, nome, dataNascimento, end, cel);
			clienteDao.inserir(novoCliente);
			System.out.println("\nInserido! Novo ID = " + novoCliente.getCPF());
		} else {
			System.out.println("Idade inapropriada! Necessita de um responsável para efetuar o cadastro!");
		}
	}

}
