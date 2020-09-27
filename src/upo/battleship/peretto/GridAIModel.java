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
		
	private int[] generaCellaCasuale() {
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
	
	class AI {
		
		private boolean naveIndividuata;
		private int[] cellaConNavePrecColpita;
		private int[] cellaConAcquaPrecColpita;
		private int[] primaCellaColpita;
		private int[] cellaPrecColpita;
		private ShipOrientation orientamento;
		private int estrNaveInd;
		private boolean affondata;
		private int dimGrid;
		
		public AI(int dimGrid){
			this.init();
			this.dimGrid = dimGrid;
		}
		
		public void init() {
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
		
		public int[] getCellaConNavePrecColpita() {
			return cellaConNavePrecColpita;
		}

		public void setCellaConNavePrecColpita(int[] cellaConNavePrecColpita) {
			this.cellaConNavePrecColpita = cellaConNavePrecColpita;
		}

		public int[] getCellaConAcquaPrecColpita() {
			return cellaConAcquaPrecColpita;
		}

		public void setCellaConAcquaPrecColpita(int[] cellaConAcquaPrecColpita) {
			this.cellaConAcquaPrecColpita = cellaConAcquaPrecColpita;
		}

		public int[] getPrimaCellaColpita() {
			return primaCellaColpita;
		}

		public void setPrimaCellaColpita(int[] primaCellaColpita) {
			this.primaCellaColpita = primaCellaColpita;
		}

		public int[] getCellaPrecColpita() {
			return cellaPrecColpita;
		}

		public void setCellaPrecColpita(int[] cellaPrecColpita) {
			this.cellaPrecColpita = cellaPrecColpita;
		}

		public ShipOrientation getOrientamento() {
			return orientamento;
		}

		public void setOrientamento(ShipOrientation orientamento) {
			this.orientamento = orientamento;
		}

		public int getEstrNaveInd() {
			return estrNaveInd;
		}

		public void setEstrNaveInd(int estrNaveInd) {
			this.estrNaveInd = estrNaveInd;
		}

		public boolean isAffondata() {
			return affondata;
		}

		public void setAffondata(boolean affondata) {
			this.affondata = affondata;
		}

		public void setNaveIndividuata(boolean naveIndividuata) {
			this.naveIndividuata = naveIndividuata;
		}
		
		public boolean isNaveIndividuata() {
			return naveIndividuata;
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
	private stateLevel stato;
	
	private enum stateLevel{
		NAVE_NON_IND,
		CERCO_ORIENTAMENTO,
		TROVATO_ORIENTAMENTO,
		CERCO_ESTREMITA
	}
	
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
		stato = stateLevel.NAVE_NON_IND;
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
			
			return cella;
		}
		else {
			if(orientamento != null){
				
				switch(orientamento) {
					case VERTICAL: {
						
						if(stato == stateLevel.CERCO_ESTREMITA){
							if(
								GridModel.getCellValue(enemyGrid, cellaPrecColpita[0], cellaPrecColpita[1]) == null ||
								GridModel.getCellValue(enemyGrid, cellaPrecColpita[0], cellaPrecColpita[1]) == CellStatus.CELL_EMPTY_HIT
							){
								estrNaveInd += 1;
							}
						}
						else{
							
						}
						
					} break;
					case HORIZONTAL: {
						
						if(stato == stateLevel.CERCO_ESTREMITA){
							if(
								GridModel.getCellValue(enemyGrid, cellaPrecColpita[0], cellaPrecColpita[1]) == null ||
								GridModel.getCellValue(enemyGrid, cellaPrecColpita[0], cellaPrecColpita[1]) == CellStatus.CELL_EMPTY_HIT
							){
								estrNaveInd += 1;
							}
						}
						
						if(estrNaveInd == 0){
							
							if(
								GridModel.getCellValue(enemyGrid, cellaConNavePrecColpita[0], cellaConNavePrecColpita[1]+1) == null ||
								GridModel.getCellValue(enemyGrid, cellaConNavePrecColpita[0], cellaConNavePrecColpita[1]+1) == CellStatus.CELL_EMPTY_HIT
							){
								estrNaveInd += 1;
								if(
									GridModel.getCellValue(enemyGrid, primaCellaColpita[0], primaCellaColpita[1]-1) == null ||
									GridModel.getCellValue(enemyGrid, primaCellaColpita[0], primaCellaColpita[1]-1) == CellStatus.CELL_EMPTY_HIT
								){
									estrNaveInd += 1;
									affondata = true;
									//----------------------- Azzero lo stato
									return generaCellaCasuale();
								}
								else{
									
								}
							}
							
						}
						else{
							
						}
						
					} break;
				}
				
			}
			else{
				
				CellStatus[] celleAdj = getCellAdj(cellaConNavePrecColpita[0], cellaConNavePrecColpita[1]);
				
				if(stato == stateLevel.CERCO_ORIENTAMENTO){
					
					CellStatus[] statoCelle = getCellAdj(cellaConNavePrecColpita[0], cellaConNavePrecColpita[1]);
					
					if(
						(statoCelle[0] == null || statoCelle[0] == CellStatus.CELL_EMPTY_HIT) && 
						(statoCelle[2] == null || statoCelle[2] == CellStatus.CELL_EMPTY_HIT)
					){
						orientamento = ShipOrientation.HORIZONTAL;
						
						if(statoCelle[1] == null || statoCelle[1] == CellStatus.CELL_EMPTY_HIT){
							estrNaveInd += 1;
						}
					}
					else if(
							(statoCelle[1] == null || statoCelle[1] == CellStatus.CELL_EMPTY_HIT) && 
							(statoCelle[3] == null || statoCelle[3] == CellStatus.CELL_EMPTY_HIT)
						){
						
						orientamento = ShipOrientation.VERTICAL;
						
						if(statoCelle[0] == null || statoCelle[0] == CellStatus.CELL_EMPTY_HIT){
							estrNaveInd += 1;
						}
					}
					
					if(orientamento == null){
						if(
							(cellaConNavePrecColpita[0] != primaCellaColpita[0]) ||
							(cellaConNavePrecColpita[1] != primaCellaColpita[1])
						){
							if(cellaConNavePrecColpita[0] != primaCellaColpita[0]){
								orientamento = ShipOrientation.VERTICAL;
							}
							else if(cellaConNavePrecColpita[1] != primaCellaColpita[1]){
								orientamento = ShipOrientation.HORIZONTAL;
							}
						}
					}
					
				}
				
				for(int i = 0; i < celleAdj.length; i++){
					if(celleAdj[i] == CellStatus.CELL_EMPTY){
						int[] cella = new int[2];
						switch(i) {
						
							case 0: {// cella Nord
								cella[0] = cellaConNavePrecColpita[0] - 1;
								cella[1] = cellaConNavePrecColpita[1];
							} break;
							
							case 1: {// cella Est
								cella[0] = cellaConNavePrecColpita[0];
								cella[1] = cellaConNavePrecColpita[1] + 1;
							} break;
							
							case 2: {// cella Sud
								cella[0] = cellaConNavePrecColpita[0] + 1;
								cella[1] = cellaConNavePrecColpita[1];
							} break;
							
							case 3: {// cella Ovest
								cella[0] = cellaConNavePrecColpita[0];
								cella[1] = cellaConNavePrecColpita[1] - 1;
							} break;
							
						}
						
						cellaPrecColpita[0] = cella[0];
						cellaPrecColpita[1] = cella[1];
						
						stato = stateLevel.CERCO_ORIENTAMENTO;
						
						return cella;
					}
				}
				
			}
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
	
	/*
	 * Restituisce lo stato delle 4 celle adiacenti alla cella data (Nord, Est, Sud, Ovest)
	 * 
	 * @param r riga cella
	 * @param c colonna cella
	 * 
	 * @return vettore contenente stato 4 celle adiacenti
	 * */
	private CellStatus[] getCellAdj(int r, int c) {
		
		CellStatus[] cAdj = new CellStatus[4];
		
		cAdj[0] = GridModel.getCellValue(enemyGrid, r-1, c);
		cAdj[1] = GridModel.getCellValue(enemyGrid, r, c+1);
		cAdj[2] = GridModel.getCellValue(enemyGrid, r+1, c);
		cAdj[3] = GridModel.getCellValue(enemyGrid, r, c-1);
		
		return cAdj;
		
	}
	
}