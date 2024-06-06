package aplication;

import java.util.Locale;
import java.util.Scanner;

import entity.Celular;
import entity.Cidade;
import entity.Cliente;
import entity.Endereco;
import entity.Estado;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Estado est = new Estado("São Paulo");
		Cidade cid = new Cidade(null, est, "3200000", "São Paulo");
		Endereco end = new Endereco(null, cid, "Prof Ginica", "788", "Ap");
		Celular cel = new Celular(null, "3199999999");
		
		Cliente cliente = new Cliente("12345678911", "Igor", 23, end, cel);
		
		System.out.println(cliente);
		
		sc.close();
	}

}
