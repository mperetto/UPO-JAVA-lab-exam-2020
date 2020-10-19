package upo.observer;

import java.util.Observable;

public class ObserverModel extends Observable {
	
	/*
	 * grid contiene lo stato della griglia della partita
	 * 
	 * si utilizzano diversi valori interi per indicare lo stato delle celle:
	 * 
	 * 	-	0	->	cella vuota non colpita
	 * 	-	1	->	cella vuota colpita
	 * 	-	2	->	cella con nave non colpita
	 * 	-	3	->	cella con nave colpita
	 * 
	 * */
	private int[][] grid;
	
	public ObserverModel() {
		this.grid = new int[10][10];
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				this.grid[i][j] = 0;
			}
		}
	}
	
	public void press(int r, int c) {
		this.grid[r][c] = 1;
		this.setChanged();
		this.notifyObservers(grid);
	}
	
	public void changeGrid() {
		this.grid[1][1] = 1;
		this.grid[1][2] = 3;
		this.grid[1][3] = 3;
		this.grid[5][1] = 2;
		this.grid[5][2] = 2;
		this.grid[5][3] = 2;
		this.grid[4][3] = 1;
		this.setChanged();
		this.notifyObservers(grid);
	}
}
