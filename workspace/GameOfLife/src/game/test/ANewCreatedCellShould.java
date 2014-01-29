package game.test;

import static org.junit.Assert.*;
import game.of.life.Cell;

import org.junit.Before;
import org.junit.Test;

public class ANewCreatedCellShould {

	Cell cell;
	
	@Before
	public void init(){
		cell = new Cell();
	}
	
	@Test
	public void beDeadUponCreation(){
		boolean expected = false;
		assertEquals(expected, cell.isAlive());
	}
	
	@Test
	public void beAbleToSetItsLifeStateToFalse() {
		boolean expected = false;
		cell.setAlive(false);
		
		assertEquals(expected, cell.isAlive());
	}
	
	@Test
	public void beAbleToSetItsLifeStateToTrue() {
		boolean expected = true;
		cell.setAlive(true);
		
		assertEquals(expected, cell.isAlive());
	}
	
}
