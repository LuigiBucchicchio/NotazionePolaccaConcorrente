package notazionePolaccaConcorrente;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
/**
 * Classe Data Flow. E' un Monitor e come tale condivide in Sincronia i dati che ha letto dal file.
 * @author Luigi_Books
 *
 */
public class DataFlow {
	private boolean end=false;
	private boolean busy;
	private final File file;
	private Stack<EspressioneReali> pilaEspressioni;
	/**
	 * In fase di lettura, DataFlow legge il contenuto del file ed immagazzina i dati
	 * @throws FileNotFoundException
	 */
	private void lettura() throws FileNotFoundException{
		Scanner s=new Scanner(file);
		String carattereLetto=s.next();
		if(!isOperator(carattereLetto)){
			s.close();
			throw new IllegalArgumentException("erroe sintattico");
		}else{
			pilaEspressioni.add(new EspressioneReali(new Operator(carattereLetto)));
		}
		while(s.hasNext()){
			ricercaDueNumeri(s);
		}
		s.close();
	}
	/**
	 * Ricorsivamente si è alla ricerca di due Numeri, altrimenti si avrebbe un errore sintattico.
	 * @param s
	 */
	private void ricercaDueNumeri(Scanner s){
		String carattereLetto=s.next();
		if(isOperator(carattereLetto)){
			pilaEspressioni.add(new EspressioneReali(new Operator(carattereLetto)));
			ricercaDueNumeri(s);
		}else{
			double n1=Double.parseDouble(carattereLetto);
			double n2=Double.parseDouble(s.next());
			pilaEspressioni.peek().setN1(n1);
			pilaEspressioni.peek().setN2(n2);
		}
	}
	/**
	 * Chiunque lo chiami si trova ad aspettare (se non si ha finito di operare e) se qualcuno sta già iterando.
	 * In caso contrario si compie un'iterazione sui dati, dove ogni operazione pronta viene eseguita e immagazzinata
	 * @throws InterruptedException se un Thread al suo interno viene interrotto
	 */
	public synchronized void iterate() throws InterruptedException{
		while(busy&&!end){
			wait();
		}
		if(!isEnd()){
			busy=true;
			// creazione subStack
			Stack<EspressioneReali> subStack= new Stack<EspressioneReali>();
			//finchè la pila non è vuota:
			while(!pilaEspressioni.isEmpty()){
				// se al momento in cima si trova un Espressione con operatore in attesa, va messa nel Substack
				if(!pilaEspressioni.peek().isReady()){
					subStack.push(pilaEspressioni.pop());
				}
				//altrimenti in cima si trova un espressione pronta
				else{
					// valutazione di N2
					EspressioneReali temp=pilaEspressioni.pop();
					double n2;
					char op=temp.getOperator();
					if(op=='-')
						n2=temp.getN1()-temp.getN2();
					else if(op=='/')
						n2=temp.getN1()/temp.getN2();
					else if(op=='*')
						n2=temp.getN1()*temp.getN2();
					else
						n2=temp.getN1()+temp.getN2();

					//se vi è solo una operazione pronta, abbiamo il risultato (è l'unico caso in cui non vi sono operazioni pronte a coppie)
					if(pilaEspressioni.isEmpty()){
						System.out.print("Risultato:"+n2+"\nTempo per Trovare la Soluzione:");
						System.out.println((System.currentTimeMillis()-MacchinaDataFlowNotazionePolacca.TIME)+"ms");
						end=true;
						busy=false;
						break;
					}

					//valutazione N1
					EspressioneReali temp2=pilaEspressioni.pop();
					double n1;
					op=temp2.getOperator();
					if(op=='-')
						n1=temp2.getN1()-temp2.getN2();
					else if(op=='/')
						n1=temp2.getN1()/temp2.getN2();
					else if(op=='*')
						n1=temp2.getN1()*temp2.getN2();
					else
						n1=temp2.getN1()+temp2.getN2();
					//finchè vi sono altre operazioni pronte in cima, ognuna di esse sarà relativa all'operatore prossimo. quindi ruotano N2 ed N1
					// il vecchio N2 è skippato. il vecchio N1 diventa N2 e la nuova operazione diventa N1
					while(pilaEspressioni.peek().isReady()){
						subStack.push(temp);
						n2=n1;
						temp=pilaEspressioni.pop();
						op=temp.getOperator();
						if(op=='-')
							n1=temp.getN1()-temp.getN2();
						else if(op=='/')
							n1=temp.getN1()/temp.getN2();
						else if(op=='*')
							n1=temp.getN1()*temp.getN2();
						else
							n1=temp.getN1()+temp.getN2();
					}
					//arrivati a questo punto abbiamo la nostra operazione
					pilaEspressioni.peek().setN1(n1);
					pilaEspressioni.peek().setN2(n2);
					subStack.push(pilaEspressioni.pop());
				}
			}
			// il subStack diventa la nuova pila da Iterare.
			while(!subStack.isEmpty())
				pilaEspressioni.push(subStack.pop());
			//notifica dell'avvenuta iterazione
			busy=false;
			notifyAll();
		}
	}

	/**
	 * Metodo costruttore del DataFlow. Esegue la Lettura in fase di costruzione
	 * @param f il file di origine
	 * @throws FileNotFoundException se file non trovato
	 */
	public DataFlow(File f) throws FileNotFoundException{
		this.file=f;
		pilaEspressioni=new Stack<EspressioneReali>();
		lettura();
		busy=false;
	}
	/**
	 * Metodo privato che consente di richiamare una procedura per sapere se una Stringa sia un operatore o meno
	 * @param op Stringa da controllare
	 * @return true se è un'operatore, false altrimenti
	 */
	private boolean isOperator(String op){
		if(!op.equals("-")&&!op.equals("/")&&!op.equals("*")&&!op.equals("+"))
			return false;
		return true;
	}
	/**
	 * Ridefinizione toString
	 */
	@Override
	public String toString() {
		if(this.pilaEspressioni.isEmpty())
			return "empty";
		else  return "pila con in cima: "+this.pilaEspressioni.peek();
	}
	/**
	 * Metodo per sapere se il flusso dati non ha bisogno di iterazioni aggiuntive
	 * @return true se si ha finito, false altirmenti
	 */
	public boolean isEnd(){
		return end;
	}

}