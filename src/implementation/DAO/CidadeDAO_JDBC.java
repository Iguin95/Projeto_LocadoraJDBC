package implementation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data_base.ConexaoDB;
import data_base.ExcecaoDataBase;
import entity.Cidade;
import entity.Estado;
import model.DAO.CidadeDAO;

public class CidadeDAO_JDBC implements CidadeDAO{
	
	Connection conn = null;
	
	public CidadeDAO_JDBC (Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Cidade obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Cidade obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarPorId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cidade encontrarPorId(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select cidade.nome_cidade, cidade.cep, " 
					+ "estado.nome_estado "
					+ "from cidade join estado on estado.id = cidade.estado_da_cidade "
					+ "where cidade.id = ?"
					);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Estado est = new Estado();
				est.setNome(rs.getString("estado.nome_estado"));
				
				Cidade cid = new Cidade();
				cid.setNome(rs.getString("cidade.nome_cidade"));
				cid.setCEP(rs.getString("cidade.cep"));
				
				cid.setUf(est);
				
				return cid;
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
	public List<Cidade> acharTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}