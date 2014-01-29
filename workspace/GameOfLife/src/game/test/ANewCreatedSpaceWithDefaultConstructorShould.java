package game.test;

import static org.junit.Assert.*;
import game.of.life.Cell;
import game.of.life.Space;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ANewCreatedSpaceWithDefaultConstructorShould extends BaseTest{

	private Space space;
	
	@Before
	public void init(){
		space = new Space();
	}

	@Test
	public void haveHeightOfFive() {
		assertEquals(5, space.getHeight());
	}

	@Test
	public void haveWidthOfFive() {
		assertEquals(5, space.getWidth());
	}
	
	@Test
	public void beAbleToReturnListOfAllCells(){
		int expected = getSpaceDiminsion(space);
		assertEquals(expected, space.getCells().size());
	}
	
	@Test
	public void haveNotNullCells(){
		List<Cell> cells = space.getCells();
		int expected = getSpaceDiminsion(space);
		int result = 0;
		
		for (Cell cell : cells) {
			if(cell != null){
				result++;
			}
		}
		assertEquals(expected, result);
	}
	
	@Test
	public void beAbleToReturnACellIfRowAndColumnWereGiven(){
		Cell cell = space.getCell(2,2);
		assertNotNull(cell);
	}
	
	@Test
	public void beAbleToSetACellIfRowAndColumnWereGiven(){
		Cell expected = new Cell();
		space.setCell(expected,2,2);
		Cell cell = space.getCell(2, 2);
		
		assertEquals(expected, cell);
	}
	
	@Test
	public void beAbleToReturnAListOfAdjacentCells() {
		int expected = 8;
		Cell cellR2C2 = space.getCell(2, 2);
		int result = space.getAdjacentsFor(cellR2C2).size();
		assertEquals(expected, result);
	}
	
	@Test 
	public void beAbleToCountLiveCells(){
		int expected = 5;
		Cell cellR2C2 = space.getCell(0, 2);
		List<Cell> adjacentCells = space.getAdjacentsFor(cellR2C2);
		
		makeNCellsAlive(adjacentCells, 5);
		
		int result = space.countLiveCellsInList(adjacentCells);
		
		assertEquals(expected, result);
	}
	
}
