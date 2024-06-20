package implementation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data_base.ConexaoDB;
import data_base.ExcecaoDataBase;
import entity.Estado;
import model.DAO.EstadoDAO;

public class EstadoDAO_JDBC implements EstadoDAO{
	
	Connection conn = null;
	
	public EstadoDAO_JDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Estado obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Estado obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarPorId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Estado encontrarPorId(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement( 
					"select estado.nome_estado from estado "
					+ "where estado.id = ?"
					);
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Estado est = new Estado();
				est.setNome(rs.getString("estado.nome_estado"));
				
				return est;
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
	public List<Estado> acharTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
