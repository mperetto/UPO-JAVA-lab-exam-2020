package upo.battleship.peretto;
import static org.junit.Assert.*;

import java.util.LinkedList;

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
		aiModel.setRandomSeed(1);
		
		int[] cella = new int[2];
		int cont = 0, numeroMosse = 45;
		
		while(!playerModel.isTutteNaviAffondate()){
			
			cella = aiModel.newMove();
			
			cont++;
		}
		
		assertEquals(cont, numeroMosse);
		
	}
	
	@Test
	public void testSimulaPartita() {
		
		BattleshipModel model = new BattleshipModel(10, 1, 1, 1);
		
		playerModel = model.getGridPlayerModel();
		aiModel = model.getGridAIModel();
		
		posiziona(playerModel, aiModel);
		
		int mosse = 0, vincitore = 0;
		aiModel.setRandomSeed(1);
		
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				model.hitCell(i, j);
				mosse++;
				vincitore = model.checkWin();
				if(vincitore != 0) break;
			}
			if(vincitore != 0) break;
		}
		
		assertEquals(vincitore, 2);
		assertEquals(mosse, 45);
		
		model = new BattleshipModel(10, 1, 1, 1);
		
		playerModel = model.getGridPlayerModel();
		aiModel = model.getGridAIModel();
		
		posiziona(playerModel, aiModel);
		
		aiModel.setRandomSeed(1);
		LinkedList<int[]> l = new LinkedList<>();
		int[] cella = new int[2];
		
		cella[0] = 1; cella[1] = 3;
		l.add(cella);
		cella = new int[2];
		cella[0] = 1; cella[1] = 4;
		l.add(cella);
		cella = new int[2];
		cella[0] = 1; cella[1] = 5;
		l.add(cella);
		cella = new int[2];
		cella[0] = 1; cella[1] = 6;
		l.add(cella);
		cella = new int[2];
		cella[0] = 1; cella[1] = 7;
		l.add(cella);
		cella = new int[2];
		
		cella[0] = 6; cella[1] = 0;
		l.add(cella);
		cella = new int[2];
		cella[0] = 7; cella[1] = 0;
		l.add(cella);
		cella = new int[2];
		cella[0] = 8; cella[1] = 0;
		l.add(cella);
		cella = new int[2];
		cella[0] = 9; cella[1] = 0;
		l.add(cella);
		cella = new int[2];
		
		cella[0] = 4; cella[1] = 7;
		l.add(cella);
		cella = new int[2];
		cella[0] = 4; cella[1] = 8;
		l.add(cella);
		cella = new int[2];
		cella[0] = 4; cella[1] = 9;
		l.add(cella);
		
		
		vincitore = 0;
		while(vincitore == 0){
			cella = l.removeFirst();
			model.hitCell(cella[0], cella[1]);
			vincitore = model.checkWin();
		}
		
		assertEquals(vincitore, 1);
	}
	
	private void posiziona(GridPlayerModel player, GridAIModel ai) {
		try{
			player.placeShip(1, 3, ShipOrientation.HORIZONTAL, 5);
			player.placeShip(6, 0, ShipOrientation.VERTICAL, 4);
			player.placeShip(4, 7, ShipOrientation.HORIZONTAL, 3);
		}
		catch (IndexOutOfBoundsException e) {
			fail("Posizionamento navi nella grilgia fallito: "+e.getMessage());
		}
		
		try{
			ai.placeShip(1, 3, ShipOrientation.HORIZONTAL, 5);
			ai.placeShip(6, 0, ShipOrientation.VERTICAL, 4);
			ai.placeShip(4, 7, ShipOrientation.HORIZONTAL, 3);
		}
		catch (IndexOutOfBoundsException e) {
			fail("Posizionamento navi nella grilgia fallito: "+e.getMessage());
		}
	}

}
