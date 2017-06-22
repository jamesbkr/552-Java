package main;

import java.util.Arrays;

import matrix.MatMath;
import matrix.MatrixFactory;
import matrix.ParralelMatrixCompute;
import matrix.StreamMatrixCompute;

public class MainRunClassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid = new int[2000][3000];
		int[][] gridb = new int[3000][3000];
		int[][]gridc = new int[2000][3000];
		
		
		for (int i=0;i<grid.length;i++){
			for (int d=0;d<grid[i].length;d++){
				grid[i][d]=(int)(Math.random()*10);
			}
		}
		
		for (int i=0;i<gridb.length;i++){
			for (int d=0;d<gridb[i].length;d++){
				gridb[i][d]=(int)(Math.random()*10);
			}
		}
		
		final MatrixFactory factory = ParralelMatrixCompute.factory;
		final MatrixFactory factory2 = StreamMatrixCompute.factory;
		final MatMath Parralel = factory.build();
		final MatMath Stream = factory2.build();
		for(int f = 0; f<grid.length;f++){
		System.out.println(Arrays.toString(grid[f]));
		}
		System.out.println("");
		for(int s=0;s<gridb.length;s++){
		System.out.println(Arrays.toString(gridb[s]));
		}
		System.out.println("");
		Parralel.multiply(grid, gridb, gridc);
		
		
		

	}

}
