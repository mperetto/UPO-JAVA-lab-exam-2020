package upo.battleship.peretto;

import java.util.Arrays;
import java.util.Observable;

public abstract class GridModel extends Observable implements GridShipsModel {
	
	protected CellStatus[][] grid;
	protected int gridRows, gridCols;
	private int celleConPartiNave;
	
	/**
	 * Genera una nuova mappa di gioco
	 * 
	 * @param rows - totale righe matrice
	 * @param cols - totale colonne matrice
	 * */
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
		
		if(row < 0 || row >= gridRows || col < 0 || col >= gridCols) {
			throw new IndexOutOfBoundsException("La cella non esiste all'interno della griglia");
		}
		
		switch(orientation) {//	Controllo se la nave può essere posizionata all'ìnterno della griglia
			case HORIZONTAL: {
				if(gridCols < (col+dim)) {
					throw new IndexOutOfBoundsException("Impossibile posizionare nave, esce dalla mappa di gioco");
					
				}
				int i = dim, c = col;
				while(i > 0){
					if(this.grid[row][c] != CellStatus.CELL_EMPTY || !isNeighborCellEmpty(this.grid, row, c)) {
						throw new IndexOutOfBoundsException("Impossibile posizionare nave, cella già occupata");
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
					if(this.grid[r][col] != CellStatus.CELL_EMPTY || !isNeighborCellEmpty(this.grid, r, col)) {
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
	
	/**
	 * Restituisce il valore della cella scelta
	 * 
	 * @param grid - la griglia da analizzare
	 * @param r - la riga della griglia
	 * @param c- la colonna della griglia
	 * */
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
	
	/**
	 * {@inheritDoc}
	 * */
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

		this.setChanged();
		this.notifyObservers(this.grid);
		
		try {
			this.newMove();
		}
		catch(UnsupportedOperationException e) {
			return;
		}
		
	}
	
	/**
	 * Il metodo viene richiamato dopo l'esecuzione di hitCell.
	 * Passa il turno all'AI permettendo la scelta della cella da colpire.
	 * 
	 * @return int[] riga e colonna della cella colpita
	 * 
	 * @throws UnsupportedOperationException - nel caso la sua esecuzione non sia prevista
	 * */
	public abstract int[] newMove() throws UnsupportedOperationException;
	
	/**
	 * Verifica se nella griglia di gioco siano ancora presenti porzioni di nave non colpite
	 * 
	 * @return <code>true</code> se tutte le navi sono satate affondate <code>false</code> altrimenti
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
				if(grid[i][j] == CellStatus.CELL_EMPTY)
					System.out.print(0+" | ");
				else
					System.out.print(1+" | ");
			}
			System.out.println();
		}
		
	}
	
}
