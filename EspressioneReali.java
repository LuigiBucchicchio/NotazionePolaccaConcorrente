package notazionePolaccaConcorrente;

/**
 * Classe che implementa l'interfaccia di Espressione di Numeri Reali
 * @author Luigi_Books
 *
 */
public class EspressioneReali implements IEspressioneReali{
	private EspressioneReali N1;
	private EspressioneReali N2;
	private double numero=Double.NaN;
	private Operator operator=null;
	/**
	 * Costruttore Base con Operatore (ESPRESSIONE)
	 * @param op, di classe {@link Operator}
	 */
	public EspressioneReali(Operator op) {
		this.N1=null;
		this.N2=null;
		this.operator=op;
	}
	/**
	 * Costruttore Base con Numer Reali (NUMERO REALE)
	 * @param n, double
	 */
	public EspressioneReali(double n) {
		this.N1=null;
		this.N2=null;
		this.operator=null;
		this.numero=n;
	}
	/**
	 * implementazione dell'interfaccia
	 */
	@Override
	public double getN1() {
		if(N1.isNumber())
			return N1.getNumero();
		else return Double.NaN;
	}
	/**
	 * implementazione dell'interfaccia
	 */
	@Override
	public double getN2() {
		if(N2.isNumber())
			return N2.getNumero();
		else return Double.NaN;
	}
	/**
	 * implementazione dell'interfaccia
	 */
	//MEME	
	@Override
	public boolean isExpression() {
		if(!Double.isNaN(numero))
			return false;
		return true;
	}
	/**
	 * implementazione dell'interfaccia
	 */
	@Override
	public boolean isNumber() {
		if(Double.isNaN(numero))
			return false;
		return true;
	}
	/**
	 * implementazione dell'interfaccia
	 */
	@Override
	public char getOperator() {
		return operator.getOperator();
	}
	/**
	 * implementazione dell'interfaccia
	 */
	@Override
	public  boolean missingN1() {
		if(N1==null)
			return true;
		if(N1.isNumber()){
			if(Double.isNaN(N1.getNumero()))
				return true;
		}
		return false;
	}
	/**
	 * implementazione dell'interfaccia
	 */
	@Override
	public  boolean missingN2() {
		if(N2==null)
			return true;
		if(N2.isNumber()){
			if(Double.isNaN(N2.getNumero()))
				return true;
		}
		return false;
	}
	/**
	 * implementazione dell'interfaccia
	 */
	@Override
	public double getNumero(){
		return numero;
	}
	/**
	 * implementazione dell'interfaccia
	 */
	@Override
	public boolean isReady() {
		if(N1==null || N2 ==null)
			return false;
		if(N1.isNumber()&&N2.isNumber()){
			if(operator!=null&&!Double.isNaN(N1.getNumero())&&!Double.isNaN(N2.getNumero()))
				return true;
		}
		return false;
	}
	/**
	 * implementazione dell'interfaccia
	 */
	@Override
	public void setN1(double n) {
		this.N1=new EspressioneReali(n);
	}
	/**
	 * implementazione dell'interfaccia
	 */
	@Override
	public void setN2(double n) {
		this.N2=new EspressioneReali(n);
	}
	/**
	 * Ridefinizione del toString
	 */
	@Override
	public String toString() {
		if(isNumber())
			return "["+ this.numero +"]";
		else{
			return "[N1=" + N1 + ", N2=" + N2 + ", operator=" + operator + "]";
		}
	}
}