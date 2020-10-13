package upo.battleship.peretto;
import static org.junit.Assert.*;

import org.junit.Test;

public class AIModelTest {
	
	GridAIModel aiModel;
	GridPlayerModel playerModel;
	
	@Test
	public void testAI() {
		playerModel = new GridPlayerModel(10, 10);
		try{
			this.playerModel.placeShip(1, 3, ShipOrientation.HORIZONTAL, 5);
			this.playerModel.placeShip(6, 0, ShipOrientation.VERTICAL, 4);
			this.playerModel.placeShip(4, 7, ShipOrientation.HORIZONTAL, 3);
		}
		catch (IndexOutOfBoundsException e) {
			fail("Posizionamento navi nella grilgia fallito: "+e.getMessage());
		}
		
		aiModel = new GridAIModel(10, 10, playerModel);
		System.out.println("1");
		int[] cella = new int[2];
		while(!playerModel.isTutteNaviAffondate()){
			
			cella = aiModel.newMove();
			System.out.println("Colpita Cella: ("+cella[0]+","+cella[1]+")");
			System.out.println("Press Any Key To Continue...");
			new java.util.Scanner(System.in).nextLine();
		}
	}

}
