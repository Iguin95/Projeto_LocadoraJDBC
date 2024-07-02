package aplication;

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

		System.out.println("----Teste Cliente----");
		ClienteDAO clienteDao = FabricaDAO.criarClienteDAO();
		List<Cliente> cli = clienteDao.acharTodos();
		for (Cliente cliente : cli) {
			System.out.println(cliente);
		}
		
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
		

		System.out.println();
		System.out.println("----Teste Filme----");
		FilmeDAO filmeDao = FabricaDAO.criarFilmeDAO();
		Filme novoFilme = new Filme(null, "Procurando Nemo", 0, 2003, 30.00);
		filmeDao.inserir(novoFilme);
		System.out.println("Inserido! Novo ID = " + novoFilme.getId());
	
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
