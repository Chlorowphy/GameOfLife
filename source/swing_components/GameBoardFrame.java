package source.swing_components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import source.game.GameBoard;

/**
 * Frame in order to display the board of the game
 * @author William Pouts
 * @date 08/03/2020
 * @version 2.0
 * 
 */
public class GameBoardFrame extends JFrame {
	/***********
	 * Private parameters
	 ***********/
	private static final long serialVersionUID = 1L;
	private JPanel gameBoardPanel;
	private GameBoard gameBoard;
	private int numRow;
	private int numCol;
 
	/**
	 * Constructor
	 * @param NUM_ROW
	 * @param NUM_COL
	 */
	public GameBoardFrame(final GameBoard INPUT_GAME_BOARD) {
		gameBoard = INPUT_GAME_BOARD;
		
		//Create the Frame
		final String TITLE_TXT = "Conway’s game of life";
		setTitle(TITLE_TXT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		final int DEFAULT_HIGH = 700;
		final int DEFAULT_WIDTH = DEFAULT_HIGH * 16 / 9;
		final Dimension DEFAULT_DIMENSION = new Dimension(DEFAULT_WIDTH, DEFAULT_HIGH);
		setSize(DEFAULT_DIMENSION);
		setMinimumSize(DEFAULT_DIMENSION);
		
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
		gameBoardPanel = new JPanel();
		numRow = gameBoard.getCurrentGenerationGridCells().length;
		numCol = gameBoard.getCurrentGenerationGridCells()[0].length;
		gameBoardPanel.setLayout(new GridLayout(numRow, numCol));
		gameBoardPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		updateDisplayBoard();
		add(gameBoardPanel);
		
		addKeyListener(new GameBoardFrameKeyListener());
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/***********
	 * Private functions
	 ***********/	
	private void updateDisplayBoard() {
		gameBoardPanel.removeAll();
		gameBoardPanel.updateUI();
		for(int row = 0; row < numRow; row++) {
			for(int col = 0; col < numCol; col++) {
				gameBoardPanel.add(new CellPanel(gameBoard.getCurrentGenerationGridCells()[row][col]));
			}
		}	
	}
	
	/***********
	 * Private classes
	 ***********/
	private class GameBoardFrameKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent event) {
			gameBoard.calculateNextGeneration();
			updateDisplayBoard();
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
