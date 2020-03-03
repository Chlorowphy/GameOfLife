package source;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
//import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.ArrayList;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Board frame for the Game of Life. Composed by n Cells
 * For each generation, calculate the population of the cells with the 4 principles
 * @author William Pouts
 * @date 01/03/2020
 * @version 1.0
 * 
 */
public class BoardFrame extends JFrame {
	/***********
	 * Private parameters
	 ***********/
	private static final long serialVersionUID = 1L;
	
	private Cell[][] currentGenerationGridCells;
	private Cell[][] nextGenerationGridCells;
	private int rowCount = 0;
	private int colCount = 0;
	private JPanel board;
	
//	private ArrayList<Point> withAtLeastOneAliveMateCellsList;
 
	/**
	 * Constructor
	 * @param NUM_ROW
	 * @param NUM_COL
	 */
	public BoardFrame(final int NUM_ROW, final int NUM_COL) throws IOException  {
		//Check the input value
		if(NUM_ROW > 0 && NUM_COL > 0) {
			//Create the Frame
			final String TITLE_TXT = "Conway’s game of life";
			setTitle(TITLE_TXT);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			final int DEFAULT_HIGH = 700;
			final int DEFAULT_WIDTH = DEFAULT_HIGH * 16 / 9;
			final Dimension DEFAULT_DIMENSION = new Dimension(DEFAULT_WIDTH, DEFAULT_HIGH);
			setSize(DEFAULT_DIMENSION);
			setMinimumSize(DEFAULT_DIMENSION);
			
			//Create the grid
			currentGenerationGridCells = new Cell[NUM_ROW][NUM_COL];
			nextGenerationGridCells = new Cell[NUM_ROW][NUM_COL];
			rowCount = NUM_ROW;
			colCount = NUM_COL;
			populateGridWithDeadCells();
			
			//Set the components of the Frame
			JPanel textPanel = new JPanel();
			textPanel.setLayout(new GridLayout());
			textPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			add(textPanel, BorderLayout.NORTH);
			
			//Add the command texts
			final Font FONT = new Font("Consolas", Font.BOLD, 18);
			final String SELECT_ALIVE_CELLS_TXT = "1. - Select alive cells";
			JLabel selectAliveCellsLabel = new JLabel(SELECT_ALIVE_CELLS_TXT);
			selectAliveCellsLabel.setFont(FONT);
			selectAliveCellsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			textPanel.add(selectAliveCellsLabel, BorderLayout.WEST);
			
			final String START_GAME_TXT = "2. - Press Enter to calcule the next generation";
			JLabel startGameLabel = new JLabel(START_GAME_TXT);
			startGameLabel.setFont(FONT);
			startGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			textPanel.add(startGameLabel, BorderLayout.EAST);
			
			//Set the panel for the cells
			board = new JPanel();
			board.setLayout(new GridLayout(NUM_ROW, NUM_COL));
			board.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			updateDisplayBoard();
			add(board);
			
			addKeyListener(new BoardKeyListener());
			
			setLocationRelativeTo(null);
			setVisible(true);
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
		updateDisplayBoard();
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
	
	/**
	 * This function is ONLY used by the Unit Tests.
	 * @param ROW
	 * @param COL
	 * @param IS_ALIVE
	 */
	public void setNewStateCell(final int ROW, final int COL, final boolean IS_ALIVE) {
		if(cellIsInTheGrid(ROW, COL)) {
			currentGenerationGridCells[ROW][COL].setAlive(IS_ALIVE);
//			if(IS_ALIVE) {
//				updateWithAtLeastOneAliveMateCellsList(ROW, COL);
//			}
		}
	}
	
	/**
	 * This function is ONLY used by the Unit Tests.
	 * @return
	 */
	public int getNumCurrentAliveCells() {		
		int numAliveCells = 0;
		for(int row = 0; row < rowCount; row++) {
			for(int col = 0; col < colCount; col++) {
				Cell cell = currentGenerationGridCells[row][col];
				if(cell.isAlive()) {
					numAliveCells++;
				}
			}
		}
		return numAliveCells;
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
	
	private void updateDisplayBoard() {
		board.removeAll();
		board.updateUI();
		for(int row = 0; row < rowCount; row++) {
			for(int col = 0; col < colCount; col++) {
				board.add(currentGenerationGridCells[row][col]);
			}
		}	
	}
	
//	private void updateWithAtLeastOneAliveMateCellsList(final int ROW, final int COL) {
//		if(cellIsInTheGrid(ROW, COL)) {
//			for(int row = ROW - 1; row <= ROW + 1; row ++) {
//				for(int col = COL - 1; col <= COL + 1; col++) {
//					if(cellIsInTheGrid(row, col)) {
//						Point cell = new Point(row, col);
//						if(!withAtLeastOneAliveMateCellsList.contains(cell)) {
//							withAtLeastOneAliveMateCellsList.add(cell);
//						}
//					}
//				}
//			}
//		}
//	}
	
	/***********
	 * Private classes
	 ***********/
	private class BoardKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent event) {
			calculateNextGeneration();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}		
	}
}
