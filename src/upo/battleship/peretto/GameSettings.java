package upo.battleship.peretto;

public class GameSettings {
	private int dimGrid;
	private int[] numNavi;
	
	public GameSettings(int numeroTipiNavi) {
		numNavi = new int[numeroTipiNavi];
	}
	
	/**
	 * ritorna la dimensione della griglia
	 * 
	 * @return int dimensione della griglia
	 * */
	public int getDimGrid() {
		return dimGrid;
	}
	
	/**
	 * imposta la dimensione della griglia
	 * 
	 * @param int dimGrid dimensione della griglia
	 * */
	public void setDimGrid(int dimGrid) {
		this.dimGrid = dimGrid;
	}
	
	/**
	 * ritorna il numero di navi per ogni tipo
	 * 
	 * @return int[] numNavi vettore contenente numero di navi per ogni tipo
	 * 
	 * Ordine per i tipi
	 * [0] numero sottomarini
	 * [1] numero portaerei
	 * [2] numero incrociatori
	 * */
	public int[] getNumNavi() {
		return numNavi;
	}
	
	/**
	 * imposta il numero di navi per ogni tipo
	 * 
	 * @param int[] numNavi vettore contenente numero di navi per ogni tipo
	 * 
	 * Ordine per i tipi
	 * [0] numero sottomarini
	 * [1] numero portaerei
	 * [2] numero incrociatori
	 * */
	public void setNumNavi(int[] numNavi) {
		this.numNavi = numNavi;
	}
	
	
}
