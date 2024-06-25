package implementation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data_base.ConexaoDB;
import data_base.ExcecaoDataBase;
import entity.Celular;
import entity.Cliente;
import model.DAO.CelularDAO;

public class CelularDAO_JDBC implements CelularDAO{
	
	Connection conn = null;
	
	public CelularDAO_JDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Celular obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Celular obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarPorId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Celular encontrarPorId(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select celular.numeroCelular, cliente.nome "
					+ "from cliente join celular on celular.id = cliente.celular_cliente "
					+ "where celular.id = ?"
					);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Cliente cli = instanciandoCliente(rs);
				
				Celular cel = instanciandoCelular(rs, cli);
				
				return cel;
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
	public List<Celular> acharTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Cliente instanciandoCliente(ResultSet rs) throws SQLException {
		Cliente cli = new Cliente();
		cli.setNome(rs.getString("cliente.nome"));
		return cli;
	}
	
	private Celular instanciandoCelular(ResultSet rs, Cliente cli) throws SQLException {
		Celular cel = new Celular();
		cel.setNumero(rs.getString("celular.numeroCelular"));
		cel.setCliente(cli);
		return cel;
	}

}
