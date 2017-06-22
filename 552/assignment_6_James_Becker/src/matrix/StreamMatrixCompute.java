package matrix;

import java.util.Arrays;
import java.util.stream.IntStream;

public interface StreamMatrixCompute{
	public static final MatrixFactory factory = new StreamMatrixComputeFactoryImpl();
	
}

class StreamMatrixComputeFactoryImpl implements MatrixFactory{

	@Override
	public MatMath build() {
		
		return new StreamMatrixComputeImpl( );
	}
	
}



class StreamMatrixComputeImpl implements MatMath {

	public StreamMatrixComputeImpl() {
		
	}

	@Override
	public void multiply(int[][] A, int[][] B, int[][] C) {

		C = Arrays.stream(A).parallel().map(r->IntStream.range(0, B[0].length).parallel().map(f -> 
		IntStream.range(0,B.length).parallel().map(k -> r[k]*B[k][f]).sum()).toArray()).toArray(int[][]::new);
		print(C);
	}

	@Override
	public void add(int[][] A, int[][] B, int[][] C) {
			IntStream.range(0,A.length).parallel().forEach(i -> IntStream.range(0,A.length).parallel().forEach(j -> C[i][j] = A[i][j]+B[i][j]));
			
			print(C);
	}

	@Override
	public void print(int[][] A) {
		for(int i=0;i<A.length;i++){
			
			System.out.println(Arrays.toString(A[i]));
		}
	}

}
