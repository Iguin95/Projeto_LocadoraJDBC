package model.DAO;

import implementation.DAO.ClienteDAO_JDBC;

public class FabricaDAO {
	
	public static ClienteDAO criarClienteDAO() {
		return new ClienteDAO_JDBC();
	}

}
