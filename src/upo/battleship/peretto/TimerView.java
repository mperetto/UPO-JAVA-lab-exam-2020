package upo.battleship.peretto;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimerView extends JPanel {
	
	JFrame f = new JFrame("Battleship");
	JLabel lblTimer;
	JButton btnSalvaPartita, btnNuovaPartita;
	
	public TimerView() {
		super();
		
		BoxLayout layoutYAxis = new BoxLayout(this, BoxLayout.Y_AXIS);
		
		this.setLayout(layoutYAxis);
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//this.add(new JLabel("Tempo rimanente:"));
		lblTimer = new JLabel("5:00");
		lblTimer.setFont(new Font("Sans-Serif", Font.BOLD, 24));
		lblTimer.setForeground(Color.RED);
		
		JPanel panelBoxXAxis = new JPanel();
		BoxLayout layoutXAxis = new BoxLayout(panelBoxXAxis, BoxLayout.X_AXIS);
		
		panelBoxXAxis.add(new JLabel("Tempo rimanente:"));
		panelBoxXAxis.add(lblTimer);
		
		this.add(panelBoxXAxis);
		
		panelBoxXAxis = new JPanel();
		layoutXAxis = new BoxLayout(panelBoxXAxis, BoxLayout.X_AXIS);
		panelBoxXAxis.setLayout(layoutXAxis);
		
		btnSalvaPartita = new JButton("Salva");
		btnNuovaPartita = new JButton("Nuova");
		panelBoxXAxis.add(btnSalvaPartita);
		panelBoxXAxis.add(btnNuovaPartita);
		
		this.add(panelBoxXAxis);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setContentPane(this);
	    f.setPreferredSize(new Dimension(300, 120));
	    f.pack();
	    f.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new TimerView();
		
	}
	
}
