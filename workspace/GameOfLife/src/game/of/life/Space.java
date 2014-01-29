package game.of.life;

import java.util.ArrayList;
import java.util.List;

public class Space {

	private Cell[][] cells;
	private Cell[][] buffer;
	public Space() {
		this(5, 5);
	}

	public Space(int width, int height) {
		cells = new Cell[width][height];

		for (int i = 0; i < cells.length; i++) {
			Cell[] row = cells[i];
			for (int j = 0; j < row.length; j++) {
				row[j] = new Cell();
			}
		}
		
		buffer = this.cloneCells();
	}

	public int getWidth() {
		return cells.length;
	}

	public int getHeight() {
		return cells[0].length;
	}

	public List<Cell> getCells() {
		List<Cell> result = new ArrayList<Cell>(getWidth() * getHeight());
		for (int i = 0; i < cells.length; i++) {
			Cell[] row = cells[i];
			for (int j = 0; j < row.length; j++) {
				Cell cell = row[j];
				result.add(cell);
			}
		}
		return result;
	}

	public Cell getCell(int row, int col) {
		return this.cells[row][col];
	}

	public void setCell(Cell cell, int row, int col) {
		this.cells[row][col] = cell;
	}

	public void tick() {
		int countAlive = 0;
		
		for (int i = 0; i < cells.length; i++) {
			Cell[] row = cells[i];
			for (int j = 0; j < row.length; j++) {
				Cell cell = row[j];
				countAlive = countLiveAdjacents(cell);
				
				Cell bCell = buffer[i][j];
				
				willLiveOrDie(countAlive, bCell);
			}
		}
		
		setCellsLives(buffer);
	}

	private int countLiveAdjacents(Cell cell) {
		List<Cell> adjacents;
		int countAlive;
		adjacents = this.getAdjacentsFor(cell);
		
		countAlive = countLiveCellsInList(adjacents);
		return countAlive;
	}

	private void willLiveOrDie(int adjacentsAlive, Cell cell) {
		if(adjacentsAlive == 3){
			cell.setAlive(true);
		}else if(adjacentsAlive > 3 || adjacentsAlive < 2){
			cell.setAlive(false);
		}
	}

	private void setCellsLives(Cell[][] aCells) {
		for (int i = 0; i < aCells.length; i++) {
			Cell[] row = aCells[i];
			for (int j = 0; j < row.length; j++) {
				Cell cell = row[j];
				cells[i][j].setAlive(cell.isAlive());
			}
		}
	}

	private Cell[][] cloneCells() {
		Cell[][] clone = new Cell [getWidth()][getHeight()];
		for (int i = 0; i < clone.length; i++) {
			Cell[] row = clone[i];
			for (int j = 0; j < row.length; j++) {
				row[j] = new Cell(cells[i][j]);
			}
		}
		return clone;
	}

	public int countLiveCellsInList(List<Cell> adjacents) {
		int result = 0;
		for (Cell cell : adjacents) {
			if(cell != null && cell.isAlive()){
				result++;
			}
		}
		return result;
	}

	public List<Cell> getAdjacentsFor(Cell cell) {
		for (int i = 0; i < cells.length; i++) {
			Cell[] row = cells[i];
			for (int j = 0; j < row.length; j++) {
				if (row[j].equals(cell)) {
					return this.getAdjacentsFor(i, j);
				}
			}
		}
		return null;
	}

	public List<Cell> getAdjacentsFor(int row, int col) {
		List<Cell> result = new ArrayList<Cell>(8);

		result.add(this.getUpperLeftCell(row, col));
		result.add(this.getUpperCell(row, col));
		result.add(this.getUpperRightCell(row, col));
		result.add(this.getRightCell(row, col));
		result.add(this.getLowerRightCell(row, col));
		result.add(this.getLowerCell(row, col));
		result.add(this.getLowerLeftCell(row, col));
		result.add(this.getLeftCell(row, col));

		return result;
	}

	private Cell getLeftCell(int row, int col) {
		if (col > 0) {
			return getCell(row, col - 1);
		}
		return null;
	}

	private Cell getLowerCell(int row, int col) {
		if (row < this.getHeight() - 1) {
			return getCell(row + 1, col);
		}
		return null;
	}

	private Cell getRightCell(int row, int col) {
		if (col < this.getWidth() - 1) {
			return getCell(row, col + 1);
		}
		return null;
	}

	private Cell getUpperCell(int row, int col) {
		if (row > 0) {
			return getCell(row - 1, col);
		}
		return null;
	}

	private Cell getLowerLeftCell(int row, int col) {
		if (this.getLowerCell(row, col) != null
		&& this.getLeftCell(row, col) != null) {
			return getCell(row + 1, col - 1);
		}
		return null;
	}

	private Cell getLowerRightCell(int row, int col) {
		if (this.getRightCell(row, col) != null
		&& this.getLowerCell(row, col) != null) {
			return getCell(row + 1, col + 1);
		}
		return null;
	}

	private Cell getUpperRightCell(int row, int col) {
		if (this.getUpperCell(row, col) != null
		&& this.getRightCell(row, col) != null) {
			return getCell(row - 1, col + 1);
		}
		return null;
	}

	private Cell getUpperLeftCell(int row, int col) {
		if (this.getUpperCell(row, col) != null
		&& this.getLeftCell(row, col) != null) {
			return getCell(row - 1, col - 1);
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cells.length; i++) {
			Cell[] row = cells[i];
			for (int j = 0; j < row.length; j++) {
				Cell cell = row[j];
				sb.append("["+(cell.isAlive() ? 1 : 0)+"] ");
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

}
