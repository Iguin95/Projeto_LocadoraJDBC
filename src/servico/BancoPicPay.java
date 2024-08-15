package servico;

public class BancoPicPay implements Pagamento{

	private static final double JUROS = 0.02;
	
	@Override
	public double juros(double quantia, int meses) {
		return quantia * JUROS * meses;
	}

}
