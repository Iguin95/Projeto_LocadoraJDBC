package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


//classe associativa
public class ClienteFilme implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Criei a classe responsável por salvar cliente com seu filme,
	//pois um filme existe sem um cliente e um cliente existe sem filme.
	
	//Crie a tabela no db também
	
	private Integer id;
	private String idCliente;
	private Integer idFilme;
	
	private Filme filme;
	private Cliente cliente;
	
	List<Cliente> listaClienteFilme = new ArrayList<>();
	List<Filme> listaFilmeCliente = new ArrayList<>();
	
	public ClienteFilme() {	
	}
	
	public ClienteFilme(Integer id, String idCliente, Integer idFilme, Filme filme, Cliente cliente) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.idFilme = idFilme;
		this.filme = filme;
		this.cliente = cliente;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}


	public Integer getIdFilme() {
		return idFilme;
	}


	public void setIdFilme(Integer idFilme) {
		this.idFilme = idFilme;
	}


	public Filme getFilme() {
		return filme;
	}


	public void setFilme(Filme filme) {
		this.filme = filme;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<Cliente> getListaClienteFilme() {
		return listaClienteFilme;
	}


	public void setListaClienteFilme(List<Cliente> listaClienteFilme) {
		this.listaClienteFilme = listaClienteFilme;
	}


	public List<Filme> getListaFilmeCliente() {
		return listaFilmeCliente;
	}


	public void setListaFilmeCliente(List<Filme> listaFilmeCliente) {
		this.listaFilmeCliente = listaFilmeCliente;
	}

	
	public String ClienteComFilmes() {
		return "Cliente com Filmes: \n" + filme + "\n";
	}
	
	public String FilmesComCliente() {
		return "Filmes com Cliente: \n" + cliente + "\n";
	}

	@Override
	public String toString() {
		   StringBuilder sb = new StringBuilder();
	        sb.append("Cliente: ").append(cliente.getNome()).append("\n");
	        sb.append("Filmes:\n");
	        
	        for (Filme filme : listaFilmeCliente) {
	            sb.append(filme.getNome()).append("\n");
	        }
	        return sb.toString();
	}
	

}
