package upo.battleship.peretto;

import java.util.Observable;

public class MappaMieNaviModel extends Observable {
	
	protected String testValue;
	
	public MappaMieNaviModel() {
		super();
		
	}
	
	public void setValue(String v) {
		this.testValue = v;
		this.setChanged();
		this.notifyObservers(v);
	}
	
}
