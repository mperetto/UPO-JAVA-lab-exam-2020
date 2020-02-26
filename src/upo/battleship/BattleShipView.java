package upo.battleship;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class BattleShipView extends JFrame {
	
	final JComboBox<String> cmbDimGriglia;
	JButton btnCaricaPartita, btnGioca;
	JButton btnPiuSottomarino, btnMenoSottomarino, btnPiuPortaerei, btnMenoPortaerei, btnPiuIncrociatore, btnMenoIncrociatore;
	//JButton[] = {JButton btnPiuSottomarino, btnMenoSottomarino, btnPiuPortaerei, btnMenoPortaerei, btnPiuIncrociatore, btnMenoIncrociatore};
	
	/**
	 * 
	 * */
	public BattleShipView() {
		super();
		
		JPanel panel = new JPanel();
		BoxLayout layoutYAxis = new BoxLayout(panel, BoxLayout.Y_AXIS);
		
		panel.setLayout(layoutYAxis);
		
		JLabel label = new JLabel("Dimensioni Griglia");
		
		panel.add(label);
		panel.add(new JLabel("\n"));
		
		String[] dimensioni = {"10x10", "20x20"};
		cmbDimGriglia = new JComboBox<>(dimensioni);
		
		panel.add(cmbDimGriglia);
		
		panel.add(new JLabel("\n"));
		label = new JLabel("Navi");
		
		panel.add(label);
		panel.add(new JLabel("\n"));
		
		JPanel panelBoxXAxis = new JPanel();
		BoxLayout layoutXAxis = new BoxLayout(panelBoxXAxis, BoxLayout.X_AXIS);
		
		panelBoxXAxis.setLayout(layoutXAxis);
		
		btnMenoSottomarino = new JButton("-");
		panelBoxXAxis.add(btnMenoSottomarino);	
		panelBoxXAxis.add(new JLabel("1"));		//dichiarazione anonima provvisoria sarà necessario aggiornare il valore
		btnPiuSottomarino = new JButton("+");
		panelBoxXAxis.add(btnPiuSottomarino);
		panelBoxXAxis.add(new JLabel("Sottomarini (1x3)"));
		panelBoxXAxis.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		panel.add(panelBoxXAxis);
		panel.add(new JLabel("\n"));
		
		panelBoxXAxis = new JPanel();
		panelBoxXAxis.setLayout(new BoxLayout(panelBoxXAxis, BoxLayout.X_AXIS));
		
		btnMenoPortaerei = new JButton("-");
		panelBoxXAxis.add(btnMenoPortaerei);	
		panelBoxXAxis.add(new JLabel("1"));		//dichiarazione anonima provvisoria sarà necessario aggiornare il valore
		btnPiuPortaerei = new JButton("+");
		panelBoxXAxis.add(btnPiuPortaerei);
		panelBoxXAxis.add(new JLabel("Portaerei (1x3)"));
		panelBoxXAxis.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		panel.add(panelBoxXAxis);
		panel.add(new JLabel("\n"));
		
		panelBoxXAxis = new JPanel();
		panelBoxXAxis.setLayout(new BoxLayout(panelBoxXAxis, BoxLayout.X_AXIS));
		
		btnMenoIncrociatore = new JButton("-");
		panelBoxXAxis.add(btnMenoIncrociatore);	
		panelBoxXAxis.add(new JLabel("0"));		//dichiarazione anonima provvisoria sarà necessario aggiornare il valore
		btnPiuIncrociatore = new JButton("+");
		panelBoxXAxis.add(btnPiuIncrociatore);
		panelBoxXAxis.add(new JLabel("Incrociatore (1x3)"));
		panelBoxXAxis.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		panel.add(panelBoxXAxis);
		panel.add(new JLabel("\n"));
		
		panelBoxXAxis = new JPanel();
		panelBoxXAxis.setLayout(new BoxLayout(panelBoxXAxis, BoxLayout.X_AXIS));
		
		btnCaricaPartita = new JButton("Carica Partita Salvata");
		panelBoxXAxis.add(btnCaricaPartita);
		btnGioca = new JButton("Gioca");
		panelBoxXAxis.add(btnGioca);
		panelBoxXAxis.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		panel.add(panelBoxXAxis);
		
		JFrame f = new JFrame("BattleShip");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setContentPane(panel);
	    f.pack();
	    f.setVisible(true);
	}
	
}
