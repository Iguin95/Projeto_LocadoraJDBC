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
		
		
		//Endereco end = new Endereco(11, null, null, null, null, null);
		//Celular cel = new Celular(2, null);
		
		//System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
	    //String dataNascimentoStr = sc.nextLine();

	    // Converte a string para LocalDate
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //define o formato da data
	    //LocalDate aniversario = LocalDate.parse(dataNascimentoStr, formatter); //Usa LocalDate.parse para converter a String para um objeto LocalDate

		/*System.out.println("----Teste Cliente----");
		ClienteDAO clienteDao = FabricaDAO.criarClienteDAO();
		Cliente cliente = clienteDao.encontrarPorIdParaAtualizar("00011177788");
		String cpfOriginal = cliente.getCPF();
		cliente.setNome("Pablo Snake");
		cliente.setCPF("88800011177");
		cliente.setIdade(LocalDate.parse("01/02/2003", formatter));
		
		Endereco end = new Endereco(10, null, null, null, null, null);
		cliente.setEndereco(end);
		Celular cel = new Celular(1, null);
		cliente.setCelular(cel);
		
		clienteDao.atualizar(cliente, cpfOriginal);
		System.out.println("Atualizado!");*/
		
		//System.out.println();
		System.out.println("----Teste Estado----");
		EstadoDAO estadoDao = FabricaDAO.criarEstadoDAO();
		Estado estado = estadoDao.encontrarPorIdParaAtualizar(4);
		estado.setNome("Maranhão");
		estadoDao.atualizar(estado);
		System.out.println("Atualizado!");
		
		/*System.out.println();
		System.out.println("----Teste Cidade----");
		CidadeDAO cidadeDao = FabricaDAO.criarCidadeDAO();
		Cidade cidade = cidadeDao.encontrarPorIdParaAtualizar(5);
		cidade.setNome("São Joaquim de Bicas");
		cidade.setCEP("36494-572");
		Estado estado = new Estado(1, null);
		cidade.setUf(estado);
		cidadeDao.atualizar(cidade);
		System.out.println("Atualizado!");*/
		
		
		/*System.out.println("----Teste Endereço----");
		EnderecoDAO enderecoDao = FabricaDAO.criarEnderecoDAO();
		Endereco endereco = enderecoDao.encontrarPorIdParaAtualizar(10);
		endereco.setRua("Av. Governador Valadares");
		endereco.setBairro("Centro");
		endereco.setComplemento("Mansão");
		endereco.setNumero("910C");
		
		Cidade cid = new Cidade(4, null, null, null);
		endereco.setCidade(cid);
		
		enderecoDao.atualizar(endereco);
		System.out.println("Atualizado!");*/
				
		/*System.out.println();
		System.out.println("----Teste Celular----");
		CelularDAO celularDao = FabricaDAO.criarCelularDAO();
		Celular celular = celularDao.encontrarSomenteCelularPorId(3);
		celular.setNumero("31998451238");
		celularDao.atualizar(celular);
		System.out.println("Atualizado!");*/
		

		/*System.out.println();
		System.out.println("----Teste Filme----");
		FilmeDAO filmeDao = FabricaDAO.criarFilmeDAO();
		Filme novoFilme = new Filme(null, "Procurando Nemo", 0, 2003, 30.00);
		filmeDao.inserir(novoFilme);
		System.out.println("Inserido! Novo ID = " + novoFilme.getId());*/
	
		/*System.out.println();
		System.out.println("----Teste Cliente com filme----");
		Cliente_FilmeDAO cliFilDao = FabricaDAO.criarClienteFilmeDAO();
		Set<Cliente> clientesFilmes = cliFilDao.acharTodosClientesComFilmes();

		for (Cliente todosClienteFilmes : clientesFilmes) {
			System.out.println(todosClienteFilmes.clienteFilmeString());
		}*/

		sc.close();
	}

}
