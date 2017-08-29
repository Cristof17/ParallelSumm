import java.util.Random;


public class ConsumerProducerBuffer {
	
	int buffer;
	boolean disponibil;
	
	public synchronized void produce(int value){
		//asta are un wait set, iar cand este apelat wait, 
		//thread-ul apelant intra in wait-set-ul lui o
		if (disponibil)
			try {
				wait();
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("Eroarea " + e.getMessage());
			}
		//else produce ceva	
		buffer = value;
		disponibil = true;
		notify();
	}
	
	public synchronized int consuma(){
		if (!disponibil)
			try {
				wait();
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("Message = " + e.getMessage());
			}
		disponibil = false;
		notify();
		return buffer;
	}
	
}
