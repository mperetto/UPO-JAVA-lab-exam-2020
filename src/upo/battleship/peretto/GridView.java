package upo.battleship.peretto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import upo.observer.ObserverController;
import upo.observer.ObserverModel;

public class GridView extends JPanel implements Observer {
	
	JComboBox<Integer> jDiagcmbRiga;
	JComboBox<Integer> jDiagcmbColonna;
	JComboBox<String> jDiagcmbOrientamento;
	JComboBox<String> JDiagcmbNavi;
	JButton jDiagbtnOk;
	
	private GridModel m;
	protected JFrame f;
	protected int dim;
	
	JLabel[][] grid;
	
	/**
	 * Crea una nuova finestra griglia
	 * 
	 * @param m - il modello che gestisce la griglia
	 * @param dim - la dimensione della griglia da creare
	 * */
	public GridView(GridModel m, int dim) {
		super();
		
		this.dim = dim;
		this.m = m;
		
		this.m.addObserver(this);
		
		this.grid = new JLabel[dim][dim];
		
		this.initJDialogComponents();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(this.generateGrid(dim, grid));
		
		f = new JFrame("Griglia Giocatore");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setContentPane(this);
	    f.pack();
	    f.setVisible(true);
	}
	
	/**
	 * Genera un nuovo pannello griglia
	 * 
	 * @param dim - la dimensione della griglia da generare
	 * @param grid - matrice della griglia  in cui creare le celle
	 * 
	 * @return JPanel il pannello generato
	 * */
	private JPanel generateGrid(int dim, JLabel[][] grid) {
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(dim+1, dim+1));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		gridPanel.add(new JLabel());
		for(int i = 0; i < dim; i++) {
			Integer intI = i;
			gridPanel.add(new JLabel(intI.toString()));
		}
		
		for(int i = 0; i < dim; i++) {
			for(int j = -1; j < dim; j++) {
				
				if(j < 0) {
					Integer inti = (Integer)i;
					
					gridPanel.add(new JLabel(inti.toString()));
				}
				else {
					grid[i][j] = new JLabel("");
					grid[i][j].setPreferredSize(new Dimension(25, 25));
					grid[i][j].setHorizontalAlignment(JLabel.CENTER);
					grid[i][j].setVerticalAlignment(JLabel.CENTER);
					grid[i][j].setOpaque(true);
					grid[i][j].setBackground(Color.WHITE);
					grid[i][j].setBorder(border);
					grid[i][j].setName(i+";"+j);
					gridPanel.add(grid[i][j]);
				}
			}
		}
		
		return gridPanel;
	}
	
	/**
	 * inizializza i componenti della finestra con le informazioni per posizionare una nave
	 * */
	private void initJDialogComponents() {
		
		String[] opzOrientamento = {"Verticale", "Orizzontale"};
		this.jDiagcmbOrientamento = new JComboBox<>(opzOrientamento);
		
		Integer[] dimGrid = new Integer[dim];
		for(Integer i = 0; i < dim; i++){
			dimGrid[i] = i;
		}
		
		this.jDiagcmbRiga = new JComboBox<>(dimGrid);
		this.jDiagcmbColonna = new JComboBox<>(dimGrid);
		
		this.jDiagbtnOk = new JButton("OK");
		
		
	}
	
	/**
	 * Crea una finestra per permettere al giocatore di scegliere dove posizionare la nave
	 * 
	 * @param tipiNavi - stringa contenente i nomi dei tipi di nave che l'utente può aggiungere
	 * */
	public void posizioneNave(String[] tipiNavi) {
		
		JDialog d = new JDialog(this.f, "Battleship - Posiziona Nave");
		this.JDiagcmbNavi = new JComboBox<>(tipiNavi);
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		p.add(new JLabel("Posiziona nave"));
		p.add(new JLabel("Scegli Tipo Nave"));
		p.add(this.JDiagcmbNavi);
		p.add(new JLabel("Scegli Riga"));
		p.add(this.jDiagcmbRiga);
		p.add(new JLabel("Scegli Colonna"));
		p.add(this.jDiagcmbColonna);
		p.add(new JLabel("Scegli l'orientamento"));
		p.add(this.jDiagcmbOrientamento);
		p.add(this.jDiagbtnOk);
		d.add(p);
		d.setSize(300, 250);
		d.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		d.setVisible(true);
		
	}
	
	/**
	 * Crea una nuova finestra mostrando al giocatore la stringa fornita
	 * 
	 * @param message - stringa contenente il messaggio da mostrare
	 * */
	public void message(String message) {
		JDialog d = new JDialog(this.f, "Battleship");
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		
		p.add(new JLabel(message), BorderLayout.CENTER);
		
		d.add(p);
		
		d.pack();
		d.setVisible(true);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		CellStatus[][] g = (CellStatus[][])arg1;
		
		for(int i = 0; i < dim; i++) {
			for(int j = 0; j < dim; j++) {
				
				switch(g[i][j]) {
					case CELL_EMPTY: this.grid[i][j].setBackground(Color.WHITE); break;
					case CELL_EMPTY_HIT: {
						this.grid[i][j].setBackground(Color.WHITE);
						this.grid[i][j].setText("X");
					}break;
					case CELL_SHIP: {
						this.grid[i][j].setBackground(Color.DARK_GRAY);
					}break;
					case CELL_SHIP_HIT: {
						this.grid[i][j].setBackground(Color.RED);
						this.grid[i][j].setForeground(Color.WHITE);
						this.grid[i][j].setText("X");
					}
				}
			}
			
		}
		
	}
}
