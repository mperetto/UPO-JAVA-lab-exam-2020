package upo.battleship.peretto;

/**
 * @author Marco Peretto
 * 
 * Questa classe gestisce la vista dell'applicazione.
 * Mette a disposizione tre finestre che permettono nello specifico di gestire le impostazioni della partita, di visualizzare la mappa di gioco e di posizionare le rispetttive navi.
 * 
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
