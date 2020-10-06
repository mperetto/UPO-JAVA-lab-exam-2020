package upo.battleship.peretto;

import java.util.Arrays;
import java.util.Observable;

public abstract class GridModel extends Observable implements GridShipsModel {
	
	protected CellStatus[][] grid;
	protected int gridRows, gridCols;
	private int celleConPartiNave;
	
	public GridModel(int rows, int cols) {
		this.grid = new CellStatus[rows][cols];
		this.gridRows = rows;
		this.gridCols = cols;
		this.celleConPartiNave = 0;
		
		for(CellStatus[] row : this.grid) {
			Arrays.fill(row, CellStatus.CELL_EMPTY);
		}
	}
	
	
	/**
	 * {@inheritDoc}
	 * */	
	public void placeShip(int row, int col, ShipOrientation orientation, int dim) throws IndexOutOfBoundsException {
		
		/*this.gridRows = grid.length;
		this.gridCols = grid[0].length;*/
		
		if(row < 0 || row >= gridRows || col < 0 || col >= gridCols) {
			throw new IndexOutOfBoundsException("La cella non esiste all'interno della griglia");
		}
		
		switch(orientation) {//	Controllo se la nave può essere posizionata all'ìnterno della griglia
			case HORIZONTAL: {
				if(gridCols < (col+dim)) {
					System.out.println("1");
					throw new IndexOutOfBoundsException("Impossibile posizionare nave, esce dalla mappa di gioco");
					
				}
				int i = dim, c = col;
				while(i > 0){
					if(this.grid[row][c] != CellStatus.CELL_EMPTY || !isNeighborCellEmpty(this.grid, row, c)) {
						System.out.println("2");
						throw new IndexOutOfBoundsException("Impossibile posizionare nave, cella già occupata");
					}
					i--;
					c++;
				}
			} break;
			
			case VERTICAL: {
				if(gridRows < row+dim) {
					System.out.println("3");
					throw new IndexOutOfBoundsException("Impossibile posizionare nave, esce dalla mappa di gioco");
				}
				int i = dim, r = row;
				while(i > 0){
					if(this.grid[r][col] != CellStatus.CELL_EMPTY || !isNeighborCellEmpty(this.grid, r, col)) {
						System.out.println("4");
						throw new IndexOutOfBoundsException("Impossibile posizionare nave, cella già occupata");
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
			this.celleConPartiNave++;
			dim--;
		}
		
		this.setChanged();
		this.notifyObservers(this.grid);
		
	}
	
	public static CellStatus getCellValue(CellStatus[][] grid, int r, int c) {
		
		CellStatus value = null;
		
		try{
			value = grid[r][c];
		}
		catch (IndexOutOfBoundsException e){
			return null;
		}
		
		return value;
		
	}
	
	private boolean isNeighborCellEmpty(CellStatus[][] grid, int r, int c) {
		
		CellStatus[] neighborCell = new CellStatus[8];
		
		neighborCell[0] = getCellValue(grid, r-1, c);// Cella in alto
		neighborCell[1] = getCellValue(grid, r-1, c+1);// Cella in alto a destra
		neighborCell[2] = getCellValue(grid, r, c+1);// Cella a destra
		neighborCell[3] = getCellValue(grid, r+1, c+1);// Cella in basso a destra
		neighborCell[4] = getCellValue(grid, r+1, c);// Cella in basso
		neighborCell[5] = getCellValue(grid, r+1, c-1);// Cella in basso a sinistra
		neighborCell[6] = getCellValue(grid, r, c-1);// Cella a sinistra
		neighborCell[7] = getCellValue(grid, r-1, c-1);// Cella in alto a sinistra
		
		for(int i = 0; i < neighborCell.length; i++){
			
			if(neighborCell[i] != null && neighborCell[i] != CellStatus.CELL_EMPTY)
				return false;
			
		}
		
		return true;
		
	}
	
	public void hitCell(int row, int col) throws IndexOutOfBoundsException {
		
		if(row < 0 || row >= this.gridRows || col < 0 || col >= this.gridCols) {
			throw new IndexOutOfBoundsException("La cella non esiste nella mappa di gioco");
		}
		
		switch(this.grid[row][col]) {
			case CELL_EMPTY: this.grid[row][col] = CellStatus.CELL_EMPTY_HIT; break;
			case CELL_SHIP: {
				this.grid[row][col] = CellStatus.CELL_SHIP_HIT;
				this.celleConPartiNave--;
			} break;
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
		
		for(CellStatus[] row : g) {
			Arrays.fill(row, CellStatus.CELL_EMPTY);
		}
		
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
	
	/**
	 * Restituisce il numero di navi nemiche affondate fino a quel momento.
	 * 
	 * @return int il totale delle navi affondate.
	 * */
	public boolean isTutteNaviAffondate() {
		return (this.celleConPartiNave == 0);
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
