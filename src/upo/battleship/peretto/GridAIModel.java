package upo.battleship.peretto;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Random; 

/**
 * La classe permette di gestire una griglia di gioco per la CPU.
 * 
 * @author Marco Peretto
 * */
public class GridAIModel extends GridModel implements Observer {
	
	private CellStatus[][] enemyGrid;
	private GridPlayerModel playerModel;
	private AI ai;
	private Random randGenerator;
	
	/**
	 * Genero una nuova griglia di gioco per la CPU.
	 * 
	 * @param rows - numero di righe della matrice.
	 * @param cols - numero di colonne della matrice.
	 * @param playerModel - griglia del giocatore.
	 * */
	public GridAIModel(int rows, int cols, GridPlayerModel playerModel) {
		super(rows, cols);
		this.enemyGrid = new CellStatus[rows][cols];
		for(CellStatus[] row : this.enemyGrid) {
			Arrays.fill(row, CellStatus.CELL_EMPTY);
		}
		this.playerModel = playerModel;
		this.playerModel.addObserver(this);
		this.ai = new AI(rows);
		this.randGenerator = new Random();
	}
	
	/**
	 * Restituisce le cordinate della cella da colpire
	 * 
	 * @return Coordinate cella colpita all'interno della griglia del player
	 * 
	 * */
	public int[] newMove() {
		
		int[] cellaColpita = new int[2];
		
		if(this.isTutteNaviAffondate())
			return null;
		
		if(!ai.isNaveIndividuata()){
			
			int[] cellaDaColpire = new int[2];
			cellaDaColpire = generaCellaCasuale();
			playerModel.hitCell(cellaDaColpire[0], cellaDaColpire[1]);
			cellaColpita[0] = cellaDaColpire[0];
			cellaColpita[1] = cellaDaColpire[1];
			
			if(this.enemyGrid[cellaDaColpire[0]][cellaDaColpire[1]] == CellStatus.CELL_SHIP_HIT){
				ai.setNaveIndividuata(true);
				ai.setCellaConNavePrecColpita(cellaDaColpire);
				ai.setPrimaCellaColpita(cellaDaColpire);
			}
		}
		else if(ai.getOrientamento() != null){
			
			switch(ai.getOrientamento()){
				case VERTICAL:{
					
					if(ai.getEstrNaveInd() == 0){
						int[] cellaDaColpire = ai.getCellaConNavePrecColpita();
						cellaDaColpire[0] -= 1;
						if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != null && GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != CellStatus.CELL_EMPTY_HIT){
							playerModel.hitCell(cellaDaColpire[0], cellaDaColpire[1]);
							cellaColpita[0] = cellaDaColpire[0];
							cellaColpita[1] = cellaDaColpire[1];
							if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) == CellStatus.CELL_EMPTY_HIT){
								ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
							}
							else{
								ai.setCellaConNavePrecColpita(cellaDaColpire);
							}
						}
						else {
							ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
							return this.newMove();
						}
					}
					else{
						int[] cellaDaColpire = ai.getPrimaCellaColpita();
						do{
							cellaDaColpire[0] += 1;
						}while(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) == CellStatus.CELL_SHIP_HIT);
						
						if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != null && GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != CellStatus.CELL_EMPTY_HIT){
							playerModel.hitCell(cellaDaColpire[0], cellaDaColpire[1]);
							cellaColpita[0] = cellaDaColpire[0];
							cellaColpita[1] = cellaDaColpire[1];
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
							return this.newMove();
						}
					}
					
				} break;
				case HORIZONTAL: {
					
					if(ai.getEstrNaveInd() == 0){
						int[] cellaDaColpire = ai.getCellaConNavePrecColpita();
						cellaDaColpire[1] += 1;
						if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != null && GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != CellStatus.CELL_EMPTY_HIT){
							playerModel.hitCell(cellaDaColpire[0], cellaDaColpire[1]);
							cellaColpita[0] = cellaDaColpire[0];
							cellaColpita[1] = cellaDaColpire[1];
							if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) == CellStatus.CELL_EMPTY_HIT){
								ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
							}
							else{
								ai.setCellaConNavePrecColpita(cellaDaColpire);
							}
						}
						else {
							ai.setEstrNaveInd(ai.getEstrNaveInd() + 1);
							return this.newMove();
						}
					}
					else{
						int[] cellaDaColpire = ai.getPrimaCellaColpita();
						do{
							cellaDaColpire[1] -= 1;
						}while(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) == CellStatus.CELL_SHIP_HIT);
						
						if(GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != null && GridModel.getCellValue(enemyGrid, cellaDaColpire[0], cellaDaColpire[1]) != CellStatus.CELL_EMPTY_HIT){
							playerModel.hitCell(cellaDaColpire[0], cellaDaColpire[1]);
							cellaColpita[0] = cellaDaColpire[0];
							cellaColpita[1] = cellaDaColpire[1];
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
							return this.newMove();
						}
					}
					
				} break;
			}
			
		}
		else{
			
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
					cellaColpita[0] = cella[0];
					cellaColpita[1] = cella[1];
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
		
		return cellaColpita;
	}
	
	/**
	 * Crea un nuovo generatore random partendo da un seme fornito
	 * 
	 * @param seed - seme di partenza per i numeri randomici
	 * */
	public void setRandomSeed(long seed) {
		this.randGenerator = new Random(seed);
	}
	
	/**
	 * Genero una cella casuale compresa nel range della griglia di gioco.
	 * 
	 * @return vettore contenente le coordinate della cella generata.
	 * */
	private int[] generaCellaCasuale() {
		int range = this.gridCols; // numeri da 0 a numero di colonne/righe della matrice
		int[] cell = new int[2];
		Random r = this.randGenerator;
		
		do{
			cell[0] = r.nextInt(range);
			cell[1] = r.nextInt(range);
		}while(this.enemyGrid[cell[0]][cell[1]] != CellStatus.CELL_EMPTY);		
		
		return cell;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		CellStatus[][] g = (CellStatus[][])arg1;
		
		for(int i = 0; i < this.gridRows; i++){
			for(int j = 0; j < this.gridCols; j++){
				switch(g[i][j]){
					case CELL_EMPTY_HIT: this.enemyGrid[i][j] = g[i][j]; break;
					case CELL_SHIP_HIT: this.enemyGrid[i][j] = g[i][j]; break;
					default: this.enemyGrid[i][j] = CellStatus.CELL_EMPTY;
					break;
				}
			}
		}
		
	}
	
	/**
	 * {@inheritDoc}
	 * Per i parametri row, col l'inserimento di valori >= 0 comporta il posizionamento della nave con i parametri forniti
	 * */
	@Override
	public void placeShip(int row, int col, ShipOrientation orientation, int dim) throws IndexOutOfBoundsException {
		
		boolean esci = false;
		int orientamento;
		
		if(row >= 0 && col >= 0){
			try{
				super.placeShip(row, col, orientation, dim);
			}
			catch (IndexOutOfBoundsException e){
				throw new IndexOutOfBoundsException("(GridAI): "+e.getMessage());
			}
		}
		else{
			while(!esci){
				
				row = randGenerator.nextInt(gridRows);
				col = randGenerator.nextInt(gridCols);
				orientamento = randGenerator.nextInt(2);
				
				if(orientamento == 0){
					orientation = ShipOrientation.HORIZONTAL;
					col = randGenerator.nextInt((gridCols-dim)+1);
				}
				else{
					orientation = ShipOrientation.VERTICAL;
					row = randGenerator.nextInt((gridRows-dim)+1);
				}
				
				try{
					super.placeShip(row, col, orientation, dim);
					esci = true;
				}
				catch(IndexOutOfBoundsException e){}
				
			}
		}
		
		
	}
	
	/**
	 * Il metodo fornisce lo stato delle quattro celle adiacenti (N, S, E, O) alla cella fornita.
	 * 
	 * @param r - riga della cella considerata.
	 * @param c - colonna della cella considerata.
	 * 
	 * @return vettore contenente le quattro celle adiacenti nel seguante ordine
	 * 			[0] cella Nord,
	 * 			[1] cella Est,
	 * 			[2] cella Sud,
	 * 			[3] cella Ovest
	 * */
	private CellStatus[] getCellAdj(int r, int c) {
		
		CellStatus[] cAdj = new CellStatus[4];
		
		cAdj[0] = GridModel.getCellValue(enemyGrid, r-1, c);
		cAdj[1] = GridModel.getCellValue(enemyGrid, r, c+1);
		cAdj[2] = GridModel.getCellValue(enemyGrid, r+1, c);
		cAdj[3] = GridModel.getCellValue(enemyGrid, r, c-1);
		
		return cAdj;
	}
	
	/**
	 * Classe utilizzata per mantenere le informazioni sullo stato raggiunto dalla CPU per i colpi sulla griglia nemica.
	 * */
	class AI {
		
		private boolean naveIndividuata;
		private int[] cellaConNavePrecColpita;
		private int[] primaCellaColpita;
		private ShipOrientation orientamento;
		private int estrNaveInd;
		private boolean affondata;
		
		/**
		 * Creo una nuova istanza della classe
		 * 
		 * @param dimGrid - dimensione della griglia.
		 * */
		public AI(int dimGrid){
			this.init();
		}
		
		/**
		 * Inizializzo lo stato dell'AI resettando tutti i valori.
		 * */
		public void init() {
			naveIndividuata = false;
			cellaConNavePrecColpita = new int[2];
			Arrays.fill(cellaConNavePrecColpita, -1);
			primaCellaColpita = new int[2];
			Arrays.fill(primaCellaColpita, -1);
			orientamento = null;
			estrNaveInd = 0;
			affondata = false;
		}
		
		/**
		 * Ritorna le coordinate dell'ultima cella contenente nave colpita.
		 * 
		 * @return vettore contenente le coordinate della cella.
		 * */
		public int[] getCellaConNavePrecColpita() {
			return cellaConNavePrecColpita;
		}
		
		/**
		 * Setta le coordinate dell'ultima cella contenente nave colpita.
		 * 
		 * @param vettore contenente coordinate cella.
		 * */
		public void setCellaConNavePrecColpita(int[] cellaConNavePrecColpita) {
			this.cellaConNavePrecColpita[0] = cellaConNavePrecColpita[0];
			this.cellaConNavePrecColpita[1] = cellaConNavePrecColpita[1];
		}
		
		/**
		 * Ritorna le coordinate della prima cella contenente nave colpita.
		 * 
		 * @return vettore contenente le coordinate della cella.
		 * */
		public int[] getPrimaCellaColpita() {
			return primaCellaColpita;
		}
		
		/**
		 * Setta le coordinate della prima cella contenente nave colpita.
		 * 
		 * @param vettore contenente coordinate cella.
		 * */
		public void setPrimaCellaColpita(int[] primaCellaColpita) {
			this.primaCellaColpita[0] = primaCellaColpita[0];
			this.primaCellaColpita[1] = primaCellaColpita[1];
		}

		/**
		 * Ritorna l'orientamento della nave individuata che si sta cercando di affondare.
		 * 
		 * @return orientamento della nave, <code>null</code> se non ho individuato l'orientamento.
		 * */
		public ShipOrientation getOrientamento() {
			return orientamento;
		}

		/**
		 * Setta l'orientamento della nave.
		 * 
		 * @param orientamento - orientamento della nave individuata.
		 * */
		public void setOrientamento(ShipOrientation orientamento) {
			this.orientamento = orientamento;
		}

		/**
		 * Ritorna il numero di estramità della nave individuate.
		 * 
		 * @return numero di estremità individuate.
		 * */
		public int getEstrNaveInd() {
			return estrNaveInd;
		}

		/**
		 * Setta le estremità della nave individuata.
		 * 
		 * @param estrNaveInd - numero di estremità individuate.
		 * */
		public void setEstrNaveInd(int estrNaveInd) {
			this.estrNaveInd = estrNaveInd;
		}

		/**
		 * Restituisce se la nave è affondata oppure no.
		 * 
		 * @return <code>true</code> se la nave è stata affondata, <code>false</code> altrimenti.
		 * */
		public boolean isAffondata() {
			return affondata;
		}
		
		/**
		 * Setta se la nave è stata affondata.
		 * 
		 * @param affondata - stato della nave.
		 * */
		public void setAffondata(boolean affondata) {
			this.affondata = affondata;
		}

		/**
		 * Setto se la nave è stata individuata oppure no.
		 * 
		 * @param naveIndividuata - stato di individuazione della nave.
		 * */
		public void setNaveIndividuata(boolean naveIndividuata) {
			this.naveIndividuata = naveIndividuata;
		}
		
		/**
		 * Restituisce se la nave è stata individuata oppure no.
		 * 
		 * @return <code>true</code> se la nave è stata individuata, <code>false</code> altrimenti.
		 * */
		public boolean isNaveIndividuata() {
			return naveIndividuata;
		}
		
	}

}