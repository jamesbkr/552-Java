package main;


import java.util.concurrent.LinkedBlockingQueue;

import threads.Merger;
import threads.MultiplyThread;
import threads.OutputThread;

public class mainProgram {

	public static void main(String[] args) {
		
		//Links between threads
		LinkedBlockingQueue<Integer> to2 			= new LinkedBlockingQueue<Integer>();
		LinkedBlockingQueue<Integer>  to3 			= new LinkedBlockingQueue<Integer>();
		LinkedBlockingQueue<Integer>  to5 			= new LinkedBlockingQueue<Integer>();
		LinkedBlockingQueue<Integer>  toMerge2 		= new LinkedBlockingQueue<Integer>();
		LinkedBlockingQueue<Integer>  toMerge3 		= new LinkedBlockingQueue<Integer>();
		LinkedBlockingQueue<Integer>  toMerge5 		= new LinkedBlockingQueue<Integer>();
		LinkedBlockingQueue<Integer>  mergeToPrint  = new LinkedBlockingQueue<Integer>();
		try{
			//seed with 1
			mergeToPrint.put(1);
		}catch(Exception ex){System.out.println(ex);}
		
		// make the runnables and wire up the information pipes
		MultiplyThread two = new MultiplyThread(to2,toMerge2,2);
		MultiplyThread three = new MultiplyThread(to3,toMerge3,3);
		MultiplyThread five = new MultiplyThread(to5,toMerge5,5);
		Merger merger = new Merger(toMerge2,toMerge3,toMerge5,mergeToPrint);
		OutputThread output = new OutputThread(to2, to3, to5, mergeToPrint);
		
		
		//start the threads
		new Thread(two).start();
		new Thread(three).start();
		new Thread(five).start();
		new Thread(merger).start();
		new Thread(output).start();
		
		
		
		

	}

}
