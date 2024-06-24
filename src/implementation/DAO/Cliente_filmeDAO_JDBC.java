package implementation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_base.ConexaoDB;
import data_base.ExcecaoDataBase;
import entity.Cliente;
import entity.ClienteFilme;
import entity.Filme;
import model.DAO.Cliente_FilmeDAO;

public class Cliente_filmeDAO_JDBC implements Cliente_FilmeDAO{
	
	Connection conn = null;
	
	public Cliente_filmeDAO_JDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public ClienteFilme encontrarClienteComFilme(String cpf) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select cliente.nome, filme.nome_filme "
					+ "from cliente join filme_cliente on cliente.cpf = filme_cliente.idCliente "
					+ "join filme on filme.id = filme_cliente.idFilme "
					+ "where cliente.cpf = ?"
					);
			
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			List<Filme> listaFilmes = new ArrayList<>();
			
			if(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setNome(rs.getString("cliente.nome"));
				
				Filme filme = new Filme();
				filme.setNome(rs.getString("filme.nome_filme"));
				listaFilmes.add(filme);
				
				ClienteFilme cf = new ClienteFilme();
				cf.setCliente(cliente);
				cf.setListaFilmeCliente(listaFilmes);
				
				return cf;
			}
			return null;
		}catch(SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		}finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
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
