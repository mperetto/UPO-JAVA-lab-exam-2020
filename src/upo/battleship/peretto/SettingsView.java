package upo.battleship.peretto;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingsView extends JPanel{
	
	final JComboBox<String> cmbDimGriglia;
	JButton btnCaricaPartita, btnGioca;
	JButton btnPiuSottomarino, btnMenoSottomarino, btnPiuPortaerei, btnMenoPortaerei, btnPiuIncrociatore, btnMenoIncrociatore;
	JLabel lblNumSottomarini, lblNumPortaerei, lblNumIncrociatori;
	JCheckBox cbTempo;
	JFrame f = new JFrame("BattleShip");
	
	public SettingsView() {
		super();
		BoxLayout layoutYAxis = new BoxLayout(this, BoxLayout.Y_AXIS);
		
		this.setLayout(layoutYAxis);
		
		JLabel label = new JLabel("Dimensioni Griglia");
		
		this.add(label);
		this.add(new JLabel("\n"));
		
		String[] dimensioni = {"10x10", "20x20"};
		cmbDimGriglia = new JComboBox<>(dimensioni);
		
		this.add(cmbDimGriglia);
		
		this.add(new JLabel("\n"));
		label = new JLabel("Navi");
		
		this.add(label);
		this.add(new JLabel("\n"));
		
		JPanel panelBoxXAxis = new JPanel();
		BoxLayout layoutXAxis = new BoxLayout(panelBoxXAxis, BoxLayout.X_AXIS);
		
		panelBoxXAxis.setLayout(layoutXAxis);
		
		btnMenoSottomarino = new JButton("-");
		setBtnName(btnMenoSottomarino, "btnMenoSottomarino");
		panelBoxXAxis.add(btnMenoSottomarino);
		
		lblNumSottomarini = new JLabel("1");
		
		panelBoxXAxis.add(lblNumSottomarini);
		btnPiuSottomarino = new JButton("+");
		setBtnName(btnPiuSottomarino, "btnPiuSottomarino");
		
		panelBoxXAxis.add(btnPiuSottomarino);
		panelBoxXAxis.add(new JLabel("Sottomarini (1x3)"));
		panelBoxXAxis.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		this.add(panelBoxXAxis);
		this.add(new JLabel("\n"));
		
		panelBoxXAxis = new JPanel();
		panelBoxXAxis.setLayout(new BoxLayout(panelBoxXAxis, BoxLayout.X_AXIS));
		
		btnMenoPortaerei = new JButton("-");
		setBtnName(btnMenoPortaerei, "btnMenoPortaerei");
		panelBoxXAxis.add(btnMenoPortaerei);
		
		lblNumPortaerei = new JLabel("1");
		
		panelBoxXAxis.add(lblNumPortaerei);
		btnPiuPortaerei = new JButton("+");
		setBtnName(btnPiuPortaerei, "btnPiuPortaerei");
		panelBoxXAxis.add(btnPiuPortaerei);
		panelBoxXAxis.add(new JLabel("Portaerei (1x5)"));
		panelBoxXAxis.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		this.add(panelBoxXAxis);
		this.add(new JLabel("\n"));
		
		panelBoxXAxis = new JPanel();
		panelBoxXAxis.setLayout(new BoxLayout(panelBoxXAxis, BoxLayout.X_AXIS));
		
		btnMenoIncrociatore = new JButton("-");
		setBtnName(btnMenoIncrociatore, "btnMenoIncrociatore");
		panelBoxXAxis.add(btnMenoIncrociatore);
		
		lblNumIncrociatori = new JLabel("0");
		
		panelBoxXAxis.add(lblNumIncrociatori);
		btnPiuIncrociatore = new JButton("+");
		setBtnName(btnPiuIncrociatore, "btnPiuIncrociatore");
		panelBoxXAxis.add(btnPiuIncrociatore);
		panelBoxXAxis.add(new JLabel("Incrociatore (1x4)"));
		panelBoxXAxis.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		this.add(panelBoxXAxis);
		this.add(new JLabel("\n"));
		
		panelBoxXAxis = new JPanel();
		panelBoxXAxis.setLayout(new BoxLayout(panelBoxXAxis, BoxLayout.X_AXIS));
		
		btnGioca = new JButton("Gioca");
		setBtnName(btnGioca, "btnGioca");
		panelBoxXAxis.add(btnGioca);
		panelBoxXAxis.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		this.add(panelBoxXAxis);
		
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setContentPane(this);
	    f.pack();
	    f.setVisible(true);
	}
	
	private void setBtnName(JButton btn, String name) {
		btn.setName(name);
	}
	
	public static void main(String[] args) {
		new SettingsView();
	}
}
