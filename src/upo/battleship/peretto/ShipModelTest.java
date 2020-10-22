package upo.battleship.peretto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShipModelTest {
	
	ShipModel ship;
	
	@Before
	public void setup() {
		ship = new ShipModel(0, 1, ShipType.SOTTOMARINO, ShipOrientation.VERTICAL);
	}
	
	@Test
	public void testShipModel() {
		assert(ship != null);
		assert(ship instanceof ShipModel);
	}

	@Test
	public void testGetType() {
		assertEquals(ship.getType(), ShipType.SOTTOMARINO);
	}

	@Test
	public void testGetOrientation() {
		assertEquals(ship.getOrientation(), ShipOrientation.VERTICAL);
	}

	@Test
	public void testGetPositionRow() {
		assertEquals(ship.getPositionRow(), 0);
	}

	@Test
	public void testGetPositionCol() {
		assertEquals(ship.getPositionCol(), 1);
	}

	@Test
	public void testGetDimension() {
		assertEquals(ship.getDimension(), 3);
	}

}
