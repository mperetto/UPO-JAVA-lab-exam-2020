package upo.battleship;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * @author Marco Peretto
 * 
 * Questa classe gestisce la vista dell'applicazione.
 * Mette a disposizione tre finestre che permettono nello specifico di gestire le impostazioni della partita, di visualizzare la mappa di gioco e di posizionare le rispetttive navi.
 * 
 * */
public class BattleShipView {
	
	/*final JComboBox<String> cmbDimGriglia;
	JButton btnCaricaPartita, btnGioca;
	JButton btnPiuSottomarino, btnMenoSottomarino, btnPiuPortaerei, btnMenoPortaerei, btnPiuIncrociatore, btnMenoIncrociatore;*/
	//JButton[] = {JButton btnPiuSottomarino, btnMenoSottomarino, btnPiuPortaerei, btnMenoPortaerei, btnPiuIncrociatore, btnMenoIncrociatore};
	
	/**
	 * 
	 * */
	public BattleShipView() {
		//super();
		
		SettaggiPartitaView settings = new SettaggiPartitaView();
		GiocoView gioco = new GiocoView(20);
		gioco.aggiungiEl();
		gioco.posizioneNave();
		
	}
	
	class SettaggiPartitaView extends JFrame{
		
		final JComboBox<String> cmbDimGriglia;
		JButton btnCaricaPartita, btnGioca;
		JButton btnPiuSottomarino, btnMenoSottomarino, btnPiuPortaerei, btnMenoPortaerei, btnPiuIncrociatore, btnMenoIncrociatore;
		JLabel lblNumSottomarini, lblNumPortaerei, lblNumIncrociatori;
		
		public SettaggiPartitaView() {
			super("BattleShip");
			
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
			
			lblNumSottomarini = new JLabel("1");
			
			panelBoxXAxis.add(lblNumSottomarini);
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
			
			lblNumPortaerei = new JLabel("1");
			
			panelBoxXAxis.add(lblNumPortaerei);
			btnPiuPortaerei = new JButton("+");
			panelBoxXAxis.add(btnPiuPortaerei);
			panelBoxXAxis.add(new JLabel("Portaerei (1x5)"));
			panelBoxXAxis.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			panel.add(panelBoxXAxis);
			panel.add(new JLabel("\n"));
			
			panelBoxXAxis = new JPanel();
			panelBoxXAxis.setLayout(new BoxLayout(panelBoxXAxis, BoxLayout.X_AXIS));
			
			btnMenoIncrociatore = new JButton("-");
			panelBoxXAxis.add(btnMenoIncrociatore);
			
			lblNumIncrociatori = new JLabel("0");
			
			panelBoxXAxis.add(lblNumIncrociatori);
			btnPiuIncrociatore = new JButton("+");
			panelBoxXAxis.add(btnPiuIncrociatore);
			panelBoxXAxis.add(new JLabel("Incrociatore (1x4)"));
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
			
			//JFrame f = new JFrame("BattleShip");
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setContentPane(panel);
		    this.pack();
		    this.setVisible(true);
		}
	}
	
	class GiocoView extends JFrame{
		
		JButton btnSalvaPartita, btnNuovaPartita;
		private JLabel[][] gridPlayer, gridEnemy;
		
		public GiocoView(int dim) {
			
			this.gridEnemy = new JLabel[dim][dim];
			this.gridPlayer = new JLabel[dim][dim];
			
			JPanel panel = new JPanel();
			BoxLayout layoutYAxis = new BoxLayout(panel, BoxLayout.Y_AXIS);
			
			panel.setLayout(layoutYAxis);
			
			JPanel panelBoxXAxis = new JPanel();
			BoxLayout layoutXAxis = new BoxLayout(panelBoxXAxis, BoxLayout.X_AXIS);
			
			panelBoxXAxis.setLayout(layoutXAxis);
			
			JPanel mapPanel = new JPanel();
			mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));
			
			mapPanel.add(new JLabel("Schieramento nemico"));
			mapPanel.add(this.generateGrid(20, gridEnemy));
			
			panelBoxXAxis.add(mapPanel);
			panelBoxXAxis.add(new JLabel("\t\t \t\t"));
			
			mapPanel = new JPanel();
			mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));
			
			mapPanel.add(new JLabel("Il tuo schieramento"));
			mapPanel.add(this.generateGrid(20, gridPlayer));
			
			panelBoxXAxis.add(mapPanel);
			
			panel.add(panelBoxXAxis);
			
			panelBoxXAxis = new JPanel();
			panelBoxXAxis.setLayout(new BoxLayout(panelBoxXAxis, BoxLayout.X_AXIS));
			
			btnSalvaPartita = new JButton("Salva Partita");
			panelBoxXAxis.add(btnSalvaPartita);
			
			btnNuovaPartita = new JButton("Nuova Partita");
			panelBoxXAxis.add(btnNuovaPartita);
			
			panel.add(panelBoxXAxis);
			
			
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setContentPane(panel);
		    this.pack();
		    this.setVisible(true);
		    //this.dispose();	il comando chiude il JFrame
		}
		
		public void posizioneNave() {
			
			JTextField cella = new JTextField();
			String[] opzOrientamento = {"Verticale", "Orizzontale"};
			JComboBox<String> orientamento = new JComboBox<>(opzOrientamento);
			
			JDialog d = new JDialog(this, "Posiziona Nave");
			
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
			
			p.add(new JLabel("Posiziona nave"));
			p.add(cella);
			p.add(new JLabel("Scegli l'orientamento"));
			p.add(orientamento);
			p.add(new JButton("Ok"));
			d.add(p);
			d.setSize(300, 150);
			d.setVisible(true);
		}
		
		private JPanel generateGrid(int dim, JLabel[][] grid) {
			
			//grid = new JLabel[dim][dim];
			//gridEnemy = grid;
			JPanel gridPanel = new JPanel();
			gridPanel.setLayout(new GridLayout(dim, dim));
			Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
			
			for(int i = 0; i < dim; i++) {
				for(int j = 0; j < dim; j++) {
					grid[i][j] = new JLabel("");
					grid[i][j].setPreferredSize(new Dimension(20, 20));
					grid[i][j].setHorizontalAlignment(JLabel.CENTER);
					grid[i][j].setVerticalAlignment(JLabel.CENTER);
					grid[i][j].setOpaque(true);
					grid[i][j].setBackground(Color.WHITE);
					grid[i][j].setBorder(border);
					gridPanel.add(grid[i][j]);
				}
			}
			
			return gridPanel;
			
		}
		
		public void aggiungiEl() {
			gridEnemy[2][4].setBackground(Color.DARK_GRAY);
			gridEnemy[2][5].setBackground(Color.DARK_GRAY);
			gridEnemy[2][6].setBackground(Color.DARK_GRAY);
		}
	}
	
}
