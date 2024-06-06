package model.DAO;

import java.util.List;

import entity.Cliente;

public interface ClienteDAO {
	
	void inserir(Cliente obj);
	void atualizar(Cliente obj);
	void deletarPorId(Integer id);
	Cliente encontrarPorId(Integer id);
	List<Cliente> acharTodos();

}
