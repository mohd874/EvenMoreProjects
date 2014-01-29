package game.of.life;

public class Cell {
	
	private boolean alive;

	public Cell() {
		this(false);
	}
	
	public Cell(Cell aCell){
		this(aCell.isAlive());
	}
	
	public Cell(boolean alive) {
		this.alive = alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isAlive() {
		return alive;
	}
}
