package upo.observer;

import java.util.Arrays;
import java.util.Random;

public class GridByRefTest {

	private int[][] m;
	
	public GridByRefTest() {
		m = new int[5][5];
		
		for(int[] r : m){
			Arrays.fill(r, 1);	
		}
	}
	
	public void crea() {
		
		AnotherClass ac = new AnotherClass(this.m);
		
		ac.printgrid();
		
		this.m[0][0] = 256;
		
		ac.printgrid();
		
	}
	
	public void GeneraCasuale() {
		long l = 1;
		Random r = new Random(1);
		
		for(int i = 0; i < 100; i++){
			
			if(i%2 == 0)
				System.out.println();
			System.out.println(r.nextInt(10));
			
		}
	}
	
	public static void main(String[] args) {
		GridByRefTest gbr = new GridByRefTest();
		
		gbr.crea();
		gbr.GeneraCasuale();
	}

}

class AnotherClass {
	
	private int[][] grid;
	
	public AnotherClass(int[][] g) {
		this.grid = g;
	}
	
	public void printgrid() {
		
		for(int i = 0; i < this.grid.length; i++){
			for(int j = 0; j < this.grid[0].length; j++)
				System.out.print(this.grid[i][j]);
			
			System.out.println();
		}
		
	}
}
