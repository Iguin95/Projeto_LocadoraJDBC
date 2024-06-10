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
import model.DAO.ClienteDAO;
import model.DAO.FabricaDAO;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Date date = new Date(04/03/2000);
		
		Estado est = new Estado("São Paulo");
		Cidade cid = new Cidade(null, est, "3200000", "São Paulo");
		Endereco end = new Endereco(null, cid, "Prof Ginica", "788", "Ap");
		Celular cel = new Celular(null, "3199999999");
		
		Cliente cliente = new Cliente("12345678911", "Igor",date , end, cel);
		
		System.out.println(cliente);*/
		
		ClienteDAO clienteDao = FabricaDAO.criarClienteDAO();

		Cliente cli = clienteDao.encontrarPorCPF("15684310664");
		
		System.out.println(cli);
		
		sc.close();
	}

}
