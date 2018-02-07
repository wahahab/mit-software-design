/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.AclEntry.Builder;
import java.util.List;
import java.util.Optional;

import javax.print.attribute.Size2DSyntax;

import org.junit.Before;
import org.junit.Test;

/**
 * TODO: Description
 */
public class BoardTest {
    
    // TODO: Testing strategy
	
	Board mBoard;
	private static final Integer SIZE = 10; 
    
	@Before
	public void buildBoard() {
		Optional<File> file = Optional.empty();
		try {
			mBoard = new Board(SIZE, SIZE, file);
		} catch(IOException e) {
			fail();
		}
	}
	
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    @Test(expected=AssertionError.class)
    public void testDigInvalidPoint() {
    		mBoard.dig(-1, 0);
    }
    
    @Test
    public void testBombCount() {
    		List<List<Cell>> boardList = mBoard.getmBoard();
    		int bombCount = 0;
    		for (List<Cell> row : boardList) {
    			for (Cell cell : row) {
    				if (cell.hasBomb())
    					bombCount++;
    			}
    		}
    		assertTrue("Bomb count shoud between size ~ size * 2",
    				bombCount >= SIZE && bombCount < SIZE * 2);
    }
    
    @Test(expected=IOException.class)
    public void testFromFileNotExist() throws IOException {
    		Optional<File> file = Optional.empty().of(new File("./foobar"));
    		try {
    			mBoard = new Board(null, null, file);
    		} catch(IOException e) {
    			throw e;
    		}
    }
    
    @Test
    public void testDigFromFile() {
    		Optional<File> file = Optional.empty().of(new File("./test/minesweeper/test.txt"));
		try {
			mBoard = new Board(null, null, file);
		} catch(IOException e) {
			fail();
		}
		testDig();
    }
    @Test(expected=AssertionError.class)
    public void testDigFromFileInvalid() throws IOException {
    		Optional<File> file = Optional.empty().of(new File("./test/minesweeper/testInvalid.txt"));
		try {
			mBoard = new Board(null, null, file);
		} catch(IOException e) {
			throw e;
		}
    }
    
    @Test
    public void testDig() {
    		// check number of bombs
    		int bombCount = 0;
    		DigResult result;
    		for (int j = 0 ; j < mBoard.getmSizeY() ; j++) {
    			for (int i = 0 ; i < mBoard.getmSizeX() ; i ++) {
    				result = mBoard.dig(i, j);
    				if (result == DigResult.BOMB)
    					bombCount++;
    			}
    		}
//    		System.out.println(bombCount);
    		assertTrue("Bomb count shoud between size ~ size * 2",
    				bombCount >= mBoard.getmSizeX() && bombCount < mBoard.getmSizeX() + mBoard.getmSizeY());
    		System.out.println(mBoard.showBoard());
    		// dig same place twice
    		result = mBoard.dig(0, 0);
    		assertEquals(result, DigResult.DIGGED);
    }
    
}
