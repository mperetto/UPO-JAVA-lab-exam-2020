package upo.observer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GridView extends JPanel implements Observer{
	
	private ObserverController c;
	private ObserverModel m;
	
	JButton btnTest;
	JButton btnSalvaPartita, btnNuovaPartita;
	private JLabel[][] gridPlayer, gridEnemy;
	JLabel lblTempo;
	
	//public GridView(ObserverController c, ObserverModel m) {
	public GridView(ObserverModel m) {
		super();
		
		this.m = m;
		
		this.m.addObserver(this);
		
		this.gridPlayer = new JLabel[10][10];
		this.btnTest = new JButton("Click!!");
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(this.generateGrid(10, gridPlayer));
		this.add(btnTest);
		
		JFrame f = new JFrame("finestra griglia");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setContentPane(this);
	    f.pack();
	    f.setVisible(true);
	}
	
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
					gridPanel.add(grid[i][j]);
				}
			}
		}
		
		return gridPanel;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		int[][] g = (int[][])arg1;
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.print(g[i][j]);
				switch(g[i][j]) {
					case 0: this.gridPlayer[i][j].setBackground(Color.WHITE); break;
					case 1: {
						this.gridPlayer[i][j].setBackground(Color.WHITE);
						this.gridPlayer[i][j].setText("X");
					}break;
					case 2: {
						this.gridPlayer[i][j].setBackground(Color.DARK_GRAY);
					}break;
					case 3: {
						this.gridPlayer[i][j].setBackground(Color.RED);
						this.gridPlayer[i][j].setForeground(Color.WHITE);
						this.gridPlayer[i][j].setText("X");
					}
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		ObserverModel m = new ObserverModel();
		GridView v = new GridView(m);
		ObserverController c = new ObserverController(v, m);
		
	}

}
