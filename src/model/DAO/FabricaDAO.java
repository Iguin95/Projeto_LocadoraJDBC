package model.DAO;

import data_base.ConexaoDB;
import implementation.DAO.CelularDAO_JDBC;
import implementation.DAO.CidadeDAO_JDBC;
import implementation.DAO.ClienteDAO_JDBC;
import implementation.DAO.Cliente_filmeDAO_JDBC;
import implementation.DAO.EnderecoDAO_JDBC;
import implementation.DAO.EstadoDAO_JDBC;
import implementation.DAO.FilmeDAO_JDBC;

public class FabricaDAO {
	
	public static ClienteDAO criarClienteDAO() {
		return new ClienteDAO_JDBC(ConexaoDB.Conectar());
	}
	
	public static FilmeDAO criarFilmeDAO() {
		return new FilmeDAO_JDBC(ConexaoDB.Conectar());
	}
	
	public static EnderecoDAO criarEnderecoDAO() {
		return new EnderecoDAO_JDBC(ConexaoDB.Conectar());
	}
	
	public static CidadeDAO criarCidadeDAO() {
		return new CidadeDAO_JDBC(ConexaoDB.Conectar());
	}
	
	public static EstadoDAO criarEstadoDAO() {
		return new EstadoDAO_JDBC(ConexaoDB.Conectar());
	}
	
	public static CelularDAO criarCelularDAO() {
		return new CelularDAO_JDBC(ConexaoDB.Conectar());
	}
	
	public static Cliente_FilmeDAO criarClienteFilmeDAO() {
		return new Cliente_filmeDAO_JDBC(ConexaoDB.Conectar());
	}

}
