package data_base;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConexaoDB {
	
	private static Connection conn = null;
	
	
	public static Connection Conectar() {
		if(conn == null) {
			try {
				Properties props = carregarPropriedades();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}catch(SQLException e) {
				throw new ExcecaoDataBase(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void FecharConexao() {
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				throw new ExcecaoDataBase(e.getMessage());
			}
		}
	}
	
	private static Properties carregarPropriedades() {
		try(FileInputStream fis = new FileInputStream("propriedades.jdbc")){
			Properties props = new Properties();
			props.load(fis);
			return props;
		}catch(IOException e) {
			throw new ExcecaoDataBase(e.getMessage());
		}
	}
	
	public static void FecharStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			}catch(SQLException e) {
				throw new ExcecaoDataBase(e.getMessage());
			}
		}
	}
	
	public static void FecharResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				throw new ExcecaoDataBase(e.getMessage());
			}
		}
	}


}
