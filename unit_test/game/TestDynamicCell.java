package unit_test.game;

import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;
import source.game.GameBoard;

/**
 * Unit tests for dynamic behaviours of a cell.
 * The cell evolves by following the rules of the game
 * @author William
 * @date 08/03/2020
 * @version 2.0
 */
public class TestDynamicCell extends TestCase {
	GameBoard board;
	
	private void init1AliveCellX1Y4() {
		initBoard();
		board.setCellAlive(1, 4, true);
	}
	
	private void initBoard() {
		try {
			board = new GameBoard(4, 8);
		} catch (IOException e) {
			fail("Fail to create a new board");
		}
	}
	
	@Test
	public void test1AliveCellWith0r1AliveNeighbourDies() {
		init1AliveCellX1Y4();
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
		board.setCellAlive(1, 5, true);
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
	}
	
	@Test
	public void test1AliveCellWith2Or3AliveNeighboursLives() {
		init1AliveCellX1Y4();
		board.setCellAlive(1, 3, true);
		board.setCellAlive(1, 5, true);
		assertEquals(true, board.livesForTheNextGeneration(1, 4));
		board.setCellAlive(0, 4, true);
		assertEquals(true, board.livesForTheNextGeneration(1, 4));
	}
	
	@Test
	public void test1AliveCellWithMoreThan3AliveNeighboursDies() {
		init1AliveCellX1Y4();
		board.setCellAlive(1, 3, true);
		board.setCellAlive(1, 5, true);
		board.setCellAlive(0, 4, true);
		board.setCellAlive(2, 4, true);
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
		board.setCellAlive(0, 3, true);
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
		board.setCellAlive(0, 5, true);
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
		board.setCellAlive(2, 3, true);
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
		board.setCellAlive(2, 5, true);
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
	}
	
	@Test
	public void test1DeadCellWith0Or1Or2AliveNeighboursStaysDead() {
		initBoard();
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
		board.setCellAlive(1, 3, true);
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
		board.setCellAlive(1, 5, true);
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
	}
	
	@Test
	public void test1DeadCellWith3AliveNeighboursLives() {
		initBoard();
		board.setCellAlive(1, 3, true);
		board.setCellAlive(1, 5, true);
		board.setCellAlive(0, 4, true);
		assertEquals(true, board.livesForTheNextGeneration(1, 4));
	}
	
	@Test
	public void test1DeadCellWithMoreThan3AliveNeighboursStaysDead() {
		initBoard();
		board.setCellAlive(1, 3, true);
		board.setCellAlive(1, 5, true);
		board.setCellAlive(0, 4, true);
		board.setCellAlive(2, 4, true);
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
		board.setCellAlive(0, 3, true);
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
		board.setCellAlive(0, 5, true);
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
		board.setCellAlive(2, 3, true);
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
		board.setCellAlive(2, 5, true);
		assertEquals(false, board.livesForTheNextGeneration(1, 4));
	}
}
