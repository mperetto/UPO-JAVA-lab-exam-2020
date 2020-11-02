package upo.battleship.peretto;

/**
 * La classe permette di gestire lo stato di una partita di Battaglia navale fornendo tutti i metodi necessari.
 * 
 * @author Marco Peretto
 * */
public class BattleshipModel {
	
	protected int dimGrid;
	protected int numSottomarini, numPortaerei, numIncrociatori;
	protected int numSottIns, numPortIns, numIncrIns;
	private GridPlayerModel playerModel;
	private GridAIModel aiModel;
	
	/**
	 * Crea un nuovo modello per la gestione del gioco,
	 * setta la dimensione della mappa di gioco il tipo e numero di navi.
	 * 
	 * @param dimGrid - dimensione griglia
	 * @param numSottomarini - numero di sottomarini
	 * @param numPortaerei - numero di portaerei
	 * @param numIncrociatori - numero incrociatori
	 * */
	public BattleshipModel(int dimGrid, int numSottomarini, int numPortaerei, int numIncrociatori) {
		this.numSottomarini = numSottomarini;
		this.numPortaerei = numPortaerei;
		this.numIncrociatori = numIncrociatori;
		this.dimGrid = dimGrid;
		this.playerModel = new GridPlayerModel(dimGrid, dimGrid);
		this.aiModel = new GridAIModel(dimGrid, dimGrid, this.playerModel);
		
		this.numSottIns = 0;
		this.numPortIns = 0;
		this.numIncrIns = 0;
	}
	
	/**
	 * Aggiunge una nave alla griglia di gioco del giocatore umano,
	 * Simultaneamente aggiunge lo stesso tipo di nave alla griglia AI
	 * 
	 * @param s - oggetto nave
	 * @throws IllegalStateException in caso la nave non possa essere posizionata
	 * */
	public void addShip(ShipModel s) throws IllegalStateException {
		
		if(isFullOf(s.getType())){
			throw new IllegalStateException("Impossibile posizionare nave, limite massimo raggiunto");
		}
		
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
		
		try{
			this.aiModel.placeShip(-1, -1, ShipOrientation.HORIZONTAL, s.getDimension());
		}
		catch (IndexOutOfBoundsException e){
			throw new IllegalStateException("(GridAI) Impossibile posizionare la nave. ERRORE: "+e.toString());
		}
		
		switch(s.getType()) {
			case SOTTOMARINO: numSottIns++; break;
			case PORTAEREI: numPortIns++; break;
			case INCROCIATORE: numIncrIns++; break;
		}
		
	}
	
	/**
	 * Controlla se si è già raggiunto il limite massimo di navi che possono essere presenti nella griglia di gioco
	 * 
	 * @param sT - tipo di nave su cui devo effettuare il controllo
	 * @return <code>true</code> se ho raggiunto il limite di navi <code>false</code> altrimenti
	 * */
	private boolean isFullOf(ShipType sT) {
		boolean full = false;
		
		switch(sT){
			case SOTTOMARINO: {
				full = numSottIns >= numSottomarini;
			}; break;
			case PORTAEREI: {
				full = numPortIns >= numPortaerei;
			}; break;
			case INCROCIATORE: {
				full = numIncrIns >= numIncrociatori;
			}; break;
		}
		
		return full;
	}
	
	/**
	 * Controlla se un giocatore ha affondato tutte le navi nemiche
	 * 
	 * @return 0 (nessun vincitore), 1 (vincitore Player), 2 (vincitore AI)
	 * */
	public int checkWin() {
		
		if(playerModel.isTutteNaviAffondate()){
			return 2;
		}
		else if(aiModel.isTutteNaviAffondate()){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	/**
	 * Riceve le coordinate della cella da colpire scelta dal giocatore, e colpisce la cella nella griglia AI
	 * 
	 * @param row - riga scelta
	 * @param col - colonna scelta
	 * @throws IndexOutOfBoundsException - nel caso la cella non possa essere colpita
	 * */
	public void hitCell(int row, int col) throws IndexOutOfBoundsException{
		
		try{
			aiModel.hitCell(row, col);
		}
		catch(IndexOutOfBoundsException e){
			throw new IndexOutOfBoundsException(e.getMessage());
		}
		
	}
	
	/**
	 * Restituisce oggetto di tipo GridPlayerModel istanziato
	 * 
	 * @return Modello della griglia del giocatore
	 * */
	public GridPlayerModel getGridPlayerModel() {
		return this.playerModel;
	}
	
	/**
	 * Restituisce oggetto di tipo GridAIModel istanziato
	 * 
	 * @return Modello della griglia dell'AI
	 * */
	public GridAIModel getGridAIModel() {
		return this.aiModel;
	}
}
