package upo.battleship.peretto;

import java.util.Arrays;
import java.util.Observable;

public abstract class GridModel extends Observable implements GridShipsModel {
	
	protected CellStatus[][] grid;
	protected int gridRows, gridCols;
	
	public GridModel(int rows, int cols) {
		this.grid = new CellStatus[rows][cols];
		this.gridRows = rows;
		this.gridCols = cols;
		
		for(CellStatus[] row : this.grid) {
			Arrays.fill(row, CellStatus.CELL_EMPTY);
		}
	}
	
	public void placeShip(int row, int col, ShipOrientation orientation, int dim) throws IndexOutOfBoundsException {
		
		/*this.gridRows = grid.length;
		this.gridCols = grid[0].length;*/
		
		if(row < 0 || row >= gridRows || col < 0 || col >= gridCols) {
			throw new IndexOutOfBoundsException("La cella non esiste all'interno della griglia");
		}
		
		switch(orientation) {//	Controllo se la nave pu� essere posizionata all'�nterno della griglia
			case HORIZONTAL: {
				if(gridCols < col+dim) {
					throw new IndexOutOfBoundsException("Impossibile posizionare nave, esce dalla mappa di gioco");
				}
				int i = dim, c = col;
				while(i > 0){
					if(this.grid[row][c] != CellStatus.CELL_EMPTY) {
						throw new IndexOutOfBoundsException("Impossibile posizionare nave cella gi� occupata");
					}
					i--;
					c++;
				}
			} break;
			
			case VERTICAL: {
				if(gridRows < row+dim) {
					throw new IndexOutOfBoundsException("Impossibile posizionare nave, esce dalla mappa di gioco");
				}
				int i = dim, r = row;
				while(i > 0){
					if(this.grid[row][r] != CellStatus.CELL_EMPTY) {
						throw new IndexOutOfBoundsException("Impossibile posizionare nave cella gi� occupata");
					}
					i--;
					r++;
				}
			} break;
		}
		
		while(dim > 0) {//	posiziono la nave all'interno della griglia
			
			if(orientation == ShipOrientation.HORIZONTAL) {
				this.grid[row][col] = CellStatus.CELL_SHIP;
				col++;
			}
			else {
				this.grid[row][col] = CellStatus.CELL_SHIP;
				row++;
			}
			
			dim--;
		}
		this.setChanged();
		this.notifyObservers();
		
	}
	
	public void hitCell(int row, int col) throws IndexOutOfBoundsException {
		
		if(row < 0 || row >= this.gridRows || col < 0 || col >= this.gridCols) {
			throw new IndexOutOfBoundsException("La cella non esiste nella mappa di gioco");
		}
		
		switch(this.grid[row][col]) {
			case CELL_EMPTY: this.grid[row][col] = CellStatus.CELL_EMPTY_HIT; break;
			case CELL_SHIP: this.grid[row][col] = CellStatus.CELL_SHIP_HIT; break;
			default:
				break;
		}
		
		CellStatus[][] filteredGrid = FilterGrid();
		
		this.setChanged();
		this.notifyObservers(filteredGrid);
		
	}
	
	/**
	 * Filtra la griglia nascondendo il posizionamento delle navi non colpite,
	 * 
	 * @return una nuova griglia filtrata
	 * */
	private CellStatus[][] FilterGrid() {
		
		CellStatus[][] g = new CellStatus[this.gridRows][this.gridCols];
		
		for(int i = 0; i < this.gridRows; i++) {
			for(int j = 0; j < this.gridCols; j++){
				
				switch(this.grid[i][j]){
					case CELL_EMPTY_HIT: g[i][j] = this.grid[i][j]; break;
					case CELL_SHIP_HIT: g[i][j] = this.grid[i][j]; break;
					default:
						break;
				}
			}
		}
		
		return g;
	}
	
	/*
	 * DEBUG USE ONLY
	 * */
	protected void printGrid(String gridName, CellStatus[][] grid) {
		
		System.out.println("Stato griglia: "+gridName);
		
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				System.out.print(grid[i][j]+" | ");
			}
			System.out.println();
		}
		
	}
	
}
