package matrix;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public interface ParralelMatrixCompute{
	public static final MatrixFactory factory = new ParralelMatrixComputeFactoryImpl();
	
}

class ParralelMatrixComputeFactoryImpl implements MatrixFactory{
	
	
	@Override
	public MatMath build() {
		
		return new ParralelMatrixComputeImpl( );
	}
	
}


class ParralelMatrixComputeImpl implements MatMath {

	
	
	public ParralelMatrixComputeImpl() {};

	@Override
	public void multiply(int[][] A, int[][] B, int[][] C) {
		// TODO Auto-generated method stub
		int count = A.length * B[1].length;
		final CountDownLatch latch = new CountDownLatch(count);
		
		for (int i=0;i<B[1].length;i++){
			for(int g=0;g<A.length;g++){
				MultiplyWorkerSubWorker worker = new MultiplyWorkerSubWorker( A,  B, C, g,i,latch);
				worker.run();
			}
		}
		try {
			latch.await();
			print(C);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(int[][] A, int[][] B, int[][] C) {
		int count = A.length * A[1].length;
		final CountDownLatch latch = new CountDownLatch(count);
		
		for (int i=0;i<A.length;i++){
			for(int g=0;g<A[i].length;g++){
				AddWorkerSubWorker worker = new AddWorkerSubWorker(A,B,C, i,g,latch);
				worker.run();
			}
		}	
		try {
			latch.await();
			print(C);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void print(int[][] A) {
		
		for(int i=0;i<A.length;i++){
			System.out.println(Arrays.toString(A[i]));
		}
		// TODO Auto-generated method stub
		
	}

}

