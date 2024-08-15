package servico;

public class BancoCaixa implements Pagamento{

	private static final double JUROS = 0.03;

	@Override
	public double juros(double quantia, int meses) {
		return quantia * JUROS * meses;
	}
	
	
}
