package model.DAO;

import java.util.List;

import entity.Cliente;
import entity.Filme;

public interface Cliente_FilmeDAO {
	
	Cliente encontrarClienteComFilme(String cpf);
	List<Cliente> acharTodosClientesComFilmes();
	List<Cliente> acharClientesPorFilme(Filme filme);

}
