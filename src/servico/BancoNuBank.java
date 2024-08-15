package servico;

public class BancoNuBank implements Pagamento {

	private static final double JUROS = 0.025;
	
	@Override
	public double juros(double quantia, int meses) {
		return quantia * JUROS * meses;
	}

}
