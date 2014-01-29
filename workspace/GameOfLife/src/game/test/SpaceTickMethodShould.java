package game.test;

import static org.junit.Assert.*;

import java.util.List;

import game.of.life.Cell;
import game.of.life.Space;

import org.junit.Before;
import org.junit.Test;

public class SpaceTickMethodShould extends BaseTest{

	private Space space;
	private Cell cellR2C2;
	
	@Before
	public void init(){
		space = new Space();
		cellR2C2 = space.getCell(2, 2);
	}
	
	@Test
	public void killCellIfMoreThanThreeAdjacentsAreAlive(){
		boolean expected = false;
		
		cellR2C2.setAlive(true);
		
		List<Cell> cells = space.getCells();
		makeNCellsAlive(cells, cells.size());
		
		space.tick();
		
		assertEquals(expected, cellR2C2.isAlive());
	}

	@Test
	public void killCellIfLessThanTwoAdjacentsAreAlive(){
		boolean expected = false;
		
		cellR2C2.setAlive(true);
		
		List<Cell> adjacentCells = space.getAdjacentsFor(cellR2C2);
		makeNCellsAlive(adjacentCells, 1);
				
		space.tick();
		
		assertEquals(expected, cellR2C2.isAlive());
	}

	@Test
	public void reviveCellIfThreeAdjacentsAreAlive(){
		boolean expected = true;
		
		cellR2C2.setAlive(false);
		
		List<Cell> adjacentCells = space.getAdjacentsFor(cellR2C2);
		makeNCellsAlive(adjacentCells, 3);
		
		space.tick();
		
		assertEquals(expected, cellR2C2.isAlive());
	}

}
