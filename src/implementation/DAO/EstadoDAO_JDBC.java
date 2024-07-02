package implementation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"insert into estado "
					+ "(nome_estado) "
					+ "values "
					+ "(?) ",
					Statement.RETURN_GENERATED_KEYS
					);
			
			ps.setString(1, obj.getNome());
			
			int linhasAfetadas = ps.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				ConexaoDB.FecharResultSet(rs);
			}else {
				throw new ExcecaoDataBase("Erro inesperado! Nenhuma linha foi afetada");
			}
		}catch(SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		}finally {
			ConexaoDB.FecharStatement(ps);
		}
		
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
				Estado est = instanciandoEstado(rs);
				
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement( 
					"select estado.nome_estado from estado "
					+ "order by estado.nome_estado "
					);
			
			rs = ps.executeQuery();
			List<Estado> list = new ArrayList<>();
			
			while(rs.next()) {
				Estado est = instanciandoEstado(rs);
				
				list.add(est);
			}
			return list;
		}catch(SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		}finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
	}

	private Estado instanciandoEstado(ResultSet rs) throws SQLException {
		Estado est = new Estado();
		est.setNome(rs.getString("estado.nome_estado"));
		return est;
	}
}
