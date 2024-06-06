package model.DAO;

import java.util.List;

import entity.Cidade;

public interface CidadeDAO {
	
	void inserir(Cidade obj);
	void atualizar(Cidade obj);
	void deletarPorId(Integer id);
	Cidade encontrarPorId(Integer id);
	List<Cidade> acharTodos();

}
