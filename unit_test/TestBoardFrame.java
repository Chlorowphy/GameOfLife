package unit_test;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import source.BoardFrame;

/**
 * Unit Tests for TestBoard class
 * @author William Pouts
 * @date 01/03/2020
 * @version 1.0
 */
public class TestBoardFrame extends TestCase {
	@Test
	public void testCreateBoardThrowsExceptionForNonPositiveValuesForRow() {
		try {
			@SuppressWarnings("unused")
			BoardFrame board = new BoardFrame(-2, 3);
			fail("Board creation does not trow an exception when input values are not positive");
		}
		catch (IllegalArgumentException error) {
			assertTrue("Board creation throws an exception when input values are not positive", true);
		}
	}
	
	@Test
	public void testCreateBoardThrowsExceptionForNonPositiveValuesForColumn() {
		try {
			@SuppressWarnings("unused")
			BoardFrame board = new BoardFrame(3, -2);
			fail("Board creation does not trow an exception when input values are not positive");
		}
		catch (IllegalArgumentException error) {
			assertTrue("Board creation throws an exception when input values are not positive", true);
		}
	}
	
	@Test
	public void testCreateBoardDoesNotThrowExceptionForPositiveValues() {
		try {
			@SuppressWarnings("unused")
			BoardFrame board = new BoardFrame(2, 3);
			assertTrue("Board creation does not trow an exception when input values are positive", true);
		}
		catch (IllegalArgumentException error) {
			fail("Board creation throws an exception when input values are positive");
		}
	}
	
	@Test
	public void testCreateBoardCreateGridOf0AliveCells() {
		final int NUM_ROW = 3;
		final int NUM_COLUMN = 2;
		BoardFrame board = new BoardFrame(NUM_ROW, NUM_COLUMN);
		assertTrue("Board with " + NUM_ROW + " rows and " + NUM_COLUMN + " columns creates a grid of 0 alive cells", (board.getNumCurrentAliveCells() == 0) == true);
	}
	
	@Test
	public void testBoardWith1CellAliveOnlyHave1AliveCells() {
		final int NUM_ROW = 3;
		final int NUM_COLUMN = 2;
		BoardFrame board = new BoardFrame(NUM_ROW, NUM_COLUMN);
		board.setNewStateCell(0, 1, true);
		assertTrue("Board with " + NUM_ROW + " rows and " + NUM_COLUMN + " columns and 1 alive cell has only 1 alive cells", (board.getNumCurrentAliveCells() == 1) == true);
	}
	
	@Test
	public void test1AliveCellWithNoMateHasNoAliveNeighbour() {
		BoardFrame board = new BoardFrame(4,8);
		board.setNewStateCell(1, 4, true);
		assertTrue("1 alive cell on the board has no alive neighbour", (board.countingAliveNeighbours(1, 4) == 0) == true);
	}
	
	@Test
	public void test2MateAliveCellsHave1Neighbour() {
		BoardFrame board = new BoardFrame(4, 8);
		board.setNewStateCell(1, 4, true);
		board.setNewStateCell(1, 5, true);
		assertTrue("1 alive cell with 1 mate has 1 alive neighbour", (board.countingAliveNeighbours(1, 4) == 1) == true);
	}
	
	@Test
	public void test1AliveCellWithNoAliveMAteOnACornerHas0Neighbour() {
		BoardFrame board = new BoardFrame(4, 8);
		board.setNewStateCell(0, 0, true);
		assertTrue("1 alive cell with no mate, on the corner, has no alive neighbour", (board.countingAliveNeighbours(0, 0) == 0) == true);
	}
	
	@Test
	public void test1AliveCellWithLessThan2AliveMatesDies() {
		BoardFrame board = new BoardFrame(4, 8);
		board.setNewStateCell(1, 4, true);
		assertTrue("1 alive cell with less than 2 alive mates dies at the next generation", board.livesForTheNextGeneration(1, 4) == false);
	}
	
