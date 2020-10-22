package upo.battleship.peretto;

public class GridPlayerModel extends GridModel {
	
	/**
	 * {@inheritDoc}
	 * */
	public GridPlayerModel(int rows, int cols) {
		super(rows, cols);
	}
	
	/**
	 * Metodo non è utilizzato nel contesto corrente
	 * */
	@Override
	public int[] newMove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

}
