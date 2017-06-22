package threads;
/*
 * Prints to console and redistributes the new number
 * 
 */
import java.util.concurrent.LinkedBlockingQueue;

public class OutputThread implements Runnable {
	
	LinkedBlockingQueue<Integer> to2 ;
	LinkedBlockingQueue<Integer>  to3 ;
	LinkedBlockingQueue<Integer>  to5 ;
	LinkedBlockingQueue<Integer> input;
	Integer counter = 0;
	
	//constructor
	public OutputThread(LinkedBlockingQueue<Integer> to2,
						LinkedBlockingQueue<Integer> to3,
						LinkedBlockingQueue<Integer> to5,
						LinkedBlockingQueue<Integer> input){
		this.to2 = to2;
		this.to3 = to3;
		this.to5 = to5;
		this.input = input;
	}
	
	
	public void run(){
		while(true){
		 Integer i = input.poll();
	
		 //only processes if there is a number in the input queue
		 if (i!=null){
			 System.out.println(i);
			try{
				//send the multipliers the new numbers
			 to2.put(i);
			 to3.put(i);
			 to5.put(i);
			 //keep track of the number of numbers
			 counter ++;
			}catch(Exception ex){System.out.println(ex);}
		 }
		 //if there has been 60 numbers stop the system. 
		 if (counter>60){
			System.exit(55);
		 }
			
		}
	}
}
