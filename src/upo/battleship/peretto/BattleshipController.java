package upo.battleship.peretto;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class BattleshipController implements ActionListener {
	
	private BattleshipModel m;
	private GridView gridView;
	private GridAIView gridAIView;
	
	private int totNavi;
	private String[] tipiNavi;
	
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
		
		this.m = new BattleshipModel(
					settings.getDimGrid(), 
					navi[0], 
					navi[1], 
					navi[2]
				);
		
		GridAIModel aiModel = this.m.getGridAIModel();
		GridPlayerModel playerModel = this.m.getGridPlayerModel();
		
		this.gridView = new GridView(playerModel, settings.getDimGrid());
		this.gridAIView = new GridAIView(aiModel, settings.getDimGrid());
		
		gridView.jDiagbtnOk.addActionListener(this);
		
		richiediPosizNavi();
		
		aggiungiListenerAPulsantiGriglia(gridAIView);
		
	}
	
	private void aggiungiListenerAPulsantiGriglia(GridView v) {
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				v.grid[i][j].addMouseListener(
						new MouseAdapter() {
					        public void mouseClicked(MouseEvent e) {
					        	
					        	JLabel l = (JLabel)e.getSource();
					        	String labelName = l.getName();
					        	String[] splitted = labelName.split(";");
					        	Integer r = Integer.parseInt(splitted[0]), c = Integer.parseInt(splitted[1]);
					        	//press(l.getName());
					        	System.out.println("Premuto: "+r+c);
					        	colpisci(r, c);
					        	v.grid[r][c].removeMouseListener(this);
					        }
					    }
				);
			}
		}
	}
	
	private void colpisci(int row, int col){
		
		try{
			m.hitCell(row, col);
			int winner = m.checkWin();
			
			switch(winner){
				case 1: {
					System.out.println("Ha vinto il Player");
				}break;
				case 2: {
					System.out.println("Ha vinto l'AI");
				}break;
			}
		}
		catch(IndexOutOfBoundsException e){
			System.out.println("Si è verificato un errore");
		}
	}
	
	private void richiediPosizNavi() {
		gridView.posizioneNave(tipiNavi);
	}
	
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
			System.out.println("error");
		}
		
		if(totNavi == 0){
	        d.dispose();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Integer riga = (Integer)gridView.jDiagcmbRiga.getSelectedItem();
		Integer colonna = (Integer)gridView.jDiagcmbColonna.getSelectedItem();
		String orientamento = String.valueOf(gridView.jDiagcmbOrientamento.getSelectedItem());
		
		int index = gridView.jDiagcmbOrientamento.getSelectedIndex();
		System.out.println(gridView.jDiagcmbOrientamento.getSelectedIndex());
		//gridView.jDiagcmbOrientamento.removeItemAt(index);
		
		System.out.println("Riga: "+riga);
		System.out.println("Colonna: "+colonna);
		System.out.println("Orientamento: "+orientamento);
		
		Component component = (Component) e.getSource();
        JDialog dialog = (JDialog) SwingUtilities.getRoot(component);
		
		posizionaNave(dialog);
	}

}
