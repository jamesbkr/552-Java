package threads;
/*
 * Multiplier class that gets passed the value it needs to multiply by.
 * Also has one input and one output queue.
 * 
 */

import java.util.concurrent.LinkedBlockingQueue;

public class MultiplyThread implements Runnable {
	LinkedBlockingQueue<Integer> input;
	LinkedBlockingQueue<Integer> output;
	int multiplier;
	
	
	//constructor
	public MultiplyThread(LinkedBlockingQueue<Integer> i, LinkedBlockingQueue<Integer> o, int c){
		input = i;
		output = o;
		multiplier = c;
	}
	
	
	//runner that runs infinitely
	public void run(){
		
		while(true){
			
			//get a value from the input queue
			Integer in = input.poll();
			if(in!=null){
				try {
					//put the value in output queue
					output.put(in*multiplier);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	

}
