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
import entity.Filme;
import model.DAO.FilmeDAO;

public class FilmeDAO_JDBC implements FilmeDAO{
	
	Connection conn = null;
	
	public FilmeDAO_JDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Filme obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"insert into filme "
					+ "(nome_filme, classificacao, ano, preco) "
					+ "values "
					+ "(?, ?, ?, ?) ",
					Statement.RETURN_GENERATED_KEYS
					);
			
			ps.setString(1, obj.getNome());
			ps.setInt(2, obj.getClassificacao());
			ps.setInt(3, obj.getAno());
			ps.setDouble(4, obj.getPreco());
			
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
	public boolean filmeExistente(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select 1 from filme " 
					+ "where filme.id = ?"
					);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			return rs.next();//se retornar true, quer dizer que o filme ja existe
		} catch (SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		} finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
	}

	@Override
	public void atualizar(Filme obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"update filme "
					+ "set nome_filme = ?, classificacao = ?, ano = ?, preco = ? "
					+ "where id = ? "
					);
			ps.setString(1, obj.getNome());
			ps.setInt(2, obj.getClassificacao());
			ps.setInt(3, obj.getAno());
			ps.setDouble(4, obj.getPreco());
			ps.setInt(5, obj.getId());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		}finally {
			ConexaoDB.FecharStatement(ps);
		}
		
	}
	
	@Override
	public Filme encontrarPorIdParaAtualizar(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select * from filme where filme.id = ?"
					);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {	
				Filme fil = new Filme();
				fil.setId(rs.getInt("filme.id"));	
				
				return fil;
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
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"delete from filme "
					+ "where id = ? "
					);
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			
			if(rows == 0) {
				throw new ExcecaoDataBase("Id Inexistente!");
			}
		}catch(SQLException e) {
			throw new ExcecaoIntegridadeDB(e.getMessage());
		}
	}

	@Override
	public Filme encontrarPorId(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select filme.nome_filme, filme.classificacao, filme.ano, filme.preco "
					+ "from filme "
					+ "where filme.id = ?");
					
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Filme filme = instanciandoFilme(rs);
								
				return filme;
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
    public List<Filme> buscarPorNome(String nome) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Filme> filmes = new ArrayList<>();

        try {
            // Usando COLLATE para ignorar case e accents
            ps = conn.prepareStatement(
            		"SELECT * FROM filme WHERE nome_filme COLLATE utf8mb4_unicode_ci LIKE ?"
            		);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Filme filme = instanciandoFilme(rs);
                /*filme.setId(rs.getInt("id"));
                filme.setNome(rs.getString("nome_filme"));
                filme.setClassificacao(rs.getInt("classificacao"));
                filme.setAno(rs.getInt("ano"));
                filme.setPreco(rs.getDouble("preco"));*/
                
                filmes.add(filme);
            }
            
            /*Explicação: COLLATE utf8mb4_unicode_ci: O COLLATE é usado para especificar uma collation (ordenação) 
             * que ignora diferenças de acentuação e caixa alta/baixa. O utf8mb4_unicode_ci é uma collation do MySQL 
             * que faz exatamente isso. O ci significa case insensitive (insensível a maiúsculas e minúsculas).
             * LIKE: A função LIKE no SQL realiza a busca por padrões, e o uso de % ao redor da variável nome permite 
             * que sejam encontrados todos os registros que contenham a string, independentemente da posição.*/
            
        } catch (SQLException e) {
            throw new ExcecaoDataBase(e.getMessage());
        } finally {
            ConexaoDB.FecharStatement(ps);
            ConexaoDB.FecharResultSet(rs);
        }

        return filmes;
    }

	@Override
	public List<Filme> acharTodos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"select filme.nome_filme, filme.classificacao, filme.ano, filme.preco "
					+ "from filme order by filme.nome_filme "
					);
					
			rs = ps.executeQuery();
			List<Filme> list = new ArrayList<>();
			
			while(rs.next()) {
				Filme filme = instanciandoFilme(rs);
								
				list.add(filme);
			}
			return list;
		}catch(SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		}finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
		
	}

	
	private Filme instanciandoFilme(ResultSet rs) throws SQLException {
		Filme filme = new Filme();
		filme.setNome(rs.getString("filme.nome_filme"));
		filme.setClassificacao(rs.getInt("filme.classificacao"));
		filme.setAno(rs.getInt("filme.ano"));
		filme.setPreco(rs.getDouble("filme.preco"));
		return filme;
	}



}
