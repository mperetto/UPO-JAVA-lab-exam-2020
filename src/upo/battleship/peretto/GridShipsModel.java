package upo.battleship.peretto;

/**
 * L'interfaccia descrive i metodi base che le griglia devono implementare
 * 
 * @author Marco Peretto
 * */
public interface GridShipsModel {
	
	/**
	 * Posiziona la nave all'interno della griglia verificanod la correttezza del posizionamento.
	 * 
	 * @param row - riga in cui dovrà risiedere l'inizio della nave
	 * @param col - colonna in cui dovrà risiedere l'inizio della nave
	 * @param orientation - orientamento della nave (orizzontale, verticale)
	 * @param dim - dimensione della nave
	 * 
	 * @throws IndexOutOfBoundsException - in caso la nave non possa essere posizionata
	 * 
	 * @return esito posizionamento nave (successo, fallimento)
	 * */
	public void placeShip(int row, int col, ShipOrientation orientation, int dim) throws IndexOutOfBoundsException;
	
	/**
	 * Colpisce una cella all'interno della propria griglia
	 * 
	 * @param row - riga in cui effettuare il colpo
	 * @param col - colonna in cui effettiare il colpo
	 * 
	 * @throws IndexOutOfBoundsException - in caso la cella non possa essere colpita
	 * */
	public void hitCell(int row, int col) throws IndexOutOfBoundsException;
	
}
