package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import servico.ParcelaFilme;


//classe associativa
public class ClienteFilme implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Criei a classe responsável por salvar cliente com seu filme,
	//pois um filme existe sem um cliente e um cliente existe sem filme.

	
	private Integer id;
	private String idCliente;
	private Integer idFilme;
	private String desejaParcelar;
	
	private Filme filme;
	private Cliente cliente;
	private ParcelaFilme parcelaFilme;
	
	List<Cliente> listaClienteFilme = new ArrayList<>();
	List<Filme> listaFilmeCliente = new ArrayList<>();
	List<ParcelaFilme> listaParcelaFilme;
	
	public ClienteFilme() {	
	}
	
	public ClienteFilme(Integer id, String idCliente, Integer idFilme, ParcelaFilme parcelaFilme) {
		this.id = id;
		this.idCliente = idCliente;
		this.idFilme = idFilme;
		this.listaParcelaFilme = new ArrayList<>(); 
		this.listaParcelaFilme.add(parcelaFilme); 
		/*Se a lista não for inicializada, a chamada ao método getListaParcelaFilme() no método 
		 * inserirClienteComFilmeComParcela retornará null, e nada será inserido no banco.*/
	}
	
	public ClienteFilme(Integer id, String idCliente, Integer idFilme, String desejaParcelar) {
		this.id = id;
		this.idCliente = idCliente;
		this.idFilme = idFilme;
		this.desejaParcelar = desejaParcelar;
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
	
	
	public String getDesejaParcelar() {
		return desejaParcelar;
	}
	

	public void setDesejaParcelar(String desejaParcelar) {
		this.desejaParcelar = desejaParcelar;
	}
	

	public List<Cliente> getListaClienteFilme() {
		return listaClienteFilme;
	}


	public void setListaClienteFilme(List<Cliente> listaClienteFilme) {
		this.listaClienteFilme = listaClienteFilme;
	}


	public ParcelaFilme getParcelaFilme() {
		return parcelaFilme;
	}

	public void setParcelaFilme(ParcelaFilme parcelaFilme) {
		this.parcelaFilme = parcelaFilme;
	}

	public List<Filme> getListaFilmeCliente() {
		return listaFilmeCliente;
	}


	public void setListaFilmeCliente(List<Filme> listaFilmeCliente) {
		this.listaFilmeCliente = listaFilmeCliente;
	}
	
		
	public List<ParcelaFilme> getListaParcelaFilme() {
		return listaParcelaFilme;
	}

	public void setListaParcelaFilme(List<ParcelaFilme> listaParcelaFilme) {
		this.listaParcelaFilme = listaParcelaFilme;
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
