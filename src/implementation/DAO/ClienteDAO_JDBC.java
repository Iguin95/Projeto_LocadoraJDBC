package implementation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data_base.ConexaoDB;
import data_base.ExcecaoDataBase;
import entity.Cliente;
import model.DAO.ClienteDAO;

public class ClienteDAO_JDBC implements ClienteDAO{
	
	private Connection conn;
	
	public ClienteDAO_JDBC(Connection conn) {
		this.conn = conn;
	}
	

	@Override
	public void inserir(Cliente obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Cliente obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarPorId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente encontrarPorCPF(String cpf) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT * FROM locadora.cliente "
					+ "where CPF = ? ");
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			if(rs.next()) {
				Cliente obj = new Cliente();
				obj.setCPF(rs.getString("CPF"));
				obj.setNome(rs.getString("nome"));
				//obj.setCelular(rs.getString("celular"));
				obj.setIdade(rs.getDate("idade"));
				//obj.setEndereco(rs.getString(""));
				
				return obj;
			}
			return null;			
		}catch(SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		}
		finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
	}

	@Override
	public List<Cliente> acharTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
