package model.DAO;

import java.util.List;

import entity.Cliente;

public interface ClienteDAO {
	
	void inserir(Cliente obj);
	void atualizar(Cliente obj, String cpfOriginal);
	void deletarPorId(Integer id);
	Cliente encontrarPorCPF(String cpf);
	Cliente encontrarPorIdParaAtualizar(String id);
	boolean clienteExistente(String cpf);
	List<Cliente> buscarPorNome(String nome);
	List<Cliente> acharTodos();
	
}
