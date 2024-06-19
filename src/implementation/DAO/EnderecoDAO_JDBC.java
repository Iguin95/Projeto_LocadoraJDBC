package implementation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data_base.ConexaoDB;
import data_base.ExcecaoDataBase;
import entity.Cidade;
import entity.Endereco;
import entity.Estado;
import model.DAO.EnderecoDAO;

public class EnderecoDAO_JDBC implements EnderecoDAO{
	
	private Connection conn;
	
	public EnderecoDAO_JDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Endereco obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Endereco obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletarPorId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Endereco encontrarPorId(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select endereco.id, endereco.rua, endereco.bairro, endereco.numeroCasa, endereco.complemento, " 
					+ "cidade.nome_cidade, estado.nome_estado "
					+ "from endereco join cidade on cidade.id = endereco.id_Cidade "
					+ "join estado on estado.id = cidade.estado_da_cidade "
					+ "where endereco.id = ?");
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Endereco end = new Endereco();
				//adicione uma coluna bairro na tabela endereco
				end.setRua(rs.getString("endereco.rua"));
				end.setBairro(rs.getString("endereco.bairro"));
				end.setNumero(rs.getString("endereco.numeroCasa"));
				end.setComplemento(rs.getString("endereco.complemento"));

				Cidade cid = new Cidade();
				cid.setNome(rs.getString("cidade.nome_cidade"));
				
				Estado est = new Estado();
				est.setNome(rs.getString("estado.nome_estado"));
				
				cid.setUf(est);
				end.setCidade(cid);
				
				return end;
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
	public List<Endereco> acharTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
