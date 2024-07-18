package model.DAO;

import java.util.List;

import entity.Estado;

public interface EstadoDAO {
	
	void inserir(Estado obj);
	void atualizar(Estado obj);
	void deletarPorId(Integer id);
	Estado encontrarPorId(Integer id);
	Estado encontrarPorIdParaAtualizar(Integer id);
	List<Estado> acharTodos();

}
