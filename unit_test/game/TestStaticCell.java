package unit_test.game;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import source.game.GameBoard;

/**
 * Unit tests for static behaviours of a cell.
 * The cell does not react to its neighbourhood for now
 * @author William
 * @date 08/03/2020
 * @version 2.0
 */
public class TestStaticCell extends TestCase {
	GameBoard board;
	
	@Before
	private void initBoard() {
		try {
			board = new GameBoard(4, 8);
		} catch (IOException e) {
			fail("Fail to create a new board");
		}
	}

	@Test
	public void test1AliveCellHas0AliveMate() {
		initBoard();
		board.setCellAlive(1, 4, true);
		assertEquals(0, board.countingAliveNeighbours(1, 4));
	}
	
	@Test
	public void test1AliveCellWith1AliveNeighbourHas1AliveMate() {
		initBoard();
		board.setCellAlive(1, 4, true);
		board.setCellAlive(1, 5, true);
		assertEquals(1, board.countingAliveNeighbours(1, 4));
	}
}
