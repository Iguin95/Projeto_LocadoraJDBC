package aplication;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entity.Celular;
import entity.Cidade;
import entity.Cliente;
import entity.Endereco;
import entity.Estado;
import implementation.DAO.ClienteDAO_JDBC;
import model.DAO.CidadeDAO;
import model.DAO.ClienteDAO;
import model.DAO.EnderecoDAO;
import model.DAO.FabricaDAO;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("----Teste Cliente----");
		ClienteDAO clienteDao = FabricaDAO.criarClienteDAO();
		Cliente cli = clienteDao.encontrarPorCPF("15684310664");
		System.out.println(cli);
		
		
		System.out.println("----Teste Endereço----");
		EnderecoDAO enderecoDao = FabricaDAO.criarEnderecoDAO();
		Endereco end = enderecoDao.encontrarPorId(9);
		System.out.println(end);
		
		
		System.out.println("----Teste Cidade----");
		CidadeDAO cidadeDao = FabricaDAO.criarCidadeDAO();
		Cidade cid = cidadeDao.encontrarPorId(3);
		System.out.println(cid);
		
		sc.close();
	}

}
