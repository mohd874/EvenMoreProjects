package game.test;

import static org.junit.Assert.*;
import game.of.life.Space;

import org.junit.Before;
import org.junit.Test;

public class SpaceWithBlinkerShould {

	private Space space;
	
	@Before
	public void init(){
		space = new Space();
		
		space.getCell(1, 2).setAlive(true);
		space.getCell(2, 2).setAlive(true);
		space.getCell(3, 2).setAlive(true);
	}
	
	@Test
	public void remainTheSameAfterTwoTicks() {
		boolean expected = true;
		
		space.tick();
		space.tick();
		
		boolean result = (space.getCell(1, 2).isAlive() 
					   && space.getCell(2, 2).isAlive() 
					   && space.getCell(3, 2).isAlive());
		
		assertEquals(expected, result);
	}

	@Test
	public void makeCell21AndCell23AliveAfterOneTick() {
		boolean expected = true;
		
		space.tick();
		
		boolean result = (space.getCell(2, 1).isAlive() 
					   && space.getCell(2, 2).isAlive() 
					   && space.getCell(2, 3).isAlive());
		
		assertEquals(expected, result);
	}
}
