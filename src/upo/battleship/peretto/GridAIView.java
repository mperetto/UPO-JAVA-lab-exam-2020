package upo.battleship.peretto;

import java.awt.Color;
import java.util.Observable;

public class GridAIView extends GridView{
	
	/**
	 * Crea una nuova vista per la griglia AI
	 * 
	 * @param m - il modello che gestisce la griglia
	 * @param dim - la dimensione della griglia da creare
	 * */
	public GridAIView(GridModel m, int dim) {
		super(m, dim);
		this.f.setTitle("Griglia AI");
	}
	
	/**
	 * Aggiorna la griglia visualizzata mostrando solo lo stato delle celle colpite
	 * */
	@Override
	public void update(Observable arg0, Object arg1) {
		
		CellStatus[][] g = (CellStatus[][])arg1;
		
		for(int i = 0; i < dim; i++) {
			for(int j = 0; j < dim; j++) {
				
				switch(g[i][j]) {
					case CELL_EMPTY_HIT: {
						this.grid[i][j].setBackground(Color.WHITE);
						this.grid[i][j].setText("X");
					}break;
					case CELL_SHIP_HIT: {
						this.grid[i][j].setBackground(Color.RED);
						this.grid[i][j].setForeground(Color.WHITE);
						this.grid[i][j].setText("X");
					}break;
					default: {
						this.grid[i][j].setBackground(Color.WHITE);
					}break;
				}
			}
			
		}
	}
	
}
