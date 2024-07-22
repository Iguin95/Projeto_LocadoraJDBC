package implementation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data_base.ConexaoDB;
import data_base.ExcecaoDataBase;
import entity.Cliente;
import entity.ClienteFilme;
import entity.Filme;
import model.DAO.Cliente_FilmeDAO;

public class Cliente_filmeDAO_JDBC implements Cliente_FilmeDAO {

	Connection conn = null;

	public Cliente_filmeDAO_JDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<ClienteFilme> encontrarClienteComFilme(String cpf) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select cliente.nome, filme.nome_filme, cliente.cpf "
					+ "from cliente join filme_cliente on cliente.cpf = filme_cliente.idCliente "
					+ "join filme on filme.id = filme_cliente.idFilme " + "where cliente.cpf = ?");

			ps.setString(1, cpf);
			rs = ps.executeQuery();

			/*
			 * Declaração e inicialização de um Map para associar os CPFs dos clientes
			 * (String) aos objetos ClienteFilme. Isso ajuda a agrupar filmes para cada
			 * cliente.
			 */

			Map<String, ClienteFilme> clienteFilmeMap = new HashMap<>();

			while (rs.next()) { // Enquanto existir uma linha do ResultSet, faça...

				String clienteCpf = rs.getString("cliente.cpf"); // Obtém o CPF do cliente da linha atual do ResultSet
																	// e...
				ClienteFilme clienteFilme = clienteFilmeMap.get(clienteCpf); // ...tenta encontrar um ClienteFilme
																				// associado a esse CPF no Map.

				/*
				 * Se não houver um 'ClienteFilme' associado ao CPF (ou seja, o cliente ainda
				 * não foi processado): - Cria um novo objeto Cliente. - Define o nome e o CPF
				 * do cliente com os valores do ResultSet. - Cria um novo objeto 'ClienteFilme'.
				 * - Define o cliente no objeto 'ClienteFilme'. - Adiciona o 'ClienteFilme' ao
				 * Map, associando-o ao CPF do cliente.
				 */

				if (clienteFilme == null) {
					Cliente cliente = instanciandoCliente(rs);
					cliente.setCPF(clienteCpf);

					clienteFilme = new ClienteFilme();
					clienteFilme.setCliente(cliente);

					clienteFilmeMap.put(clienteCpf, clienteFilme);
				}

				Filme filme = instanciandoFilme(rs);

				clienteFilme.getListaFilmeCliente().add(filme);
			}

			return new ArrayList<>(clienteFilmeMap.values());
			/*
			 * Após processar todas as linhas do ResultSet, retorna uma nova lista
			 * (ArrayList) contendo todos os valores do Map, ou seja, todos os objetos
			 * ClienteFilme.
			 */

		} catch (SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		} finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}

		/*
		 * OBS: Essa estrutura permite agrupar facilmente todos os filmes de cada
		 * cliente, evitando duplicações e facilitando a construção da lista final de
		 * ClienteFilme: - Se um cliente já existe no Map (com o mesmo CPF), seus filmes
		 * são adicionados à lista de filmes desse cliente. - Se um cliente não existe
		 * no Map, um novo objeto ClienteFilme é criado e adicionado ao Map.
		 */
	}

	@Override
	public Set<Cliente> acharTodosClientesComFilmes() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select cliente.nome, cliente.cpf "
					+ "from cliente join filme_cliente on cliente.cpf = filme_cliente.idCliente "
					+ "order by cliente.nome");

			rs = ps.executeQuery();

			Set<Cliente> set = new HashSet<>();

			while (rs.next()) {
				Cliente cliente = instanciandoCliente(rs);

				set.add(cliente);
			}

			return set;

		} catch (SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		} finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
	}

	@Override
	public List<ClienteFilme> acharClientesPorFilme(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select filme.nome_filme, cliente.nome, cliente.cpf "
					+ "from cliente join filme_cliente on cliente.cpf = filme_cliente.idCliente "
					+ "join filme on filme.id = filme_cliente.idFilme " + "where filme.id = ? "
					+ "order by cliente.nome ");

			ps.setInt(1, id);
			rs = ps.executeQuery();

			Map<String, ClienteFilme> clienteFilmeMap = new HashMap<>();

			while (rs.next()) {

				String clienteCpf = rs.getString("cliente.cpf");
				ClienteFilme clienteFilme = clienteFilmeMap.get(clienteCpf); 

				if (clienteFilme == null) {
					Cliente cliente = instanciandoCliente(rs);
					cliente.setCPF(clienteCpf);

					clienteFilme = new ClienteFilme();
					clienteFilme.setCliente(cliente);

					clienteFilmeMap.put(clienteCpf, clienteFilme);
				}

				Filme filme = instanciandoFilme(rs);

				clienteFilme.getListaFilmeCliente().add(filme);
			}

			return new ArrayList<>(clienteFilmeMap.values());

		} catch (SQLException e) {
			throw new ExcecaoDataBase(e.getMessage());
		} finally {
			ConexaoDB.FecharStatement(ps);
			ConexaoDB.FecharResultSet(rs);
		}
	}

	private Cliente instanciandoCliente(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setNome(rs.getString("cliente.nome"));
		cliente.setCPF(rs.getString("cliente.cpf"));
		return cliente;
	}

	private Filme instanciandoFilme(ResultSet rs) throws SQLException {
		Filme filme = new Filme();
		filme.setNome(rs.getString("filme.nome_filme"));
		return filme;
	}

}
