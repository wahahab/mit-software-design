/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyStore.PrivateKeyEntry;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.w3c.dom.css.ElementCSSInlineStyle;


class Point {
	int mY;
	int mX;
	public Point(int x, int y) {
		mX = x;
		mY = y;
	}
}

/**
 * @author wahahab
 *	Cell class: represent each block in board
 */
class Cell {
	
	private Boolean hasBomb = false;
	private Boolean digged = false;
	private Integer surBombNum = 0;
	private Boolean flag = false;
	
	Cell() {
		this(false);
	}
	Cell(Boolean isBomb) {
		hasBomb = isBomb;
	}
	
	public Boolean hasBomb() {
		return hasBomb;
	}
	public void setBomb() {
		this.hasBomb = true;
	}
	public void dig() {
		this.digged = true;
	}
	public Boolean hasDigged() {
		return digged;
	}
	public void setSurBombNum(Integer surBombNum) {
		this.surBombNum = surBombNum;
	}
	public Integer getSurBombNum() {
		return surBombNum;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public Boolean getFlag() {
		return flag;
	}
}

/**
 * Minesweeper game board
 */
public class Board {
    
    // TODO: Abstraction function, rep invariant, rep exposure, thread safety
	// Abstraction function:
	//   represent a game board
	// Rep invariant:
	//   
    
	private Integer mSizeX;
	private Integer mSizeY;
	private List<List<Cell>> mBoard;
	private Random mRandom = new Random(new Date().getTime());
	
    public Board(Integer sizeX, Integer sizeY, Optional<File> file) throws IOException {
    		int bombNum;
    		int x;
    		int y;
    		List<Cell> row;
    		Cell cell;
    		
    		mBoard = new ArrayList<>();
    		if (file.isPresent()) {
    			BufferedReader reader = new BufferedReader(new FileReader(file.get()));
    			String line;
    			String tokens[];
    			int rowCount = 0;
    			// read header
    			line = reader.readLine();
    			tokens = line.split(" ");
    			assert tokens.length == 2;
    			mSizeX = Integer.parseInt(tokens[0]);
    			mSizeY = Integer.parseInt(tokens[1]);
    			while((line = reader.readLine()) != null) {
    				tokens = line.split(" ");
    				row = new ArrayList<>();
    				assert tokens.length == mSizeX;
    				for (int i = 0 ; i < tokens.length ; i++) {
    					if (tokens[i].equals("1")) {
    						row.add(new Cell(true));
    					} else {
    						row.add(new Cell());
    					}
    				}
    				rowCount++;
    				mBoard.add(row);
    			}
    			assert rowCount == mSizeY;
    			return;
    		}
    		mSizeX = sizeX;
    		mSizeY = sizeY;
    	 	// initial board list
    		for (int i = 0 ; i < sizeY ; i ++) {
    			row = new ArrayList<>();
    			for (int j = 0 ; j < sizeX ; j ++) {
    				row.add(new Cell());
    			}
    			mBoard.add(row);
    		}
    		// setup bombs
    		bombNum = sizeX + mRandom.nextInt(sizeY);
    		for (int i = 0 ; i < bombNum ; i++) {
    			x = mRandom.nextInt(sizeX);
    			y = mRandom.nextInt(sizeY);
    			cell = mBoard.get(y).get(x);
    			if (cell.hasBomb()) {
    				i--;
    			} else {
    				cell.setBomb();
    			}
    		}
    }
    
    public DigResult dig(int x, int y) {
    		if (!isValidPoint(x, y)) {
    			return DigResult.INVALID;
    		}
    		Cell cell = mBoard.get(y).get(x);
    		int surBombNum;
    		
    		if (cell.hasDigged())
    			return DigResult.DIGGED;
    		cell.dig();
    		if (cell.hasBomb()) {
    			return DigResult.BOMB;
    		}
    		surBombNum = getSurBombNum(x, y);
    		if (surBombNum > 0) {
    			cell.setSurBombNum(surBombNum);
    			return DigResult.OK;
    		}
    		// recursively dig surround points
    		for (Point point : getSurLoc(x, y, false)) {
    			if (isValidPoint(point.mX, point.mY))
    				dig(point.mX, point.mY);
    		}
    		return DigResult.OK;
    }
    public void flag(int x, int y) {
    		if (!isValidPoint(x, y)) {
    			return ;
    		}
    		Cell cell = mBoard.get(y).get(x);
    		if (cell.hasDigged()) {
    			return;
    		}
    		cell.setFlag(true);
    }
    public void deflag(int x, int y) {
    		if (!isValidPoint(x, y)) {
			return ;
		}
		Cell cell = mBoard.get(y).get(x);
		if (cell.hasDigged()) {
			return;
		}
		cell.setFlag(false);
    }
    
    private int getSurBombNum(int x, int y) {
    		int bombNum = 0;
    		
    		for (Point point : getSurLoc(x, y)) {
    			if (isValidPoint(point.mX, point.mY)
    					&& mBoard.get(point.mY)
    						.get(point.mX).hasBomb()) {
    				bombNum++;
    			}
    		}
    		return bombNum;
    }
    
    private Boolean isValidPoint(int x, int y) {
    		return x >= 0 && x < mSizeX
    				&& y >= 0 && y < mSizeY;
    }
    
    private List<Point> getSurLoc(int x, int y) {
    		return getSurLoc(x, y, true);
    }
    private List<Point> getSurLoc(int x, int y, Boolean diagonal) {
    	List<Point> surLoc = new ArrayList<>();
		surLoc.add(new Point(x + 1, y));
		surLoc.add(new Point(x - 1, y));
		surLoc.add(new Point(x, y + 1));
		surLoc.add(new Point(x, y - 1));
		if (diagonal) {
			surLoc.add(new Point(x - 1, y + 1));
			surLoc.add(new Point(x - 1, y - 1));
			surLoc.add(new Point(x + 1, y - 1));
			surLoc.add(new Point(x + 1, y + 1));
		}
		return surLoc;
    }
    
    // for testing
    public List<List<Cell>> getmBoard() {
		return mBoard;
	}
    public String showBoard() {
    		StringBuilder builder = new StringBuilder();
    		
    		for (List<Cell> row : mBoard) {
    			for (Cell cell : row) {
    				if (cell.getSurBombNum() > 0) {
    					builder.append(String.valueOf(cell.getSurBombNum()));
    				}
    				else if (cell.hasDigged()) {
    					builder.append(' ');
    				}
    				else if (cell.getFlag()) {
    					builder.append('F');
    				}
    				else {
    					builder.append('-');
    				}
    				builder.append(' ');
    			}
    			builder.append('\n');
    		}
    		return builder.toString();
    }
    
    public Integer getmSizeX() {
		return mSizeX;
	}
    public Integer getmSizeY() {
		return mSizeY;
	}
	
    
}
