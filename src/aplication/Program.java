package aplication;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import entity.Celular;
import entity.Cidade;
import entity.Cliente;
import entity.ClienteFilme;
import entity.Endereco;
import entity.Estado;
import entity.Filme;
import model.DAO.CelularDAO;
import model.DAO.CidadeDAO;
import model.DAO.ClienteDAO;
import model.DAO.Cliente_FilmeDAO;
import model.DAO.EnderecoDAO;
import model.DAO.EstadoDAO;
import model.DAO.FabricaDAO;
import model.DAO.FilmeDAO;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		
		Endereco end = new Endereco(11, null, null, null, null, null);
		Celular cel = new Celular(2, null);
		
		System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
	    String dataNascimentoStr = sc.nextLine();

	    // Converte a string para LocalDate
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //define o formato da data
	    LocalDate aniversario = LocalDate.parse(dataNascimentoStr, formatter); //Usa LocalDate.parse para converter a String para um objeto LocalDate

		System.out.println("----Teste Cliente----");
		ClienteDAO clienteDao = FabricaDAO.criarClienteDAO();
		Cliente novoCliente = new Cliente("62145789355", "Ragnar Dimitrus", aniversario, end, cel);
		clienteDao.inserir(novoCliente);
		System.out.println("Inserido! Novo ID = " + novoCliente.getCPF());
		
		//System.out.println();
		//System.out.println("----Teste Estado----");
		//EstadoDAO estadoDao = FabricaDAO.criarEstadoDAO();
		//Estado estado = new Estado(1, null);
		
		
		//System.out.println();
		//System.out.println("----Teste Cidade----");
		//CidadeDAO cidadeDao = FabricaDAO.criarCidadeDAO();
		//Cidade cidade = new Cidade(1, null, null, null);
		
		
		/*System.out.println("----Teste Endereço----");
		EnderecoDAO enderecoDao = FabricaDAO.criarEnderecoDAO();
		Endereco novoEndereco = new Endereco(null, cidade, "Clóvis Salgado", "Industrial", "874C", "Apartamento");
		enderecoDao.inserir(novoEndereco);
		System.out.println("Inserido! Novo ID = " + novoEndereco.getId());*/
				
		/*System.out.println();
		System.out.println("----Teste Celular----");
		CelularDAO celularDao = FabricaDAO.criarCelularDAO();
		Celular novoCelular = new Celular(null, "31988710060");
		celularDao.inserir(novoCelular);
		System.out.println("Inserido! Novo ID = " + novoCelular.getId());*/
		

		/*System.out.println();
		System.out.println("----Teste Filme----");
		FilmeDAO filmeDao = FabricaDAO.criarFilmeDAO();
		Filme novoFilme = new Filme(null, "Procurando Nemo", 0, 2003, 30.00);
		filmeDao.inserir(novoFilme);
		System.out.println("Inserido! Novo ID = " + novoFilme.getId());*/
	
		System.out.println();
		System.out.println("----Teste Cliente com filme----");
		Cliente_FilmeDAO cliFilDao = FabricaDAO.criarClienteFilmeDAO();
		Set<Cliente> clientesFilmes = cliFilDao.acharTodosClientesComFilmes();

		for (Cliente todosClienteFilmes : clientesFilmes) {
			System.out.println(todosClienteFilmes.clienteFilmeString());
		}

		sc.close();
	}

}
