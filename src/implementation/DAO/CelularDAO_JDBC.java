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
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"insert into celular "
					+ "(numeroCelular) "
					+ "values "
					+ "(?) ",
					Statement.RETURN_GENERATED_KEYS
					);
			ps.setString(1, obj.getNumero());
			
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
	public void atualizar(Celular obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"update celular "
					+ "set numeroCelular = ? "
					+ "where celular.id = ? "
					);
			ps.setString(1, obj.getNumero());
			ps.setInt(2, obj.getId());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		}finally {
			ConexaoDB.FecharStatement(ps);
		}
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
	
	/*Método abaixo criado para atualização do celular no DB pois,
	 * só atualizaria se somente o celular fosse instanciado, se é
	 * instanciado com o cliente, não pode ser atualizado.*/
	@Override
	public Celular encontrarSomenteCelularPorId(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select * from celular where id = ?"
					);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Celular celular = new Celular();
				celular.setId(rs.getInt("id"));
				celular.setNumero(rs.getString("numeroCelular"));
				return celular;				
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select celular.numeroCelular, cliente.nome "
					+ "from cliente join celular on celular.id = cliente.celular_cliente "
					+ "order by cliente.nome"
					);
			
			rs = ps.executeQuery();
			List<Celular> list = new ArrayList<>();
			
			while(rs.next()) {
				Cliente cli = instanciandoCliente(rs);
				
				Celular cel = instanciandoCelular(rs, cli);
				
				list.add(cel);
				
			}
			return list;
		}catch(SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		}finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
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
