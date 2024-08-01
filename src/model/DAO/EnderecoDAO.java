package model.DAO;

import java.util.List;

import entity.Cidade;
import entity.Endereco;

public interface EnderecoDAO {
	
	void inserir(Endereco obj);
	void atualizar(Endereco obj);
	void deletarPorId(Integer id);
	Endereco encontrarPorId(Integer id);
	Endereco encontrarPorIdParaAtualizar(Integer id);
	boolean existe(Endereco obj);
	Endereco buscarEnderecoExistente(String rua, String bairro, String numero, String complemento, Cidade cidade);
	List<Endereco> acharTodos();

}
