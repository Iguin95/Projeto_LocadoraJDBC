package implementation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data_base.ConexaoDB;
import data_base.ExcecaoDataBase;
import entity.Celular;
import entity.Cidade;
import entity.Cliente;
import entity.Endereco;
import entity.Estado;
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
					"select cliente.CPF, cliente.nome, cliente.idade, celular.numeroCelular, " 
					+ "endereco.rua, endereco.numeroCasa, endereco.complemento, " 
					+ "cidade.nome, cidade.cep, estado.nome "
					+ "from cliente join endereco on endereco.id = cliente.endereco_cliente "
					+ "join cidade join celular join estado "
					+ "where CPF = ?");
			
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			if(rs.next()) {
				Estado est = new Estado();
				est.setNome(rs.getString("estado.nome"));
						
				Cidade cid = new Cidade();
				cid.setNome(rs.getString("cidade.nome"));
				cid.setCEP(rs.getString("cidade.cep"));
				
				
				Celular cell = new Celular();
				cell.setNumero(rs.getString("celular.numeroCelular"));
				
				Endereco end = new Endereco();
				//end.setId(rs.getInt("endereco.id")); //erro na hora de resgatar o id
				end.setRua(rs.getString("endereco.rua"));
				end.setNumero(rs.getString("endereco.numeroCasa"));
				end.setLogradouro(rs.getString("endereco.complemento"));
				
				Cliente obj = new Cliente();
				obj.setCPF(rs.getString("CPF"));
				obj.setNome(rs.getString("nome"));
				obj.setIdade(rs.getDate("idade"));
				
				cid.setUf(est);
				end.setCidade(cid);
				obj.setCelular(cell);
				obj.setEndereco(end);
				
				
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
