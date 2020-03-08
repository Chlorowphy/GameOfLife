package source.game;

import java.io.IOException;

/**
 * Board object for the Game of Life
 * A board contains x rows and y columns which are fill with cells
 * @author William Pouts
 * @date 08/03/2020
 * @version 2.0
 * 
 */
public class GameBoard {
	/***********
	 * Private parameters
	 ***********/
	private Cell[][] currentGenerationGridCells;
	private Cell[][] nextGenerationGridCells;
	private int rowCount = 0;
	private int colCount = 0;
	
	public GameBoard(final int NUM_ROW, final int NUM_COL) throws IOException {
		if(NUM_ROW > 0 && NUM_COL > 0) {
			//Create the grid
			currentGenerationGridCells = new Cell[NUM_ROW][NUM_COL];
			nextGenerationGridCells = new Cell[NUM_ROW][NUM_COL];
			rowCount = NUM_ROW;
			colCount = NUM_COL;
			populateGridWithDeadCells();
		}
		else {
			throw new IOException();
		}
	}
	
	/***********
	 * Public functions
	 ***********/
	public void calculateNextGeneration() {
		for(int row = 0; row < rowCount; row++) {
			for(int col = 0; col < colCount; col++) {
				nextGenerationGridCells[row][col].setAlive(livesForTheNextGeneration(row, col));
//		for(Point checkPoint : withAtLeastOneAliveMateCellsList) {
//			int row = checkPoint.x;
//			int col = checkPoint.y;
//		}	
			}
		}
		updateCurrentGenerationGridCells();
	}
	
	public void setCellAlive(final int ROW, final int COL, final boolean IS_ALIVE) {
		if(cellIsInTheGrid(ROW, COL)) {
			currentGenerationGridCells[ROW][COL].setAlive(IS_ALIVE);
		}
	}
	
	public Cell[][] getCurrentGenerationGridCells() {
		return currentGenerationGridCells;
	}
	
	public boolean livesForTheNextGeneration(final int ROW, final int COL) {
		if(cellIsInTheGrid(ROW, COL)) {
			int numberAliveNeighbours = countingAliveNeighbours(ROW, COL);
			if(numberAliveNeighbours == 3 || (numberAliveNeighbours == 2 && currentGenerationGridCells[ROW][COL].isAlive())) {
				return true;
			}
		}
		return false;
	}
	
	public int countingAliveNeighbours(final int ROW, final int COL) {
		int numberAliveNeighbours = 0;
		if(cellIsInTheGrid(ROW, COL)) {
			for(int checkRow = ROW - 1; checkRow <= ROW + 1; checkRow++) {
				for(int checkCol = COL - 1; checkCol <= COL + 1; checkCol ++) {
					if(cellIsInTheGrid(checkRow, checkCol) && currentGenerationGridCells[checkRow][checkCol].isAlive() && !areTheSameCells(checkRow, checkCol, ROW, COL)) {
						numberAliveNeighbours++;
					}
				}
			}
		}
		return numberAliveNeighbours;
	}
	
	public boolean isTheSameGameBoard(final GameBoard OTHER_GAME_BOARD) {
		if((OTHER_GAME_BOARD.getCurrentGenerationGridCells().length != this.getCurrentGenerationGridCells().length) 
				|| OTHER_GAME_BOARD.getCurrentGenerationGridCells()[0].length != this.getCurrentGenerationGridCells()[0].length) {
			return false;
		}
		for(int checkRow = 0; checkRow < rowCount; checkRow++) {
			for(int checkCol = 0; checkCol < colCount; checkCol ++) {
				if(OTHER_GAME_BOARD.cellIsInTheGrid(checkRow, checkCol) && this.cellIsInTheGrid(checkRow, checkCol)
						&& (OTHER_GAME_BOARD.getCurrentGenerationGridCells()[checkRow][checkCol].isAlive() != this.getCurrentGenerationGridCells()[checkRow][checkCol].isAlive())) {
					return false;
				}
			}
		}
		return true;
	}
	
//	/**
//	 * This function is ONLY used by Unit Test
//	 * @return
//	 */
//	public ArrayList<Point> getInterestCellsList() {
//		return withAtLeastOneAliveMateCellsList;
//	}
	
	/***********
	 * Private functions
	 ***********/
	private void populateGridWithDeadCells() {
		for(int row = 0; row < rowCount; row++) {
			for(int col = 0; col < colCount; col++) {
				//By Cell constructor, all new cells are dead
				currentGenerationGridCells[row][col] = new Cell();
				nextGenerationGridCells[row][col] = new Cell();
//				withAtLeastOneAliveMateCellsList = new ArrayList<>(0);
			}
		}
	}
	
	private boolean cellIsInTheGrid(final int CHECK_ROW, final int CHECK_COL) {
		return (CHECK_ROW >= 0 && CHECK_ROW < rowCount && CHECK_COL >= 0 && CHECK_COL < colCount);
	}
	
	private boolean areTheSameCells(final int FIRST_CELL_ROW, final int FIRST_CELL_COL, final int SECOND_CELL_ROW, final int SECOND_CELL_COL) {
		return (FIRST_CELL_ROW == SECOND_CELL_ROW && FIRST_CELL_COL == SECOND_CELL_COL);
	}
	
	private void updateCurrentGenerationGridCells() {
		for(int row = 0; row < rowCount; row++) {
			for(int col = 0; col < colCount; col++) {
				currentGenerationGridCells[row][col].setAlive(nextGenerationGridCells[row][col].isAlive());
			}
		}
	}
	
//	private void updateWithAtLeastOneAliveMateCellsList(final int ROW, final int COL) {
//	if(cellIsInTheGrid(ROW, COL)) {
//		for(int row = ROW - 1; row <= ROW + 1; row ++) {
//			for(int col = COL - 1; col <= COL + 1; col++) {
//				if(cellIsInTheGrid(row, col)) {
//					Point cell = new Point(row, col);
//					if(!withAtLeastOneAliveMateCellsList.contains(cell)) {
//						withAtLeastOneAliveMateCellsList.add(cell);
//					}
//				}
//			}
//		}
//	}
//}
}
