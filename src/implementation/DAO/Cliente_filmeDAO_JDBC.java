package implementation.DAO;

import java.sql.Connection;
import java.util.List;

import entity.Cliente;
import entity.Filme;
import model.DAO.Cliente_FilmeDAO;

public class Cliente_filmeDAO_JDBC implements Cliente_FilmeDAO{
	
	Connection conn = null;
	
	public Cliente_filmeDAO_JDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Cliente encontrarClienteComFilme(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Cliente> acharTodosClientesComFilmes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> acharClientesPorFilme(Filme filme) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
