package upo.battleship.peretto;

import java.lang.Math;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Random; 

public class GridAIModel extends GridModel implements Observer {
	
	private CellStatus[][] enemyGrid;
	private GridPlayerModel playerModel;
	private AI ai;

	public GridAIModel(int rows, int cols, GridPlayerModel playerModel) {
		super(rows, cols);
		this.enemyGrid = new CellStatus[rows][cols];
		this.playerModel = playerModel;
		this.playerModel.addObserver(this);
		this.ai = new AI(rows);
	}
	
	/**
	 * Restituisce le cordinate della cella da colpire
	 * 
	 * @return cell, cella da colpire all'interno della griglia del player
	 * 
	 * */
	public int[] newMove(long ...seed) {
		
		if(!ai.isNaveIndividuata()){
			
			int[] cellaDaColpire = new int[2];
			cellaDaColpire = generaCellaCasuale();
			playerModel.hitCell(cellaDaColpire[0], cellaDaColpire[1]);
			
			if(this.enemyGrid[cellaDaColpire[0]][cellaDaColpire[1]] == CellStatus.CELL_SHIP_HIT){
				ai.setNaveIndividuata(true);
				ai.setCellaConNavePrecColpita(cellaDaColpire);
				ai.setPrimaCellaColpita(cellaDaColpire);
			}
		}
		else if(ai.getOrientamento() != null){
			//Possiedo l'orientamento
			switch(ai.getOrientamento()){
				case VERTICAL:{
					
					if(ai.getEstrNaveInd() == 0){
						int[] cellaDaColpire = ai.getCellaConNavePrecColpita();
						cellaDaColpire[0] -= 1;
						if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != null && GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != CellStatus.CELL_EMPTY_HIT){
							playerModel.hitCell(cellaDaColpire[0], cellaDaColpire[1]);
							if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) == CellStatus.CELL_EMPTY_HIT){
								ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
							}
							else{
								ai.setCellaConNavePrecColpita(cellaDaColpire);
							}
						}
						else {
							ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
							this.newMove();
						}
					}
					else{
						int[] cellaDaColpire = ai.getPrimaCellaColpita();
						do{
							cellaDaColpire[0] += 1;
						}while(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) == CellStatus.CELL_SHIP_HIT);
						
						if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != null && GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != CellStatus.CELL_EMPTY_HIT){
							playerModel.hitCell(cellaDaColpire[0], cellaDaColpire[1]);
							if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) == CellStatus.CELL_EMPTY_HIT){
								ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
								ai.setAffondata(true);
								ai.init();
							}
							else{
								ai.setCellaConNavePrecColpita(cellaDaColpire);
								ai.setPrimaCellaColpita(cellaDaColpire);
							}
						}
						else {
							ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
							ai.setAffondata(true);
							ai.init();
							this.newMove();
						}
					}
					
				} break;
				case HORIZONTAL: {
					
					if(ai.getEstrNaveInd() == 0){
						int[] cellaDaColpire = ai.getCellaConNavePrecColpita();
						cellaDaColpire[1] += 1;
						if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != null && GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != CellStatus.CELL_EMPTY_HIT){
							playerModel.hitCell(cellaDaColpire[0], cellaDaColpire[1]);
							if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) == CellStatus.CELL_EMPTY_HIT){
								ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
							}
							else{
								ai.setCellaConNavePrecColpita(cellaDaColpire);
							}
						}
						else {
							ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
							this.newMove();
						}
					}
					else{
						int[] cellaDaColpire = ai.getPrimaCellaColpita();
						do{
							cellaDaColpire[0] -= 1;
						}while(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) == CellStatus.CELL_SHIP_HIT);
						
						if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != null && GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != CellStatus.CELL_EMPTY_HIT){
							playerModel.hitCell(cellaDaColpire[0], cellaDaColpire[1]);
							if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) == CellStatus.CELL_EMPTY_HIT){
								ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
								ai.setAffondata(true);
								ai.init();
							}
							else{
								ai.setCellaConNavePrecColpita(cellaDaColpire);
								ai.setPrimaCellaColpita(cellaDaColpire);
							}
						}
						else {
							ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
							ai.setAffondata(true);
							ai.init();
							this.newMove();
						}
					}
					
				} break;
			}
			
		}
		else{
			//Non possiedo l'orientamento lo cerco
			int[] cellaConNavePrecColpita = ai.getCellaConNavePrecColpita();
			CellStatus[] celleAdj = this.getCellAdj(cellaConNavePrecColpita[0], cellaConNavePrecColpita[1]);
			int[] cella = new int[2];
			
			for(int i = 0; i < celleAdj.length; i++){
				if(celleAdj[i] == CellStatus.CELL_EMPTY){
					
					switch(i) {
						case 0: {// cella Nord
							cella[0] = cellaConNavePrecColpita[0] - 1;
							cella[1] = cellaConNavePrecColpita[1];
							
							playerModel.hitCell(cellaConNavePrecColpita[0] - 1, cellaConNavePrecColpita[1]);
							
						} break;
						
						case 1: {// cella Est
							cella[0] = cellaConNavePrecColpita[0];
							cella[1] = cellaConNavePrecColpita[1] + 1;
							
							playerModel.hitCell(cellaConNavePrecColpita[0], cellaConNavePrecColpita[1] + 1);
							
						} break;
						
						case 2: {// cella Sud
							cella[0] = cellaConNavePrecColpita[0] + 1;
							cella[1] = cellaConNavePrecColpita[1];
							
							playerModel.hitCell(cellaConNavePrecColpita[0] + 1, cellaConNavePrecColpita[1]);
							
						} break;
						
						case 3: {// cella Ovest
							cella[0] = cellaConNavePrecColpita[0];
							cella[1] = cellaConNavePrecColpita[1] - 1;
							
							playerModel.hitCell(cellaConNavePrecColpita[0], cellaConNavePrecColpita[1] - 1);
							
						} break;
						
					}
					
					break;
				}
				
				
			}
			celleAdj = this.getCellAdj(cellaConNavePrecColpita[0], cellaConNavePrecColpita[1]);
			if(GridModel.getCellValue(enemyGrid, cella[0], cella[1]) == CellStatus.CELL_SHIP_HIT){
				ai.setCellaConNavePrecColpita(cella);
			}
			
			if(
				(celleAdj[0] == null || celleAdj[0] == CellStatus.CELL_EMPTY_HIT) &&
				(celleAdj[2] == null || celleAdj[2] == CellStatus.CELL_EMPTY_HIT)
			){
				ai.setOrientamento(ShipOrientation.HORIZONTAL);
				if(celleAdj[1] == null || celleAdj[1] == CellStatus.CELL_EMPTY_HIT){
					ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
				}
			}
			else if(
					(celleAdj[1] == null || celleAdj[1] == CellStatus.CELL_EMPTY_HIT) &&
					(celleAdj[3] == null || celleAdj[3] == CellStatus.CELL_EMPTY_HIT)
			){
				ai.setOrientamento(ShipOrientation.VERTICAL);
				if(celleAdj[0] == null || celleAdj[0] == CellStatus.CELL_EMPTY_HIT){
					ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
				}
			}
			
			if(ai.getOrientamento() == null){
				
				int[] cpc = ai.getCellaConNavePrecColpita();
				int[] pcc = ai.getPrimaCellaColpita();
				
				if(
					cpc[0] != -1 &&
					(cpc[0] != pcc[0] || cpc[1] != pcc[1])
				){
					if(cpc[0] != pcc[0]){
						ai.setOrientamento(ShipOrientation.VERTICAL);
					}
					else if(cpc[1] != pcc[1]){
						ai.setOrientamento(ShipOrientation.HORIZONTAL);
					}
				}
				
			}
		}
		
		return null;
	}
	
	private int[] generaCellaCasuale(long ...seed) {
		int range = this.gridCols; // numeri da 0 a numero di colonne/righe della matrice
		int[] cell = new int[2];
		Random rand;
		
		if(seed.length == 0)
			rand = new Random();
		else
			rand = new Random(seed[0]);
		
		do{
			cell[0] = rand.nextInt(range);
			cell[1] = rand.nextInt(range);
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
	
	public CellStatus[] getCellAdj(int r, int c) {
		
		CellStatus[] cAdj = new CellStatus[4];
		
		cAdj[0] = GridModel.getCellValue(enemyGrid, r-1, c);
		cAdj[1] = GridModel.getCellValue(enemyGrid, r, c+1);
		cAdj[2] = GridModel.getCellValue(enemyGrid, r+1, c);
		cAdj[3] = GridModel.getCellValue(enemyGrid, r, c-1);
		
		return cAdj;
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
			this.cellaConNavePrecColpita[0] = cellaConNavePrecColpita[0];
			this.cellaConNavePrecColpita[1] = cellaConNavePrecColpita[1];
		}

		public int[] getCellaConAcquaPrecColpita() {
			return cellaConAcquaPrecColpita;
		}

		public void setCellaConAcquaPrecColpita(int[] cellaConAcquaPrecColpita) {
			this.cellaConAcquaPrecColpita[0] = cellaConAcquaPrecColpita[0];
			this.cellaConAcquaPrecColpita[1] = cellaConAcquaPrecColpita[1];
		}

		public int[] getPrimaCellaColpita() {
			return primaCellaColpita;
		}

		public void setPrimaCellaColpita(int[] primaCellaColpita) {
			this.primaCellaColpita[0] = primaCellaColpita[0];
			this.primaCellaColpita[1] = primaCellaColpita[1];
		}

		public int[] getCellaPrecColpita() {
			return cellaPrecColpita;
		}

		public void setCellaPrecColpita(int[] cellaPrecColpita) {
			this.cellaPrecColpita[0] = cellaPrecColpita[0];
			this.cellaPrecColpita[1] = cellaPrecColpita[1];
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