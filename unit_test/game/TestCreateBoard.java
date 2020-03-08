package unit_test.game;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import source.game.GameBoard;

/**
 * Unit tests for user's input values 
 * @author William
 * @date 08/03/2020
 * @version 2.0
 */
public class TestCreateBoard extends TestCase {
	@Test
	public void testCreateBoardThrowsExceptionForNonPositiveValuesForRow() {
		try {
			@SuppressWarnings("unused")
			GameBoard board = new GameBoard(-3, 3);
			fail("Board creation does not trow an exception when input values are not positive");
		}
		catch (IOException error) {
			assertTrue("Board creation throws an exception when input values are not positive", true);
		}
	}
	
	@Test
	public void testCreateBoardThrowsExceptionForNonPositiveValuesForColumn() {
		try {
			@SuppressWarnings("unused")
			GameBoard board = new GameBoard(3, -2);
			fail("Board creation does not trow an exception when input values are not positive");
		}
		catch (IOException error) {
			assertTrue("Board creation throws an exception when input values are not positive", true);
		}
	}
	
	@Test
	public void testCreateBoardDoesNotThrowExceptionForPositiveValues() {
		try {
			@SuppressWarnings("unused")
			GameBoard board = new GameBoard(2, 3);
			assertTrue("Board creation does not trow an exception when input values are positive", true);
		}
		catch (IOException error) {
			fail("Board creation throws an exception when input values are positive");
		}
	}
}
