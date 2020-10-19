package upo.observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class ObserverController implements ActionListener{
	
	private GridView v;
	private ObserverModel m;
	
	public ObserverController(GridView v, ObserverModel m) {
		this.v = v;
		this.m = m;
		
		v.btnTest.addActionListener(this);
		
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				v.gridPlayer[i][j].addMouseListener(
						new MouseAdapter() {
					        public void mouseClicked(MouseEvent e) {
					        	
					        	JLabel l = (JLabel)e.getSource();
					        	String labelName = l.getName();
					        	String[] splitted = labelName.split(";");
					        	Integer r = Integer.parseInt(splitted[0]), c = Integer.parseInt(splitted[1]);
					        	press(l.getName());
					        	v.gridPlayer[r][c].removeMouseListener(this);
					        }
					    }
				);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		m.changeGrid();
	}
	
	private void press(String s){
		String[] splittedString = s.split(";");
		Integer r = Integer.parseInt(splittedString[0]), c = Integer.parseInt(splittedString[1]);
		
		
		System.out.println("Premuto "+s);
		System.out.println(r + " " + c);
		m.press(r, c);
	}

}