	@Test
	public void test1AliveCellWithMoreThan3AliveMatesDies() {
		BoardFrame board = new BoardFrame(4, 8);
		board.setNewStateCell(1, 4, true);
		board.setNewStateCell(1, 5, true);
		board.setNewStateCell(0, 4, true);
		board.setNewStateCell(2, 4, true);
		board.setNewStateCell(1, 3, true);
		assertTrue("1 alive cell with more than 3 alives mates dies at the next generation", board.livesForTheNextGeneration(1, 4) == false);
	}
	
	@Test
	public void test1AliveCellWith2AliveMatesLives() {
		BoardFrame board = new BoardFrame(4, 8);
		board.setNewStateCell(1, 4, true);
		board.setNewStateCell(1, 5, true);
		board.setNewStateCell(1, 3, true);
		assertTrue("1 alive cell with 2 alive mates lives at the next generation", board.livesForTheNextGeneration(1, 4) == true);
	}
	
	@Test
	public void test1AliveCellWith3AliveMatesLives() {
		BoardFrame board = new BoardFrame(4, 8);
		board.setNewStateCell(1, 4, true);
		board.setNewStateCell(1, 5, true);
		board.setNewStateCell(1, 3, true);
		board.setNewStateCell(0, 4, true);
		assertTrue("1 alive cell with 3 alive mates lives at the next generation", board.livesForTheNextGeneration(1, 4) == true);
	}
	
	@Test
	public void test1DeadCellWith2AliveMatesStaysDeath() {
		BoardFrame board = new BoardFrame(4, 8);
		board.setNewStateCell(1, 5, true);
		board.setNewStateCell(1, 3, true);
		assertTrue("1 dead cell with 2 alive mates stays dead at the the next generation", board.livesForTheNextGeneration(1, 4) == false);
	}
	
	@Test
	public void test1DeadCellWith3AliveMatesLives() {
		BoardFrame board = new BoardFrame(4, 8);
		board.setNewStateCell(1, 3, true);
		board.setNewStateCell(1, 5, true);
		board.setNewStateCell(0, 4, true);
		assertTrue("1 dead cell with 3 alive mates becomes alive at the next generation", board.livesForTheNextGeneration(1, 4) == true);
	}
	
	@Test
	public void testInterestCellsListLenghtFor0AliveCell() {
		BoardFrame board = new BoardFrame(4, 8);
		ArrayList<Point> interestingCellsList = board.getInterestCellsList();
		assertTrue("Board with 0 alive cell has an interesting cells list of 0 lenght", interestingCellsList.isEmpty() == true);
	}
	
	@Test
	public void testInterestingCellsListLenghtFor1MiddleAliveCell() {
		BoardFrame board = new BoardFrame(4, 8);
		board.setNewStateCell(1, 4, true);
		ArrayList<Point> interestingCellsList = board.getInterestCellsList();
		assertTrue("Board with 1 alive cell in the middle has an interesting cells list of 9 lenght", (interestingCellsList.size() == 9) == true);
	}
	
	@Test
	public void testInterestingCellsListLenghtFor1CornerAliveCell() {
		BoardFrame board = new BoardFrame(4, 8);
		board.setNewStateCell(3, 7, true);
		ArrayList<Point> interestingCellsList = board.getInterestCellsList();
		assertTrue("Board with 1 alive cell on one corner has an intereseting cells list of 5 lenght", (interestingCellsList.size() == 4) == true);
	}
	
	@Test
	public void testCalculateNewGeneration() {
		BoardFrame board = new BoardFrame(4, 8);
		board.setNewStateCell(1, 4, true);
		board.setNewStateCell(1, 3, true);
		board.setNewStateCell(1, 5, true);
		board.setNewStateCell(0, 4, true);
		board.calculateNextGeneration();
		assertTrue("Board with these 4 alive cells will have 7 alive cells at the next generation", (board.getNumCurrentAliveCells() == 7) == true);
	}
}
