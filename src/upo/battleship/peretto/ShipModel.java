package upo.battleship.peretto;

/**
 * La classe permette di gestire le caratteristiche di una nave, quali tipo, dimensione, posizione, orientamento.
 * 
 * @author Marco Peretto
 * */
public class ShipModel {
	private ShipType type;
	private ShipOrientation orientation;
	private int positionRow;
	private int positionCol;
	
	/**
	 * Crea un nuovo oggetto nave
	 * 
	 * @param headRowCell - numero riga cella di partenza
	 * @param headColCell - numero colonna cella di partenza
	 * @param type - tipo nave
	 * @param orientation - orientamento nave
	 * */
	public ShipModel(int headRowCell, int headColCell, ShipType type, ShipOrientation orientation) {
		this.positionRow = headRowCell;
		this.positionCol = headColCell;
		this.type = type;
		this.orientation = orientation;
	}
	
	/**
	 * Restituisce tipo nave
	 * 
	 * @return ShipType tipo nave
	 * */
	public ShipType getType() {
		return type;
	}
	
	/**
	 * Restituisce orientamento nave
	 * 
	 * @return orientamento nave
	 * */
	public ShipOrientation getOrientation() {
		return orientation;
	}
	
	/**
	 * Restituisce riga cella di partenza
	 * 
	 * @return riga cella
	 * */
	public int getPositionRow() {
		return positionRow;
	}
	
	/**
	 * Restituisce colonna cella di partenza
	 * 
	 * @return colonna cella
	 * */
	public int getPositionCol() {
		return positionCol;
	}
	
	/**
	 * Restituisce dimensione nave
	 * 
	 * @return dimensione della nave
	 * */
	public int getDimension() {
		int dim = 0;
		
		switch(type){
			case SOTTOMARINO: dim = 3; break;
			case PORTAEREI: dim = 5; break;
			case INCROCIATORE: dim = 4; break;
		}
		
		return dim;
		
	}
}
