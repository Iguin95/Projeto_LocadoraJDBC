package model.DAO;

import java.util.List;

import entity.Cliente;
import entity.Filme;

public interface FilmeDAO {
	
	void inserir(Filme obj);
	void atualizar(Filme obj);
	void deletarPorId(Integer id);
	Filme encontrarPorId(Integer id);
	List<Filme> acharFilmeComCliente(Cliente cliente);
	List<Filme> acharTodos();

}
