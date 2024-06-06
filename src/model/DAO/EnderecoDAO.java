package model.DAO;

import java.util.List;

import entity.Endereco;

public interface EnderecoDAO {
	
	void inserir(Endereco obj);
	void atualizar(Endereco obj);
	void deletarPorId(Integer id);
	Endereco encontrarPorId(Integer id);
	List<Endereco> acharTodos();

}
