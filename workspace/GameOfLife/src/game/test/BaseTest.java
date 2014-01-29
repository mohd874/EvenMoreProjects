package game.test;

import game.of.life.Cell;
import game.of.life.Space;

import java.util.List;

public class BaseTest{

	protected void makeNCellsAlive(List<Cell> adjacentCells, int requiredLivingCells) {
		for (int i = 0; i < adjacentCells.size(); i++) {
			Cell cell = adjacentCells.get(i);
			
			if(cell != null){
				cell.setAlive(false);
				if(requiredLivingCells > 0){
					cell.setAlive(true);
				}
				requiredLivingCells--;
			}
		}
	}

	protected int getSpaceDiminsion(Space space) {
		return space.getWidth()*space.getHeight();
	}
}
