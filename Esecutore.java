package notazionePolaccaConcorrente;

/**
 * Classe Esecutore di DataFlow.
 * La sua esecuzione dipende Esclusivamente dai Dati e dall'esecuzione
 * @author Luigi_Books
 *
 */
public class Esecutore extends Thread{
	private DataFlow dati;

	/**
	 * Costruttore di un Esecutore.
	 * @param dati di classe {@link DataFlow}
	 */
	public Esecutore(DataFlow dati){
		this.dati=dati;
	}
	/**
	 * L'esecutore esegue finché il Monitor non si Ritiene Spento, ovvero finchè non è stato ottenuto il risultato
	 */
	@Override
	public void run() {
		while(true){
			try {
				if(this.dati.isEnd())
					break;
				this.dati.iterate();
			} catch (InterruptedException e) {
				System.exit(1);
			}
		}
	}
}