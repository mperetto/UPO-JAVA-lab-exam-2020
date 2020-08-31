package upo.battleship.peretto;

public class BattleshipModel {
	
	protected int dimGrid;
	protected int numSottomarini, numPortaerei, numIncrociatori;
	private GridPlayerModel playerModel;
	private GridAIModel aiModel;
	
	/**
	 * Crea un nuovo modello per la gestione del gioco,
	 * setta la dimensione della mappa di gioco il tipo e numero di navi.
	 * 
	 * @param int dimGrid dimensione griglia
	 * @param int numSottomarini numero di sottomarini
	 * @param int numPortaerei numero di portaerei
	 * @param int numIncrociatori numero incrociatori
	 * */
	public BattleshipModel(int dimGrid, int numSottomarini, int numPortaerei, int numIncrociatori) {
		this.numSottomarini = numSottomarini;
		this.numPortaerei = numPortaerei;
		this.numIncrociatori = numIncrociatori;
		this.dimGrid = dimGrid;
		this.playerModel = new GridPlayerModel(dimGrid, dimGrid);
		this.aiModel = new GridAIModel(dimGrid, dimGrid, this.playerModel);
	}
	
	/**
	 * Aggiunge una nave alla griglia di gioco del giocatore umano
	 * 
	 * @param ShipModel oggetto nave
	 * @throws IllegalStateException in caso la nave non possa essere posizionata
	 * */
	public void addShip(ShipModel s) throws IllegalStateException {
		
		try{
			this.playerModel.placeShip(
					s.getPositionRow(),
					s.getPositionCol(),
					s.getOrientation(),
					s.getDimension()
				);
		}
		catch (IndexOutOfBoundsException e){
			throw new IllegalStateException("Impossibile posizionare la nave. ERRORE: "+e.toString());
		}
		
	}
	
	/**
	 * Restituisce oggetto di tipo GridPlayerModel istanziato
	 * 
	 * @return GridPlayerModel oggetto istanziato
	 * */
	public GridPlayerModel getGridPlayerModel() {
		return this.playerModel;
	}
	
	/**
	 * Restituisce oggetto di tipo GridAIModel istanziato
	 * 
	 * @return GridAIModel oggetto istanziato
	 * */
	public GridAIModel getGridAIModel() {
		return this.aiModel;
	}
}
