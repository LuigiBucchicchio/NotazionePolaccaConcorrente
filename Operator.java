package notazionePolaccaConcorrente;

/**
 * Classe per rappresentare un Operatore Elementare: (* ; - ; / ; +)
 * @author Luigi_Books
 *
 */
public class Operator {
	private char operator;
	/**
	 * Costruttore che utilizza un char e lo controlla.
	 * Lancia {@link IllegalArgumentException} se non è un operatore
	 * @param op, char
	 */
	public Operator(char op){
		if(op!='-'&&op!='/'&&op!='*'&&op!='+')
			throw new IllegalArgumentException("operatore non valido");
		operator=op;
	}
	/**
	 * Costruttore che utilizza una stringa e la controlla
	 * Lancia {@link IllegalArgumentException} se non è un operatore
	 * @param op, String
	 */
	public Operator(String op){
		if(op.length()!=1)
			throw new IllegalArgumentException("operatore non valido");
		if(!op.equals("-")&&!op.equals("/")&&!op.equals("*")&&!op.equals("+"))
			throw new IllegalArgumentException("operatore non valido");
		operator=op.charAt(0);
	}
	/**
	 * Get dell'operatore
	 * @return L'operatore (char)
	 */
	public char getOperator(){
		return operator;
	}
	/**
	 * Ridefinito toString
	 */
	@Override
	public String toString() {
		return "[" + operator + "]";
	}
}