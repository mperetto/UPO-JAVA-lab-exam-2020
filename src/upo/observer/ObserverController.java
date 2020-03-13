package upo.observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObserverController implements ActionListener {
	
	private GridView v;
	private ObserverModel m;
	
	public ObserverController(GridView v, ObserverModel m) {
		this.v = v;
		this.m = m;
		
		v.btnTest.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		m.changeGrid();
	}

}
