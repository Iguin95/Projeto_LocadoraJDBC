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
import data_base.ExcecaoIntegridadeDB;
import entity.Cidade;
import entity.Estado;
import model.DAO.CidadeDAO;

public class CidadeDAO_JDBC implements CidadeDAO {

	Connection conn = null;

	public CidadeDAO_JDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Cidade obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"insert into cidade "
					+ "(nome_cidade, cep, estado_da_cidade) "
					+ "values " + "(?, ?, ?) ",
					Statement.RETURN_GENERATED_KEYS
					);
			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getCEP());
			ps.setInt(3, obj.getUf().getId());

			int linhasAfetadas = ps.executeUpdate();

			if (linhasAfetadas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				ConexaoDB.FecharResultSet(rs);
			} else {
				throw new ExcecaoDataBase("Erro inesperado! Nenhuma linha foi afetada!");
			}
		} catch (SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		} finally {
			ConexaoDB.FecharStatement(ps);
		}

	}

	@Override
	public void atualizar(Cidade obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"update cidade " 
					+ "set nome_cidade = ?, cep = ?, estado_da_cidade = ? "
					+ "where id = ? "
					);
			ps.setString(1, obj.getNome());
			ps.setString(2, obj.getCEP());
			ps.setInt(3, obj.getUf().getId());
			ps.setInt(4, obj.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		} finally {
			ConexaoDB.FecharStatement(ps);
		}

	}

	@Override
	public void deletarPorId(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"delete from cidade " 
					+ "where id = ? "
					);
			ps.setInt(1, id);
			int rows = ps.executeUpdate();

			if (rows == 0) {
				throw new ExcecaoDataBase("Id Inexistente!");
			}
		} catch (SQLException e) {
			throw new ExcecaoIntegridadeDB(e.getMessage());
		}
	}

	@Override
	public Cidade encontrarPorId(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select cidade.nome_cidade, cidade.cep, "
					+ "estado.nome_estado "
					+ "from cidade join estado on estado.id = cidade.estado_da_cidade " 
					+ "where cidade.id = ?"
					);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Estado est = instanciandoEstado(rs);

				Cidade cid = instanciandocidade(rs, est);

				cid.setUf(est);

				return cid;
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
	public Cidade encontrarPorIdParaAtualizar(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select * from cidade where cidade.id = ?"
					);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Estado est = new Estado();
				est.setId(rs.getInt("estado_da_cidade"));

				Cidade cid = new Cidade();
				cid.setId(rs.getInt("cidade.id"));
				cid.setNome(rs.getString("cidade.nome_cidade"));
				cid.setCEP(rs.getString("cidade.cep"));

				cid.setUf(est);

				return cid;
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
	public List<Cidade> acharTodos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select cidade.nome_cidade, cidade.cep, " 
					+ "estado.nome_estado "
					+ "from cidade join estado on estado.id = cidade.estado_da_cidade "
					+ "order by cidade.nome_cidade"
					);

			rs = ps.executeQuery();
			List<Cidade> list = new ArrayList<>();

			while (rs.next()) {
				Estado est = instanciandoEstado(rs);

				Cidade cid = instanciandocidade(rs, est);

				cid.setUf(est);

				list.add(cid);
			}
			return list;
		} catch (SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		} finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
	}

	@Override
	public boolean existe(Cidade obj) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT 1 FROM cidade WHERE nome_cidade = ? "
					+ "AND estado_da_cidade = ?"
					);
			ps.setString(1, obj.getNome());
			ps.setInt(2, obj.getUf().getId());
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		} finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
	}

	@Override
	public Cidade buscarCidadeExistente(String nome, String cep, Estado uf) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT * FROM cidade WHERE nome_cidade = ? "
					+ "AND cep = ? AND estado_da_cidade = ?"
					);
			ps.setString(1, nome);
			ps.setString(2, cep);
			ps.setInt(3, uf.getId());
			rs = ps.executeQuery();
			if (rs.next()) {
				Estado estado = new Estado(rs.getInt("estado_da_cidade"), null);
				return new Cidade(rs.getInt("id"), rs.getString("nome_cidade"), rs.getString("cep"), estado);
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
	}

	private Estado instanciandoEstado(ResultSet rs) throws SQLException {
		Estado est = new Estado();
		est.setNome(rs.getString("estado.nome_estado"));
		return est;
	}

	private Cidade instanciandocidade(ResultSet rs, Estado est) throws SQLException {
		Cidade cid = new Cidade();
		cid.setNome(rs.getString("cidade.nome_cidade"));
		cid.setCEP(rs.getString("cidade.cep"));
		cid.setUf(est);
		return cid;
	}

}
