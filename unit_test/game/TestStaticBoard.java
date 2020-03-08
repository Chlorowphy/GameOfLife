package unit_test.game;

import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;
import source.game.GameBoard;

/**
 * Unit tests for static behaviours of a board.
 * @author William
 * @date 08/03/2020
 * @version 2.0
 */
public class TestStaticBoard extends TestCase {
	GameBoard firstBoard;
	GameBoard secondBoard;

	private void initFirstBoard() {
		try {
			firstBoard = new GameBoard(4, 8);
		} catch (IOException e) {
			fail("Fail to create the first board");
		}
	}
	
	private void initSecondBoard() {
		try {
			secondBoard = new GameBoard(4, 8);
		} catch (IOException e) {
			fail("Fail to create the second board");
		}
	}
	
	@Test
	public void testBoardEqualItself() {
		initFirstBoard();
		assertEquals(true, firstBoard.isTheSameGameBoard(firstBoard));
	}
	
	@Test
	public void testEmptyBoardsNotSameRowSizeAreNotEqual() {		
		try {
			initFirstBoard();
			secondBoard = new GameBoard(3, 8);
			assertEquals(false, firstBoard.isTheSameGameBoard(secondBoard));
		} catch (IOException e) {
			fail("Fail to create a second board");
		}
	}
	
	@Test
	public void testEmptyBoardsNotSameColSizeAreNotEqual() {
		try {
			initFirstBoard();
			secondBoard = new GameBoard(4, 7);
			assertEquals(false, firstBoard.isTheSameGameBoard(secondBoard));
		} catch (IOException e) {
			fail("Fail to create a second board");
		}
	}
	
	@Test
	public void testEmptyBoardsSameSizeAreEqual() {
		initFirstBoard();
		initSecondBoard();
		assertEquals(true, firstBoard.isTheSameGameBoard(secondBoard));
	}
	
	@Test
	public void testBoardSameSizeButNotSameAliveCellsAreNotEqual() {
		initFirstBoard();
		initSecondBoard();
		secondBoard.setCellAlive(1, 4, true);
		assertEquals(false, firstBoard.isTheSameGameBoard(secondBoard));
	}
	
	@Test
	public void testBoardSameSizeSameAliveCellAreEqual() {
		initFirstBoard();
		initSecondBoard();
		firstBoard.setCellAlive(1, 4, true);
		secondBoard.setCellAlive(1, 4, true);
		assertEquals(true, firstBoard.isTheSameGameBoard(secondBoard));
		firstBoard.setCellAlive(3, 2, true);
		secondBoard.setCellAlive(3, 2, true);
		assertEquals(true, firstBoard.isTheSameGameBoard(secondBoard));
		firstBoard.setCellAlive(3, 2, false);
		secondBoard.setCellAlive(3, 2, false);
		assertEquals(true, firstBoard.isTheSameGameBoard(secondBoard));
	}
}
