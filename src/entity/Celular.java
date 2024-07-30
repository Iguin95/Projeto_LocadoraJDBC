package entity;

import java.io.Serializable;

public class Celular implements Serializable {
	private static final long serialVersionUID = 1L;

	//entidade fraca
	
	private Integer id;
	private String numero;
	
	private Cliente cliente;
	
	public Celular() {
	}
	
	public Celular(Integer id, String numero) {
		this.numero = numero;
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public static boolean validarNumero(String numero) {
		/*Uso de Expressões Regulares (Regex):
		 * A expressão regular \\d{11} é usada para verificar se a string
		 * numero consiste exatamente em 11 dígitos.
		 * \\d representa qualquer dígito númerico e {11} especifica que 
		 * deve haver exatamente 11 dígitos.
		 * Método matches:
		 * O método matches da classe String é usado para comparar a string
		 * com a expressão regular. Se a string corresponder ao padrão, 
		 * matches retorna true; caso contrário, retorna false.*/
		
		if(numero.matches("\\d{11}")){
			return true;
		}else {
			System.out.println("Número inválido!");
			return false;
		}
	}

	@Override
	public String toString() {
		return "- Número do Celular: " + numero;
	}
	
	public String retornoCelularCliente() {
		return "- Número do Celular: " + numero 
				+ " - Cliente: " + cliente.getNome();
	}
}
