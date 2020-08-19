package upo.battleship.peretto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

public class SettingsController implements ActionListener {
	
	private SettingsView v;
	
	public SettingsController(SettingsView v) {
		
		this.v = v;
		
		v.btnMenoSottomarino.addActionListener(this);
		v.btnPiuSottomarino.addActionListener(this);
		v.btnMenoPortaerei.addActionListener(this);
		v.btnPiuPortaerei.addActionListener(this);
		v.btnMenoIncrociatore.addActionListener(this);
		v.btnPiuIncrociatore.addActionListener(this);
		
		v.btnCaricaPartita.addActionListener(this);
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
			
			case "btnCaricaPartita": {
				/*
				 * To be implemented
				 * */
				System.out.println(((JComponent)e.getSource()).getName());
			} break;
			
			case "btnGioca": {
				/*
				 * To be implemented
				 * */
				System.out.println(((JComponent)e.getSource()).getName());
			} break;
			
		}
		
	}
}
