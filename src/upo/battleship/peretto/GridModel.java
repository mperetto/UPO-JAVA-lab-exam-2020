package upo.battleship.peretto;

import java.util.Arrays;
import java.util.Observable;

public abstract class GridModel extends Observable implements GridShipsModel {
	
	protected CellStatus[][] grid;
	private int gridRows, gridCols;
	
	public GridModel(int rows, int cols) {
		this.grid = new CellStatus[rows][cols];
		this.gridRows = rows;
		this.gridCols = cols;
		
		for(CellStatus[] row : this.grid) {
			Arrays.fill(row, CellStatus.CELL_EMPTY);
		}
	}
	
	public void placeShip(int row, int col, ShipOrientation orientation, int dim) throws IndexOutOfBoundsException {
		
		this.gridRows = grid.length;
		this.gridCols = grid[0].length;
		
		if(row < 0 || row >= gridRows || col < 0 || col >= gridCols) {
			throw new IndexOutOfBoundsException("La cella non esiste all'interno della griglia");
		}
		
		while(dim > 0) {
			
			if(this.grid[row][col] != 0) {
				throw new IndexOutOfBoundsException("Impossibile posizionare nave cella già occupata");
			}
			
			if(orientation == ShipOrientation.HORIZONTAL) {
				col++;
			}
			else {
				row++;
			}
			
			if(row >= gridRows || col >= gridCols) {
				throw new IndexOutOfBoundsException("Impossibile posizionare nave, esce dalla mappa di gioco");
			}
			
			dim--;
		}
		
	}
	
	public void hitCell(int row, int col) throws IndexOutOfBoundsException {
		
	}
	
}
