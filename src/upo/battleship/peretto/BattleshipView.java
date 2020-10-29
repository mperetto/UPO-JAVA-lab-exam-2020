package upo.battleship.peretto;

/**
 * Questa classe gestisce la vista dell'applicazione.
 * La classe genera le due viste delle griglie, una per la griglia del giocatore l'altra per la griglia dell'AI.
 * 
 * @see GridView
 * @see GridAIView
 * 
 * @author Marco Peretto
 * */
public class BattleshipView {

	private GridView playerView;
	private GridAIView aiView;
	
	/**
	 * Crea una nuova vista battaglia navale
	 * 
	 * @param model - il modello che rappresenta il gioco
	 * @param dim - la dimensione della griglia
	 * */
	public BattleshipView(BattleshipModel model, int dim) {
		
		this.playerView = new GridView(model.getGridPlayerModel(), dim);
		this.aiView = new GridAIView(model.getGridAIModel(), dim);
		
	}
	
	/**
	 * Ritorna la vista del giocatore
	 * 
	 * @return GridView - la vista della griglia del giocatore
	 * */
	public GridView getGridPlayerView() {
		return this.playerView;
	}
	
	/**
	 * Ritorna la vista dell'AI
	 * 
	 * @return GridAIView - la vista dell'AI
	 * */
	public GridAIView getGridAIView() {
		return this.aiView;
	}
}
