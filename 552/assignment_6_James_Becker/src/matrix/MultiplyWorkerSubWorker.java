package matrix;

import java.util.concurrent.CountDownLatch;

public class MultiplyWorkerSubWorker implements Runnable {

	 int[][] A,B,C;
	 int m;
	 int n;
	CountDownLatch thisLatch;
	
	MultiplyWorkerSubWorker(int[][] J,int[][] K, int[][] Z,int i, int j ,CountDownLatch latch){
		this.A = J;
		this.B = K;
		this.C = Z;
		this.m = i;
		this.n = j;
		thisLatch = latch;
	}
	
	@Override
	public void run() {
		int[] first = new int[A[1].length];
		int[] second = new int[B.length];
		
		for(int i = 0; i < B.length;i++){
			
			second[i] = B[i][n];
		}
		
		for(int x =0;x<A[1].length;x++){
			
			first[x] = A[m][x];
		}
		
		int sum = 0;
		for(int R = 0;R<first.length;R++){
			
			sum = sum + (first[R]*second[R]);
		}
		
		C[m][n] = sum;
		thisLatch.countDown();
	}

	
	
}
