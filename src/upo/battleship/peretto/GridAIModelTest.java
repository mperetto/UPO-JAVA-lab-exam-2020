package upo.battleship.peretto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GridAIModelTest {
	
	GridPlayerModel playerModel;
	GridAIModel aiModel;
	
	
	@Before
	public void setup() {
		this.playerModel = new GridPlayerModel(10, 10);
		this.aiModel = new GridAIModel(10, 10, playerModel);
	}
	
	@Ignore
	public void testGridAIModel() {
		
		this.playerModel = new GridPlayerModel(10, 10);
		this.aiModel = new GridAIModel(10, 10, playerModel);
				
	}

	@Test
	public void testNewMove() {
		//this.aiModel.newMove()
	}

	@Ignore
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlaceShip() {
		
		try{
			this.aiModel.placeShip(1, 3, ShipOrientation.HORIZONTAL, 5);
			System.out.println("t1");
			this.aiModel.placeShip(6, 3, ShipOrientation.HORIZONTAL, 4);
			System.out.println("t2");
			this.aiModel.placeShip(9, 0, ShipOrientation.HORIZONTAL, 3);
			System.out.println("t3");
			this.aiModel.placeShip(2, 0, ShipOrientation.VERTICAL, 5);
			System.out.println("t4");
			this.aiModel.placeShip(1, 9, ShipOrientation.VERTICAL, 3);
			System.out.println("t5");
			this.aiModel.placeShip(5, 9, ShipOrientation.VERTICAL, 4);
			System.out.println("t6");
		}
		catch (IndexOutOfBoundsException e) {
			fail("Posizionamento navi nella grilgia fallito: "+e.getMessage());
		}
		catch(Exception e){
			fail("Posizionamento navi nella griglia fallito con eccezzione: "+e.toString());
		}
		
		try{
			this.aiModel.placeShip(3, 7, ShipOrientation.HORIZONTAL, 4);
			fail("Il posizionamento della nave doveva fallire, nave esce dalla griglia in orizzontale");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try{
			this.aiModel.placeShip(8, 6, ShipOrientation.VERTICAL, 4);
			fail("Il posizionamento della nave doveva fallire, nave esce dalla griglia in verticale");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try{
			this.aiModel.placeShip(8, 6, ShipOrientation.HORIZONTAL, 4);
			fail("Il posizionamento della nave doveva fallire, nave occupa posizione di altra nave presente");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try{
			this.aiModel.placeShip(1, 8, ShipOrientation.VERTICAL, 4);
			fail("Il posizionamento della nave doveva fallire, le navi non possono toccarsi.");
		}
		catch (IndexOutOfBoundsException e) {}
	}

	@Test
	public void testHitCell() {
		fail("Not yet implemented");
	}

}
