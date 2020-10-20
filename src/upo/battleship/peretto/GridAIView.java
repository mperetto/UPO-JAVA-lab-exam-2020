package upo.battleship.peretto;

import java.awt.Color;
import java.util.Observable;

public class GridAIView extends GridView{
	
	public GridAIView(GridModel m, int dim) {
		super(m, dim);
		this.f.setTitle("Griglia AI");
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		CellStatus[][] g = (CellStatus[][])arg1;
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.print(g[i][j]);
				switch(g[i][j]) {
					case CELL_EMPTY_HIT: {
						this.grid[i][j].setBackground(Color.WHITE);
						this.grid[i][j].setText("X");
					}break;
					case CELL_SHIP_HIT: {
						this.grid[i][j].setBackground(Color.RED);
						this.grid[i][j].setForeground(Color.WHITE);
						this.grid[i][j].setText("X");
					}
					default: {
						this.grid[i][j].setBackground(Color.WHITE);
					}break;
				}
			}
			System.out.println();
		}
	}
	
}
