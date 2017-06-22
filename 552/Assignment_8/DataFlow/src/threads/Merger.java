package threads;
/*
 * merges the inputs and produces an output
 * 
 */
import java.util.concurrent.BlockingQueue;

public class Merger implements Runnable {
	BlockingQueue<Integer> input1;
	BlockingQueue<Integer> input2;
	BlockingQueue<Integer> input3;
	BlockingQueue<Integer> output;
	
	//constructor
	public Merger(BlockingQueue<Integer> input1,
				  BlockingQueue<Integer> input2,
				  BlockingQueue<Integer> input3,
				  BlockingQueue<Integer> output)
	{
		this.input1 = input1;
		this.input2 = input2;
		this.input3 = input3;
		this.output = output;
		
		
	}
	public void run(){
		
		while(true){
			//look at the next numbers and set value to max int value
			Integer two = input1.peek();
			Integer three = input2.peek();
			Integer five = input3.peek();
			Integer min = Integer.MAX_VALUE;
		
		// only process numbers if there is a number in every queue.
		if((two!=null)&&(three!=null)&&(five!=null)){	
			
			//find the minimum value
			if(two<min){
				min =two;
			}
			 if(three<min){
				min=three;
			}
			if(five<min){
				min = five;
			}
			 try {
				 //remove the minimum value from the queues
				 if(min.equals(two)){
					 input1.remove();
				 }if(min.equals(three)){
					 input2.remove();
				 }if(min.equals(five)){
					 input3.remove();
				 }
				//send the minimum value to the output thread
				output.put(min);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
			
			
			
		}
		
		
	}
	
	
	
}
