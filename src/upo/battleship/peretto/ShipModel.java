package upo.battleship.peretto;

public class ShipModel {
	private ShipType type;
	private ShipOrientation orientation;
	private int positionRow;
	private int positionCol;
	
	public ShipModel(int headRowCell, int headColCell, ShipType type, ShipOrientation orientation) {
		this.positionRow = headRowCell;
		this.positionCol = headColCell;
		this.type = type;
		this.orientation = orientation;
	}

	public ShipType getType() {
		return type;
	}
	
	public ShipOrientation getOrientation() {
		return orientation;
	}

	public int getPositionRow() {
		return positionRow;
	}

	public int getPositionCol() {
		return positionCol;
	}
	
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
