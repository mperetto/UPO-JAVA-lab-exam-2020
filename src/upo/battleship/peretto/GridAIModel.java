package upo.battleship.peretto;

import java.lang.Math;
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
	public int[] NewMove() {
		
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
