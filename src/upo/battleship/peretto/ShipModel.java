package upo.battleship.peretto;

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
	 * @return ShipOrientation orientamento nave
	 * */
	public ShipOrientation getOrientation() {
		return orientation;
	}
	
	/**
	 * Restituisce riga cella di partenza
	 * 
	 * @return int riga cella
	 * */
	public int getPositionRow() {
		return positionRow;
	}
	
	/**
	 * Restituisce colonna cella di partenza
	 * 
	 * @return int colonna cella
	 * */
	public int getPositionCol() {
		return positionCol;
	}
	
	/**
	 * Restituisce dimensione nave
	 * 
	 * @return int dimensione della nave
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
