package model.DAO;

import java.util.List;

import entity.Cidade;
import entity.Estado;

public interface CidadeDAO {
	
	void inserir(Cidade obj);
	void atualizar(Cidade obj);
	void deletarPorId(Integer id);
	Cidade encontrarPorId(Integer id);
	Cidade encontrarPorIdParaAtualizar(Integer id);
	boolean existe(Cidade obj);
	Cidade buscarCidadeExistente(String nome, String cep, Estado uf);
	List<Cidade> acharTodos();

}
