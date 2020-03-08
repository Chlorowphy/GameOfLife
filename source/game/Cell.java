package source.game;

/**
 * Cell object for the Board
 * A cell is either dead or alive
 * @author William Pouts
 * @date 08/03/2020
 * @version 2.0
 * 
 */
public class Cell {
	/***********
	 * Private parameters
	 ***********/
	private boolean isAlive = false;
	
	/**
	 * Constructor of Cell
	 */
	public Cell() {
	}
	
	/***********
	 * Public functions
	 ***********/
	public void setAlive(boolean alive) {
		isAlive = alive;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
}
