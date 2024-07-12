package model.DAO;

import java.util.List;
import entity.Celular;

public interface CelularDAO {
	
	void inserir(Celular obj);
	void atualizar(Celular obj);
	void deletarPorId(Integer id);
	Celular encontrarPorId(Integer id);
	Celular encontrarPorIdParaAtualizar(Integer id); //Método útil para atualização no DB
	List<Celular> acharTodos();

}
