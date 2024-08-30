package model.DAO;

import java.util.List;
import java.util.Set;

import entity.Cliente;
import entity.ClienteFilme;
import entity.Filme;

public interface Cliente_FilmeDAO {
	
	List<ClienteFilme> encontrarClienteComFilme(String cpf);
	Set<Cliente> acharTodosClientesComFilmes();
	List<ClienteFilme> acharClientesPorFilme(Integer filme);
	void inserirClienteComFilme(ClienteFilme obj);

}
