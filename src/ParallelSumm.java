import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class ParallelSumm{

	private int[] array;
	private int[] summ;
	private int id;
	
	public ParallelSumm(int[] array){
		this.array = array;
		this.summ = new int[array.length];
		for (int i = 0; i < array.length; ++i)
			summ[i] = array[i];

	}
	
	public int calculate(){
		
		Thread[] threads = new Thread[array.length];
		CyclicBarrier barrier = new CyclicBarrier(array.length);
		
		for (int round = 0; round <= (int)Math.round(Math.log((int)array.length)); ++round){	
			
			for (int i = 0 ; i < array.length; ++i){
				threads[i] = new SummThread(i, round, array, summ, barrier);
				threads[i].start();
			}
			
			for (int i = 0; i < array.length; ++i){
				try {
					threads[i].join();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return summ[array.length - 1];
	}
	
	private static class SummThread extends Thread{
		
		private int id;
		private int[] array;
		private int[] summ;
		private CyclicBarrier barrier;
		private int round;
		
		public SummThread(int id, int round, int[] array, int[] summ, CyclicBarrier barrier){
			this.id = id;
			this.array = array;
			this.summ = summ;
			this.barrier = barrier;
			this.round = round;
		}
		
		public void run(){
				
			if (((id + 1) % Math.pow(2, round + 1)) == 0){
				summ[id] += summ[id - (int)Math.pow(2, round)];
				System.out.println("Thread " + id + " calculating summ for position " + id + " and " + (id - (int)Math.pow(2, round)) + " which is " + summ[id]);
			}
			try { 
				barrier.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
};
