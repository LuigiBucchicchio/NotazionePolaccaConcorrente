package notazionePolaccaConcorrente;

/**
 * Classe che Implementa un'Espressione di Numeri Reali.
 * Ci� che deve fare � sapere se si tratta di un'espressione (tra due numeri e con operatore)
 * oppure se � un numero.
 * @author Luigi_Books
 *
 */
public interface IEspressioneReali {
	/**
	 * Primo Numero dell'espressione. Null nel caso non sia un'espressione
	 * @return Primo Numero
	 */
	public double getN1();
	/**
	 * Impostazione del Primo Numero dell'espressione. Va controllato se si ha almeno un Operatore
	 * @param n , ovvero il numero reale
	 */
	public void setN1(double n);
	/**
	 * Secondo Numero dell'espressione. Null nel caso non sia un'espressione
	 * @return Secondo Numero
	 */
	public double getN2();
	/**
	 * Impostazione del Secondo Numero dell'espressione. Va controllato se si ha almeno un Operatore
	 * @param n , ovvero il numero reale
	 */
	public void setN2(double n);
	/**
	 * Un'Espressione Reale deve sapere se � un'espressione (su cui bisogna operare) oppure se � un numero reale
	 * @return true se si ha almeno l'operatore
	 */
	public boolean isExpression();
	/**
	 * Un'Espressione reale pu� essere un numero reale da solo, quindi:
	 * @return true se Operatore � mancante ed i Numeri Reali dell'espressione sono Mancanti
	 */
	public boolean isNumber();
	/**
	 * Si prende l'operatore dell'espressione. Andrebbe fatto un controllo con "isExpression"
	 * @return char dell'operatore
	 */
	public char getOperator();
	/**
	 * Metodo per definire se � mancante il primo Numero
	 * @return true se � un Numero, false altrimenti
	 */
	public boolean missingN1();
	/**
	 * Metodo per definire se � mancante il secondo Numero
	 * @return true se � un numero, false altrimenti
	 */
	public boolean missingN2();
	/**
	 * Metodo per Osservare che Questa Espressione di Numeri Reali sia pronta ad essere eseguita
	 * @return true se: isExpression � true, missingN1 � false e missingN2 � false.
	 */
	public boolean isReady();
	/**
	 * Metodo per Ottenere il numero reale quando si � un numero reale invece che un'espressione
	 * @return double Numero Reale
	 */
	public double getNumero();
}