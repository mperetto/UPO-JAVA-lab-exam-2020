package upo.battleship.peretto;

import java.util.Observable;
import java.util.Observer;

public class GridPlayerModel extends GridModel implements Observer{
	
	private CellStatus[][] enemyGrid;
	private GridAIModel aiModel;
	
	/**
	 * Inizializza una nuova griglia
	 * */
	public GridPlayerModel(int rows, int cols, GridAIModel aiModel) {
		super(rows, cols);
		this.enemyGrid = new CellStatus[rows][cols];
		this.aiModel = aiModel;
		this.aiModel.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		CellStatus[][] g = (CellStatus[][])arg;
		
		for(int i = 0; i < this.gridRows; i++){
			for(int j = 0; j < this.gridCols; j++){
				this.enemyGrid[i][j] = g[i][j];
			}
		}
		
	}

}
