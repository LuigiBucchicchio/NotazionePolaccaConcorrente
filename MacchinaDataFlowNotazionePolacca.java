package notazionePolaccaConcorrente;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Classe che implementa un DataFlow e lo fa eseguire ad un ThreadPool di Esecutori
 * @author Luigi_Books
 *
 */
public class MacchinaDataFlowNotazionePolacca {
	public static long TIMECOSTR;
	public static long TIME;
	/**
	 * main contenente File, DataFlow ed Esecutori. prima dello start, stampa il tempo corrente.
	 * Verrà stampato il tempo di esecuzione all'ottenimento del risultato
	 * @param args None
	 * @throws InterruptedException Nel caso di un Interruzione improvvisa
	 * @throws IOException Se il File non è stato trovato
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		File f=new File("expression10.txt");
		TIMECOSTR=System.currentTimeMillis();
		DataFlow datiEspressione=new DataFlow(f);
		System.out.println("Tempo Costruzione: "+(System.currentTimeMillis()-TIMECOSTR)+"ms");
		//numero di Thread massimi in parallelo
		int n=4;
		ExecutorService threadpool = Executors.newFixedThreadPool(n);
		//ExecutorService threadpool=Executors.newCachedThreadPool();
		TIME=System.currentTimeMillis();
		for(int i=0;i<10;i++){
			threadpool.execute(new Esecutore(datiEspressione));
		}
		threadpool.shutdown();
		while (!threadpool.isTerminated()) {
		}
		System.out.println("Spegnimento Thread dopo:"+(System.currentTimeMillis()-TIME)+"ms");
		System.out.print("Sono stati utilizzati "+n+" Threads al massimo in parallelo");


		/*
		Esecutore esecutore1= new Esecutore(datiEspressione);
		Esecutore esecutore2= new Esecutore(datiEspressione);
		Esecutore esecutore3= new Esecutore(datiEspressione);
		Esecutore esecutore4= new Esecutore(datiEspressione);
		TIME=System.currentTimeMillis();
		esecutore1.start();
		esecutore2.start();
		esecutore3.start();
		esecutore4.start();
		//Soft Interrupt, controllando che nessuno dei Thread sia vivo. Altrimenti aspetta un po'
		int attesaMain=2;
		while(esecutore1.isAlive()&&esecutore2.isAlive()&&esecutore3.isAlive()&&esecutore4.isAlive()){
			Thread.sleep(attesaMain);
		}
		esecutore1.interrupt();
		esecutore2.interrupt();
		esecutore3.interrupt();
		esecutore4.interrupt();
		System.out.println("Spegnimento dopo:"+(System.currentTimeMillis()-TIME)+"ms con attesa del main di "+attesaMain+"ms");
		*/
	}
}