package upo.battleship.peretto;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class BattleshipController implements ActionListener {
	
	private BattleshipModel m;
	private GridView gridView;
	private GridAIView gridAIView;
	
	private int totNavi;
	private String[] tipiNavi;
	private int dim;
	
	/**
	 * Crea un nuovo controllerm per la partita
	 * 
	 * @param settings - impostazioni della partita da creare
	 * */
	public BattleshipController(GameSettings settings) {
		
		int[] navi = settings.getNumNavi();
		totNavi = 0;
		
		for(int i = 0; i < navi.length; i++)
			totNavi = totNavi + navi[i];
		
		tipiNavi = new String[totNavi];
		int tipiNaviIndex = 0;
		
		for(int i = 0; i < navi.length; i++){
			for(int j = 0; j < navi[i]; j++){
				switch(i){
					case 0: tipiNavi[tipiNaviIndex] = "Sottomarino"; break;
					case 1: tipiNavi[tipiNaviIndex] = "Portaerei"; break;
					case 2: tipiNavi[tipiNaviIndex] = "Incrociatore"; break;
				}
				tipiNaviIndex++;
			}
		}
		
		dim = settings.getDimGrid();
		
		this.m = new BattleshipModel(
					settings.getDimGrid(), 
					navi[0], 
					navi[1], 
					navi[2]
				);
		
		BattleshipView view = new BattleshipView(this.m, this.dim);
		
		this.gridView = view.getGridPlayerView();
		this.gridAIView = view.getGridAIView();
		
		gridView.jDiagbtnOk.addActionListener(this);
		
		richiediPosizNavi();
		
	}
	
	/**
	 * Aggiunge un ascoltatore ad ogni cella della griglia
	 * 
	 * @param v - vista griglia
	 * */
	private void aggiungiListenerAPulsantiGriglia(GridView v) {
		for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				v.grid[i][j].addMouseListener(
						new MouseAdapter() {
					        public void mouseClicked(MouseEvent e) {
					        	
					        	JLabel l = (JLabel)e.getSource();
					        	String labelName = l.getName();
					        	String[] splitted = labelName.split(";");
					        	Integer r = Integer.parseInt(splitted[0]), c = Integer.parseInt(splitted[1]);
					        	
					        	colpisci(r, c);
					        	v.grid[r][c].removeMouseListener(this);
					        }
					    }
				);
			}
		}
	}
	
	/**
	 * Rimuove tutti gli ascoltatori presenti sulle celle della griglia
	 * */
	private void stopGame() {
		for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				MouseListener[] ml = gridAIView.grid[i][j].getMouseListeners();
				if(ml.length > 0)
					gridAIView.grid[i][j].removeMouseListener(ml[0]);
			}
		}
	}
	
	/**
	 * Colpisce la cella scelta dal giocatore
	 * 
	 * @param row - la riga della cella
	 * @param col - la colonna della cella
	 * */
	private void colpisci(int row, int col){
		
		try{
			m.hitCell(row, col);
			int winner = m.checkWin();
			
			switch(winner){
				case 1: {
					gridView.message("<html><div style='color: red; font-size: 40px'><b>Hai Vinto !!!</b></div></html>");
				}break;
				case 2: {
					gridView.message("<html><div style='color: red; font-size: 40px'><b>Hai Perso !!!</b></div></html>");
				}break;
			}
			
			if(winner != 0){
				stopGame();
			}
		}
		catch(IndexOutOfBoundsException e){
			gridView.message("<html><div style='color: red; font-size: 40px'><b>Si è verificato un errore: "+e.getMessage()+"</b></div></html>");
		}
	}
	
	/**
	 * Richiede la visualizzazione della finestra utilizzata per posizionare una nave
	 * */
	private void richiediPosizNavi() {
		gridView.posizioneNave(tipiNavi);
	}
	
	/**
	 * Tenta di posizionare la nave nella posizione scelta restituendo un messaggio di errore in caso di posizionamento fallito
	 * 
	 * @param d - il pannello usato per recuperare le informazioni sulla nave da posizionare
	 * */
	private void posizionaNave(JDialog d) {
		
		Integer riga = (Integer)gridView.jDiagcmbRiga.getSelectedItem();
		Integer colonna = (Integer)gridView.jDiagcmbColonna.getSelectedItem();
		String orientamento = String.valueOf(gridView.jDiagcmbOrientamento.getSelectedItem());
		ShipOrientation orientation = ShipOrientation.HORIZONTAL;
		
		String tipoNave = String.valueOf(gridView.JDiagcmbNavi.getSelectedItem());
		ShipType shipType = ShipType.PORTAEREI;
		
		switch(orientamento){
			case "Orizzontale": orientation = ShipOrientation.HORIZONTAL; break;
			case "Verticale": orientation = ShipOrientation.VERTICAL; break;
		}
		
		switch(tipoNave){
			case "Sottomarino": shipType = ShipType.SOTTOMARINO; break;
			case "Portaerei": shipType = ShipType.PORTAEREI; break;
			case "Incrociatore": shipType = ShipType.INCROCIATORE; break;
		}
		
		try{
			m.addShip(new ShipModel(riga, colonna, shipType, orientation));
			gridView.JDiagcmbNavi.removeItemAt(gridView.JDiagcmbNavi.getSelectedIndex());
			totNavi--;
		}
		catch(IllegalStateException e){
			gridView.message("<html><div style='color: red; font-size: 20px'><b>Ops non è stato possibile posizionare la nave, riprova.</b></div></html>");
		}
		
		if(totNavi == 0){
	        d.dispose();
	        aggiungiListenerAPulsantiGriglia(gridAIView);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Component component = (Component) e.getSource();
        JDialog dialog = (JDialog) SwingUtilities.getRoot(component);
		
		posizionaNave(dialog);
	}

}
