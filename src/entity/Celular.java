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

	@Override
	public String toString() {
		return "- Número do Celular: " + numero;
	}
	
	public String retornoCelularCliente() {
		return "- Número do Celular: " + numero 
				+ " - Cliente: " + cliente.getNome();
	}
}
