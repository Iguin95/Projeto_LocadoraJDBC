package implementation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data_base.ConexaoDB;
import data_base.ExcecaoDataBase;
import entity.Cliente;
import entity.Filme;
import model.DAO.FilmeDAO;

public class FilmeDAO_JDBC implements FilmeDAO{
	
	Connection conn = null;
	
	public FilmeDAO_JDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Filme obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Filme obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarPorId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Filme encontrarPorId(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select filme.nome_filme, filme.classificacao, filme.ano, filme.preco "
					+ "from filme "
					+ "where filme.id = ?");
					
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Filme filme = instanciandoFilme(rs);
								
				return filme;
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
	public List<Filme> acharTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Filme> acharFilmeComCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Filme instanciandoFilme(ResultSet rs) throws SQLException {
		Filme filme = new Filme();
		filme.setNome(rs.getString("filme.nome_filme"));
		filme.setClassificacao(rs.getInt("filme.classificacao"));
		filme.setAno(rs.getInt("filme.ano"));
		filme.setPreco(rs.getDouble("filme.preco"));
		return filme;
	}

}
