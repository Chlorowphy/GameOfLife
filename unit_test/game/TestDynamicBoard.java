package unit_test.game;

import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;
import source.game.GameBoard;

/**
 * Unit tests for dynamic behaviours of a board.
 * The board evolves at each generation
 * @author William
 * @date 08/03/2020
 * @version 2.0
 */
public class TestDynamicBoard extends TestCase {
	GameBoard board;
	GameBoard firstGenerationBoard;
	GameBoard secondGenerationBoard;
	
	private void initBoard() {
		try {
			board = new GameBoard(4, 8);
		} catch (IOException e) {
			fail("Fail to create the first board");
		}
	}
	
	private void initFirstGenBoard() {
		try {
			firstGenerationBoard = new GameBoard(4, 8);
		} catch (IOException e) {
			fail("Fail to create the first gen board");
		}
	}
	
	private void initSecondGenBoard() {
		try {
			secondGenerationBoard = new GameBoard(4, 8);
		} catch (IOException e) {
			fail("Fail to create the second gen board");
		}
	}		
	
	@Test
	public void test1CalculateNewGeneration() {
		// . . . . . . . .
		// . . X X X . . .
		// . . . . X . . .
		// . . . . . X . .
		initBoard();
		board.setCellAlive(1, 2, true);
		board.setCellAlive(1, 3, true);
		board.setCellAlive(1, 4, true);
		board.setCellAlive(2, 4, true);
		board.setCellAlive(3, 5, true);			
		
		// . . . . . . . .      . . . X . . . .
		// . . X X X . . .      . . . X X . . .
		// . . . . X . . .  =>  . . . . X X . .
		// . . . . . X . .      . . . . . . . .
		board.calculateNextGeneration();
		initFirstGenBoard();
		firstGenerationBoard.setCellAlive(0, 3, true);
		firstGenerationBoard.setCellAlive(1, 3, true);
		firstGenerationBoard.setCellAlive(1, 4, true);
		firstGenerationBoard.setCellAlive(2, 4, true);
		firstGenerationBoard.setCellAlive(2, 5, true);			
		assertEquals(true, board.isTheSameGameBoard(firstGenerationBoard));
		
		// . . . . . . . .      . . . X . . . .      . . . X X . . .
		// . . X X X . . .      . . . X X . . .      . . . X . X . .
		// . . . . X . . .  =>  . . . . X X . .  =>  . . . X X X . .
		// . . . . . X . .      . . . . . . . .      . . . . . . . .
		board.calculateNextGeneration();
		initSecondGenBoard();
		secondGenerationBoard.setCellAlive(0, 3, true);
		secondGenerationBoard.setCellAlive(0, 4, true);
		secondGenerationBoard.setCellAlive(1, 3, true);
		secondGenerationBoard.setCellAlive(1, 5, true);
		secondGenerationBoard.setCellAlive(2, 3, true);
		secondGenerationBoard.setCellAlive(2, 4, true);
		secondGenerationBoard.setCellAlive(2, 5, true);
		assertEquals(true, board.isTheSameGameBoard(secondGenerationBoard));
	}
	
	@Test
	public void test2CalculateNewGeneration() {
		// . . . . . . . .
		// . . . X . . . .
		// . . X X X . . .
		// . . . X . . . .
		initBoard();
		board.setCellAlive(1, 3, true);
		board.setCellAlive(2, 2, true);
		board.setCellAlive(2, 3, true);
		board.setCellAlive(2, 4, true);
		board.setCellAlive(3, 3, true);
		
		// . . . . . . . .      . . . . . . . .
		// . . . X . . . .      . . X X X . . .
		// . . X X X . . .  =>  . . X . X . . .
		// . . . X . . . .      . . X X X . . .
		board.calculateNextGeneration();
		initFirstGenBoard();
		firstGenerationBoard.setCellAlive(1, 2, true);
		firstGenerationBoard.setCellAlive(1, 3, true);
		firstGenerationBoard.setCellAlive(1, 4, true);
		firstGenerationBoard.setCellAlive(2, 2, true);
		firstGenerationBoard.setCellAlive(2, 4, true);
		firstGenerationBoard.setCellAlive(3, 2, true);
		firstGenerationBoard.setCellAlive(3, 3, true);
		firstGenerationBoard.setCellAlive(3, 4, true);
		assertEquals(true, board.isTheSameGameBoard(firstGenerationBoard));
		
		// . . . . . . . .      . . . . . . . .      . . . X . . . .
		// . . . X . . . .      . . X X X . . .      . . X . X . . .
		// . . X X X . . .  =>  . . X . X . . .  =>  . X . . . X . .
		// . . . X . . . .      . . X X X . . .      . . X . X . . .
		board.calculateNextGeneration();
		initSecondGenBoard();
		secondGenerationBoard.setCellAlive(0, 3, true);
		secondGenerationBoard.setCellAlive(1, 2, true);
		secondGenerationBoard.setCellAlive(1, 4, true);
		secondGenerationBoard.setCellAlive(2, 1, true);
		secondGenerationBoard.setCellAlive(2, 5, true);
		secondGenerationBoard.setCellAlive(3, 2, true);
		secondGenerationBoard.setCellAlive(3, 4, true);
		assertEquals(true, board.isTheSameGameBoard(secondGenerationBoard));
	}
}