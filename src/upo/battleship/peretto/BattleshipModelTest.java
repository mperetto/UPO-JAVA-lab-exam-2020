package upo.battleship.peretto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BattleshipModelTest {
	
	private BattleshipModel model;
	
	@Before
	public void setup() {
		this.model = new BattleshipModel(10, 2, 2, 2);
	}

	@Test
	public void testAddShip() {
		
		try{
			model.addShip(new ShipModel(1, 3, ShipType.PORTAEREI, ShipOrientation.HORIZONTAL));
			model.addShip(new ShipModel(1, 9, ShipType.SOTTOMARINO, ShipOrientation.VERTICAL));
			model.addShip(new ShipModel(2, 0, ShipType.PORTAEREI, ShipOrientation.VERTICAL));
			model.addShip(new ShipModel(5, 9, ShipType.INCROCIATORE, ShipOrientation.VERTICAL));
			model.addShip(new ShipModel(6, 3, ShipType.INCROCIATORE, ShipOrientation.HORIZONTAL));
			model.addShip(new ShipModel(9, 0, ShipType.SOTTOMARINO, ShipOrientation.HORIZONTAL));
		}
		catch(IllegalStateException e){
			fail("Aggiunta navi fallito Errore: "+e.toString());
		}
		catch(Exception e){
			fail("Aggiunta navi fallito Errore: "+e.toString());
		}
		
	}
	
	@Test
	public void testAddShipOverLimit() {
		
		try{
			model.addShip(new ShipModel(1, 9, ShipType.SOTTOMARINO, ShipOrientation.VERTICAL));
			model.addShip(new ShipModel(9, 0, ShipType.SOTTOMARINO, ShipOrientation.HORIZONTAL));
			model.addShip(new ShipModel(3, 0, ShipType.SOTTOMARINO, ShipOrientation.HORIZONTAL));
			fail("Errore Superato limite di sottomarini inseribili");
		}
		catch(IllegalStateException e){}
		
		model = new BattleshipModel(10, 2, 2, 2);
		
		try{
			model.addShip(new ShipModel(1, 9, ShipType.PORTAEREI, ShipOrientation.VERTICAL));
			model.addShip(new ShipModel(9, 0, ShipType.PORTAEREI, ShipOrientation.HORIZONTAL));
			model.addShip(new ShipModel(3, 0, ShipType.PORTAEREI, ShipOrientation.HORIZONTAL));
			fail("Errore Superato limite di portaerei inseribili");
		}
		catch(IllegalStateException e){}
		
		model = new BattleshipModel(10, 2, 2, 2);
		
		try{
			model.addShip(new ShipModel(1, 9, ShipType.INCROCIATORE, ShipOrientation.VERTICAL));
			model.addShip(new ShipModel(9, 0, ShipType.INCROCIATORE, ShipOrientation.HORIZONTAL));
			model.addShip(new ShipModel(3, 0, ShipType.INCROCIATORE, ShipOrientation.HORIZONTAL));
			fail("Errore Superato limite di incrociatori inseribili");
		}
		catch(IllegalStateException e){}
		
		model = new BattleshipModel(10, 2, 2, 0);
		
		try{
			model.addShip(new ShipModel(1, 9, ShipType.INCROCIATORE, ShipOrientation.VERTICAL));
			fail("Errore Superato limite di incrociatori inseribili");
		}
		catch(IllegalStateException e){}
	}

	@Test
	public void testGetGridPlayerModel() {
		assert(this.model.getGridPlayerModel() != null && this.model.getGridPlayerModel() instanceof GridPlayerModel);
	}

	@Test
	public void testGetGridAIModel() {
		assert(this.model.getGridAIModel() != null && this.model.getGridAIModel() instanceof GridAIModel);
	}

}
