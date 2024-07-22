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
		
		
		System.out.println("----Teste Estado----");
		EstadoDAO estadoDao = FabricaDAO.criarEstadoDAO();
		estadoDao.deletarPorId(5);
		System.out.println("Deletado!");
		
		
		System.out.println("----Teste Cidade----");
		CidadeDAO cidadeDao = FabricaDAO.criarCidadeDAO();
		cidadeDao.deletarPorId(5);
		System.out.println("Deletado!");
		
		
		System.out.println("----Teste Endereço----");
		EnderecoDAO enderecoDao = FabricaDAO.criarEnderecoDAO();
		enderecoDao.deletarPorId(12);
		System.out.println("Deletado!");
				
		System.out.println();
		System.out.println("----Teste Celular----");
		CelularDAO celularDao = FabricaDAO.criarCelularDAO();
		celularDao.deletarPorId(6);
		System.out.println("Deletado!");
		

		System.out.println();
		System.out.println("----Teste Filme----");
		FilmeDAO filmeDao = FabricaDAO.criarFilmeDAO();
		filmeDao.deletarPorId(6);
		System.out.println("Deletado!");
	

		sc.close();
	}

}
