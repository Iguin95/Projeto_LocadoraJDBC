package entity;

import java.io.Serializable;

public class Celular implements Serializable {
	private static final long serialVersionUID = 1L;

	//entidade fraca
	
	private String numero;
	
	public Celular(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Número do Celular: " + numero;
	}
	
	
	
}
