package servico;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContratoDeVenda {
	
	//Hora atual da máquina
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.systemDefault());
	
	private Double precoTotal;
	private LocalDate dataAtual;
	
	List<ParcelaFilme> parcelas = new ArrayList<>();
	List<AluguelFilme> aluguel = new ArrayList<>();
	
	public ContratoDeVenda() {
	}

	public ContratoDeVenda(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public ContratoDeVenda(Double precoTotal, LocalDate dataAtual) {
		this.precoTotal = precoTotal;
		this.dataAtual = dataAtual;
	}

	public Double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public LocalDate getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(LocalDate dataAtual) {
		this.dataAtual = dataAtual;
	}

	public List<ParcelaFilme> getParcelas() {
		return parcelas;
	}

	public List<AluguelFilme> getAluguel() {
		return aluguel;
	}
	
}
