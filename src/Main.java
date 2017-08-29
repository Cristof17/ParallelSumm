
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final ConsumerProducerBuffer producatorConsumator = new ConsumerProducerBuffer();
		
		// TODO Auto-generated method stub
		Runnable producerRunnable = new Runnable(){
			public void run(){
				for (int i = 0; i < 100; ++i){
					producatorConsumator.produce(i);
					System.out.println("Am produs " + i);
				}
			}
		};
		
		Runnable consumatorRunnable = new Runnable(){
			public void run(){
				for(int i = 0; i < 100; ++i){
					int value = producatorConsumator.consuma();
					System.out.println("Am consumat " + value);
				}
			}
		};
		
//		Thread producator = new Thread(producerRunnable);
//		Thread consumator = new Thread(consumatorRunnable);
//		
//		producator.start();
//		consumator.start();
		
		int[] values = new int[] {10, 20, 30, 40, 50, 60, 70, 80};
		ParallelSumm summ = new ParallelSumm(values);
		int suma = summ.calculate();
		System.out.println("Suma = " + suma);
		
	}

}
