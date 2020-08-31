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
			this.aiModel.placeShip(6, 3, ShipOrientation.HORIZONTAL, 4);
			this.aiModel.placeShip(9, 0, ShipOrientation.HORIZONTAL, 3);
			this.aiModel.placeShip(2, 0, ShipOrientation.VERTICAL, 5);
			this.aiModel.placeShip(1, 9, ShipOrientation.VERTICAL, 3);
			this.aiModel.placeShip(5, 9, ShipOrientation.VERTICAL, 4);
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
		try{
			this.aiModel.placeShip(1, 3, ShipOrientation.HORIZONTAL, 5);
			this.aiModel.placeShip(6, 3, ShipOrientation.HORIZONTAL, 4);
			this.aiModel.placeShip(9, 0, ShipOrientation.HORIZONTAL, 3);
			this.aiModel.placeShip(2, 0, ShipOrientation.VERTICAL, 5);
			this.aiModel.placeShip(1, 9, ShipOrientation.VERTICAL, 3);
			this.aiModel.placeShip(5, 9, ShipOrientation.VERTICAL, 4);
		}
		catch (IndexOutOfBoundsException e) {
			fail("Posizionamento navi nella grilgia fallito: "+e.getMessage());
		}
		
		try{
			this.aiModel.hitCell(-1, 2);
			fail("Il colpo doveva fallire, riga matrice fornita negativa");
		}
		catch (IndexOutOfBoundsException e){}
		
		try{
			this.aiModel.hitCell(1, -2);
			fail("Il colpo doveva fallire, colonna matrice fornita negativa");
		}
		catch (IndexOutOfBoundsException e){}
		
		try{
			this.aiModel.hitCell(-1, -2);
			fail("Il colpo doveva fallire, riga e colonna matrice fornite negative");
		}
		catch (IndexOutOfBoundsException e){}
		
		try{
			this.aiModel.hitCell(10, 10);
			fail("Il colpo doveva fallire, riga e colonna matrice fornite fuori range");
		}
		catch (IndexOutOfBoundsException e){}
		
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				try{
					this.aiModel.hitCell(i, j);
				}
				catch (Exception e){
					fail("Test fallito alla cella ("+i+";"+j+"): "+e.toString());
				}
			}
		}
	}
}
