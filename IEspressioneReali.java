package notazionePolaccaConcorrente;

/**
 * Classe che Implementa un'Espressione di Numeri Reali.
 * Ciò che deve fare è sapere se si tratta di un'espressione (tra due numeri e con operatore)
 * oppure se è un numero.
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
	 * Un'Espressione Reale deve sapere se è un'espressione (su cui bisogna operare) oppure se è un numero reale
	 * @return true se si ha almeno l'operatore
	 */
	public boolean isExpression();
	/**
	 * Un'Espressione reale può essere un numero reale da solo, quindi:
	 * @return true se Operatore è mancante ed i Numeri Reali dell'espressione sono Mancanti
	 */
	public boolean isNumber();
	/**
	 * Si prende l'operatore dell'espressione. Andrebbe fatto un controllo con "isExpression"
	 * @return char dell'operatore
	 */
	public char getOperator();
	/**
	 * Metodo per definire se è mancante il primo Numero
	 * @return true se è un Numero, false altrimenti
	 */
	public boolean missingN1();
	/**
	 * Metodo per definire se è mancante il secondo Numero
	 * @return true se è un numero, false altrimenti
	 */
	public boolean missingN2();
	/**
	 * Metodo per Osservare che Questa Espressione di Numeri Reali sia pronta ad essere eseguita
	 * @return true se: isExpression è true, missingN1 è false e missingN2 è false.
	 */
	public boolean isReady();
	/**
	 * Metodo per Ottenere il numero reale quando si è un numero reale invece che un'espressione
	 * @return double Numero Reale
	 */
	public double getNumero();
}