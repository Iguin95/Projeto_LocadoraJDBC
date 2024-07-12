package implementation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import data_base.ConexaoDB;
import data_base.ExcecaoDataBase;
import entity.Celular;
import entity.Cidade;
import entity.Cliente;
import entity.Endereco;
import entity.Estado;
import model.DAO.ClienteDAO;

public class ClienteDAO_JDBC implements ClienteDAO {
	
	Date date;

	private Connection conn;

	public ClienteDAO_JDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Cliente obj) {
		//Confere se o cliente já existe pegando o CPF que foi passado como parâmetro
		if (!clienteExistente(obj.getCPF())) {
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(
						"insert into cliente "
						+ "(cpf, nome, idade, endereco_cliente, celular_cliente) " 
						+ "values " + "(?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS
						);
				
				ps.setString(1, obj.getCPF());
				ps.setString(2, obj.getNome());
				ps.setDate(3, Date.valueOf(obj.getIdade()));/*Ao inserir uma data no banco de dados, 
				é necessário convertê-la para java.sql.Date. Isso pode ser feito usando o método valueOf de 
				java.sql.Date, que aceita um LocalDate.*/

				ps.setInt(4, obj.getEndereco().getId());
				ps.setInt(5, obj.getCelular().getId());

				int linhasAfetadas = ps.executeUpdate();

				if (linhasAfetadas > 0) {
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next()) {
						String id = rs.getString("cpf");
						obj.setCPF(id);
					}
					ConexaoDB.FecharResultSet(rs);
				} else {
					throw new ExcecaoDataBase("Erro inesperado! Nenhuma linha foi afetada");
				}
			} catch (SQLException e) {
				throw new ExcecaoDataBase(e.getMessage());
			} finally {
				ConexaoDB.FecharStatement(ps);
			}

		} else {
			throw new ExcecaoDataBase("Erro! Já existe cliente com CPF: " + obj.getCPF());
		}
	}

	//método para verificar se um cliente já existe
	private boolean clienteExistente(String cpf) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select 1 from cliente " 
					+ "where cliente.cpf = ?"
					);//consulta SQL que vai pegar o registro(no caso o CPF)
			
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			return rs.next();//se retornar true, quer dizer que o CPF ja existe
		} catch (SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		} finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
	}

	@Override
	public void atualizar(Cliente obj, String cpfOriginal) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"update cliente "
					+ "set cpf = ?, nome = ?, idade = ?, endereco_cliente = ?, celular_cliente = ? "
					+ "where cpf = ? "
					);
			ps.setString(1, obj.getCPF());
			ps.setString(2, obj.getNome());
			ps.setDate(3, Date.valueOf(obj.getIdade()));
			ps.setInt(4, obj.getEndereco().getId());
			ps.setInt(5, obj.getCelular().getId());
			ps.setString(6, cpfOriginal);
			
			ps.executeUpdate();
					
		}catch(SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		}finally {
			ConexaoDB.FecharStatement(ps);
		}

	}
	
	@Override
	public Cliente encontrarPorIdParaAtualizar(String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select * from cliente where cliente.cpf = ?"
					);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {	
				Endereco end = new Endereco();
				end.setId(rs.getInt("endereco_cliente"));
				
				Celular cel = new Celular();
				cel.setId(rs.getInt("celular_cliente"));
				
				Cliente cli = new Cliente();
				cli.setCPF(rs.getString("cpf"));
				cli.setNome(rs.getString("nome"));
				cli.setIdade(rs.getDate("idade").toLocalDate());				
				
				return cli;
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
	public void deletarPorId(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cliente encontrarPorCPF(String cpf) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select cliente.CPF, cliente.nome, cliente.idade, "
					+ "endereco.rua, endereco.bairro, endereco.numeroCasa, endereco.complemento, "
					+ "celular.numeroCelular, " 
					+ "cidade.nome_cidade, cidade.cep, " 
					+ "estado.nome_estado "
					+ "from cliente join endereco on endereco.id = cliente.endereco_cliente "
					+ "join celular on celular.id = cliente.celular_cliente "
					+ "join cidade on cidade.id = endereco.id_Cidade "
					+ "join estado on estado.id = cidade.estado_da_cidade " + "where CPF = ?"
					);

			ps.setString(1, cpf);
			rs = ps.executeQuery();

			if (rs.next()) {

				Estado est = instanciandoEstado(rs);
				Celular cell = instanciandoCelular(rs);
				Cidade cid = instanciandoCidade(rs, est);
				Endereco end = instanciandoEndereco(rs, cid);
				Cliente obj = instanciandoCliente(rs, cell, end);

				return obj;

			}
			return null;
		} catch (SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		} finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
	}
	

	@Override
	public List<Cliente> acharTodos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select cliente.CPF, cliente.nome, cliente.idade, "
					+ "endereco.rua, endereco.bairro, endereco.numeroCasa, endereco.complemento, "
					+ "celular.numeroCelular, " + "cidade.nome_cidade, cidade.cep, " 
					+ "estado.nome_estado "
					+ "from cliente join endereco on endereco.id = cliente.endereco_cliente "
					+ "join celular on celular.id = cliente.celular_cliente "
					+ "join cidade on cidade.id = endereco.id_Cidade "
					+ "join estado on estado.id = cidade.estado_da_cidade " + "order by cliente.nome "
					);

			rs = ps.executeQuery();
			List<Cliente> list = new ArrayList<>();
			while (rs.next()) {

				Estado est = instanciandoEstado(rs);
				Celular cell = instanciandoCelular(rs);
				Cidade cid = instanciandoCidade(rs, est);
				Endereco end = instanciandoEndereco(rs, cid);
				Cliente obj = instanciandoCliente(rs, cell, end);

				list.add(obj);

			}
			return list;
		} catch (SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		} finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
	}

	// Funções para reutilização de instanciações
	private Estado instanciandoEstado(ResultSet rs) throws SQLException {
		Estado est = new Estado();
		est.setNome(rs.getString("estado.nome_estado"));

		return est;
	}

	private Cidade instanciandoCidade(ResultSet rs, Estado est) throws SQLException {
		Cidade cid = new Cidade();
		cid.setNome(rs.getString("cidade.nome_cidade"));
		cid.setCEP(rs.getString("cidade.cep"));
		cid.setUf(est);

		return cid;
	}

	private Endereco instanciandoEndereco(ResultSet rs, Cidade cid) throws SQLException {
		Endereco end = new Endereco();
		end.setRua(rs.getString("endereco.rua"));
		end.setBairro(rs.getString("endereco.bairro"));
		end.setNumero(rs.getString("endereco.numeroCasa"));
		end.setComplemento(rs.getString("endereco.complemento"));
		end.setCidade(cid);

		return end;
	}

	private Celular instanciandoCelular(ResultSet rs) throws SQLException {
		Celular cell = new Celular();
		cell.setNumero(rs.getString("celular.numeroCelular"));

		return cell;
	}

	private Cliente instanciandoCliente(ResultSet rs, Celular cell, Endereco end) throws SQLException {
		Cliente obj = new Cliente();
		obj.setCPF(rs.getString("CPF"));
		obj.setNome(rs.getString("nome"));
		obj.setIdade(rs.getDate("idade").toLocalDate());/*este método espera um LocalDate mas está sendo passado
		um java.sql.Date. Para resolver isso, converta o java.sql.Date em LocalDate utilizando o toLocalDate da 
		classe java.sql.Date*/

		obj.setCelular(cell);
		obj.setEndereco(end);

		return obj;
	}

}
