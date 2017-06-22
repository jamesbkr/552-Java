package matrix;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class AddWorkerSubWorker implements Runnable {

		 int[][] A,B,C;
		 int m;
		 int n;
		CountDownLatch thisLatch;
		
		AddWorkerSubWorker(int[][] J,int[][] K, int[][] Z,int i, int j ,CountDownLatch latch){
			this.A = J;
			this.B = K;
			this.C = Z;
			this.m = i;
			this.n = j;
			thisLatch = latch;
		}


	@Override
	public void run() {
		C[m][n] = A[m][n]+B[m][n];		
		thisLatch.countDown();
		
	}

}
