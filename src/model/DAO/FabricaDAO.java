package model.DAO;

import data_base.ConexaoDB;
import implementation.DAO.ClienteDAO_JDBC;

public class FabricaDAO {
	
	public static ClienteDAO criarClienteDAO() {
		return new ClienteDAO_JDBC(ConexaoDB.Conectar());
	}
	
	public static FilmeDAO criarFilmeDAO() {
		return new FilmeDAO_JDBC();
	}

}
