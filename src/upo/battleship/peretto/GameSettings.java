package upo.battleship.peretto;

/**
 * La classe contiene tutte le informazioni utili a generare una nuova partita.
 * 
 * @author Marco Peretto
 * */
public class GameSettings {
	private int dimGrid;
	private int[] numNavi;
	
	/**
	 * Crea un nuovo contenitore per i settaggi della partita
	 * 
	 * @param numeroTipiNavi - il numero di tipi di nave presenti
	 * */
	public GameSettings(int numeroTipiNavi) {
		numNavi = new int[numeroTipiNavi];
	}
	
	/**
	 * ritorna la dimensione della griglia
	 * 
	 * @return dimensione della griglia
	 * */
	public int getDimGrid() {
		return dimGrid;
	}
	
	/**
	 * imposta la dimensione della griglia
	 * 
	 * @param dimGrid dimensione della griglia
	 * */
	public void setDimGrid(int dimGrid) {
		this.dimGrid = dimGrid;
	}
	
	/**
	 * ritorna il numero di navi per ogni tipo
	 * 
	 * @return numNavi vettore contenente numero di navi per ogni tipo
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
	 * @param numNavi vettore contenente numero di navi per ogni tipo
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
