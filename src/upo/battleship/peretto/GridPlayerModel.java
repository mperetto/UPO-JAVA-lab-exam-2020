package upo.battleship.peretto;

public class GridPlayerModel extends GridModel {
	
	/**
	 * Inizializza una nuova griglia
	 * */
	public GridPlayerModel(int rows, int cols) {
		super(rows, cols);
	}

	@Override
	public int[] newMove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

}
