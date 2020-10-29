package upo.battleship.peretto;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 * La classe genera un controller della finestra contenente le impostazioni della partita.
 * 
 * @see SettingsView
 * @author Marco Peretto
 * */
public class SettingsController implements ActionListener {
	
	private SettingsView v;
	
	/**
	 * Crea un nuovo controller per la finestra dei settaggi della partita
	 * 
	 * @param v - la vista dei settaggi della partita da creare
	 * */
	public SettingsController(SettingsView v) {
		
		this.v = v;
		
		v.btnMenoSottomarino.addActionListener(this);
		v.btnPiuSottomarino.addActionListener(this);
		v.btnMenoPortaerei.addActionListener(this);
		v.btnPiuPortaerei.addActionListener(this);
		v.btnMenoIncrociatore.addActionListener(this);
		v.btnPiuIncrociatore.addActionListener(this);
		
		v.btnGioca.addActionListener(this);
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String componentName = ((JComponent)e.getSource()).getName();
		Integer num;
		
		switch(componentName) {
		
			case "btnMenoSottomarino": {
				num = Integer.parseInt(v.lblNumSottomarini.getText());
				
				if(num > 1) {
					num--;
					v.lblNumSottomarini.setText(num.toString());
				}
			} break;
			
			case "btnPiuSottomarino": {
				num = Integer.parseInt(v.lblNumSottomarini.getText());
				
				if(num < 2) {
					num++;
					v.lblNumSottomarini.setText(num.toString());
				}
			} break;
			
			case "btnMenoPortaerei": {
				num = Integer.parseInt(v.lblNumPortaerei.getText());
				
				if(num > 1) {
					num--;
					v.lblNumPortaerei.setText(num.toString());
				}
			} break;
			
			case "btnPiuPortaerei": {
				num = Integer.parseInt(v.lblNumPortaerei.getText());
				
				if(num < 2) {
					num++;
					v.lblNumPortaerei.setText(num.toString());
				}
			} break;
			
			case "btnMenoIncrociatore": {
				num = Integer.parseInt(v.lblNumIncrociatori.getText());
				
				if(num > 0) {
					num--;
					v.lblNumIncrociatori.setText(num.toString());
				}
			} break;
			
			case "btnPiuIncrociatore": {
				num = Integer.parseInt(v.lblNumIncrociatori.getText());
				
				if(num < 2) {
					num++;
					v.lblNumIncrociatori.setText(num.toString());
				}
			} break;
			
			case "btnGioca": {
				String dimGriglia = String.valueOf(v.cmbDimGriglia.getSelectedItem());
				
				/*
				 * Vettore contenente numero di navi scelto
				 * 
				 * navi[0] numero sottomarini
				 * navi[1] numero portaerei
				 * navi[2] numero incrociatori
				 * */
				int[] navi = new int[3];
				
				int rows, cols;
				
				switch(dimGriglia){
					case "10x10": {
						rows = 10;
						cols = 10;
					} break;
					case "20x20": {
						rows = 20;
						cols = 20;
					} break;
					default: {
						rows = 10;
						cols = 10;
					}
				}
				
				navi[0] = Integer.parseInt(v.lblNumSottomarini.getText());
				navi[1] = Integer.parseInt(v.lblNumPortaerei.getText());
				navi[2] = Integer.parseInt(v.lblNumIncrociatori.getText());
				
				GameSettings settings = new GameSettings(3);
				settings.setDimGrid(rows);
				settings.setNumNavi(navi);
				
				BattleshipController controller = new BattleshipController(settings);
				
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			} break;
			
		}
		
	}
}
