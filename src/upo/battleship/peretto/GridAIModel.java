package upo.battleship.peretto;

import java.lang.Math;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer; 

public class GridAIModel extends GridModel implements Observer {
	
	private CellStatus[][] enemyGrid;
	private GridPlayerModel playerModel;

	public GridAIModel(int rows, int cols, GridPlayerModel playerModel) {
		super(rows, cols);
		this.enemyGrid = new CellStatus[rows][cols];
		this.playerModel = playerModel;
		this.playerModel.addObserver(this);
	}
	
	/**
	 * Restituisce le cordinate della cella da colpire
	 * 
	 * @return cell, cella da colpire all'interno della griglia del player
	 * 
	 * */
	public int[] newMove() {
		
		int range = this.gridCols; // numeri da 0 a numero di colonne/righe della matrice
		int[] cell = new int[2];
		
		do{
			cell[0] = (int)Math.random()*range;
			cell[1] = (int)Math.random()*range;
		}while(this.enemyGrid[cell[0]][cell[1]] != CellStatus.CELL_EMPTY);		
		
		return cell;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		CellStatus[][] g = (CellStatus[][])arg1;
		
		for(int i = 0; i < this.gridRows; i++){
			for(int j = 0; j < this.gridCols; j++){
				this.enemyGrid[i][j] = g[i][j];
			}
		}
	}

}

class AI {
	
	CellStatus[][] enemyGrid;
	private boolean naveIndividuata;
	private int[] cellaConNavePrecColpita;
	private int[] cellaConAcquaPrecColpita;
	private int[] primaCellaColpita;
	private int[] cellaPrecColpita;
	private ShipOrientation orientamento;
	private int estrNaveInd;
	private boolean affondata;
	
	public AI(CellStatus[][] g) {
		enemyGrid = g;
		naveIndividuata = false;
		cellaConNavePrecColpita = new int[2];
		Arrays.fill(cellaConNavePrecColpita, -1);
		cellaConAcquaPrecColpita = new int[2];
		Arrays.fill(cellaConAcquaPrecColpita, -1);
		primaCellaColpita = new int[2];
		Arrays.fill(primaCellaColpita, -1);
		cellaPrecColpita = new int[2];
		Arrays.fill(cellaPrecColpita, -1);
		orientamento = null;
		estrNaveInd = 0;
		affondata = false;
	}
	
	public int[] colpisci() {
		
		if(cellaPrecColpita[0] != -1){
			
			if(enemyGrid[cellaPrecColpita[0]][cellaPrecColpita[1]] == CellStatus.CELL_EMPTY_HIT){
				
				cellaConAcquaPrecColpita[0] = cellaPrecColpita[0];
				cellaConAcquaPrecColpita[1] = cellaPrecColpita[1];
				
			}
			else if(enemyGrid[cellaPrecColpita[0]][cellaPrecColpita[1]] == CellStatus.CELL_SHIP_HIT){
				
				if(primaCellaColpita[0] == -1){
					primaCellaColpita[0] = cellaPrecColpita[0];
					primaCellaColpita[1] = cellaPrecColpita[1];
					
					naveIndividuata = true;
				}
				
				cellaConNavePrecColpita[0] = cellaPrecColpita[0];
				cellaConNavePrecColpita[1] = cellaPrecColpita[1];
				
			}
			
		}
		
		if(!naveIndividuata) {
			int[] cella = generaCellaCasuale();
			
			cellaPrecColpita[0] = cella[0];
			cellaPrecColpita[1] = cella[1];
			
		}
		else {
			
		}
		
		return null;
	}
	
	private int[] generaCellaCasuale() {
		
		int range = enemyGrid.length; // numeri da 0 a numero di colonne/righe della matrice
		int[] cell = new int[2];
		
		do{
			cell[0] = (int)Math.random()*range;
			cell[1] = (int)Math.random()*range;
		}while(this.enemyGrid[cell[0]][cell[1]] != CellStatus.CELL_EMPTY);		
		
		return cell;
	}
	
}