package upo.battleship.peretto;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class TestObserverView extends JPanel implements Observer {

	MappaMieNaviModel m;
	BattleshipController c;
	
	JLabel label;
	JButton btnButton;
	
	public TestObserverView(MappaMieNaviModel m, BattleshipController c) {
		super();
		this.c = c;
		this.m = m;
		
		this.m.addObserver(this);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.label = new JLabel("Primo Messaggio");
		this.add(label);
		this.btnButton = new JButton("Click");
		this.add(btnButton);
		btnButton.addActionListener(c);
		
		JFrame f = new JFrame("Frame");
		f.setContentPane(this);
		f.pack();
	    f.setVisible(true);
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.label.setText((String)arg);
	}
	
	/*public static void main(String[] args) {
		MappaMieNaviModel m = new MappaMieNaviModel();
		//BattleshipController c = new BattleshipController(m);
		
		TestObserverView v = new TestObserverView(m, c);
	}*/
	
}
