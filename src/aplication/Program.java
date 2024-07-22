package aplication;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entity.ClienteFilme;
import model.DAO.ClienteDAO;
import model.DAO.Cliente_FilmeDAO;
import model.DAO.FabricaDAO;


public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("----Teste Cliente com filme----");
		Cliente_FilmeDAO cliFilDao = FabricaDAO.criarClienteFilmeDAO();
		
		List<ClienteFilme> clientesFilmes = cliFilDao.acharClientesPorFilme(1);

		for (ClienteFilme clienteFilme : clientesFilmes) {
			System.out.println(clienteFilme);
		}
			

		sc.close();
	}

}
