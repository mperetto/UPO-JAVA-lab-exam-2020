package upo.battleship.peretto;

import static org.junit.Assert.*;

import org.junit.Test;

public class GridPlayerModelTest {

	@Test
	public void testNewMove() {
		GridPlayerModel player = new GridPlayerModel(10, 10);
		
		try{
			player.newMove();
			fail("La newmove doveva causare una eccezzione.");
		}
		catch(UnsupportedOperationException e){
			
		}
	}

}
