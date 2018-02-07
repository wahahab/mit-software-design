package minesweeper.server;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.naming.spi.DirStateFactory.Result;

import minesweeper.Board;
import minesweeper.DigResult;

public class BoardProcess implements Runnable {
	
	private Board mBoard;
	private ConcurrentLinkedQueue<BoardMessage> mQueue;
	
	public BoardProcess(ConcurrentLinkedQueue<BoardMessage> queue,
			Integer sizeX, Integer sizeY, Optional<File> file) throws IOException {
		mQueue = queue;
		mBoard = new Board(sizeX, sizeY, file);
	}
	
	public Board getmBoard() {
		return mBoard;
	}

	@Override
	public void run() {
		BoardMessage msg;
		DigResult digResult;
		String response = "";
		
		while (true) {
			msg = mQueue.poll();
			if (msg != null) {
				msg.getmMsgLock().lock();
				try {
					switch(msg.getMsgType()) {
						case DIG:
							digResult = mBoard.dig(msg.getX(), msg.getY());
							response = mBoard.showBoard();
							if (digResult == DigResult.BOMB)
								response = "BOOM";
							break;
						case FLAG:
							mBoard.flag(msg.getX(), msg.getY());
							response = mBoard.showBoard();
							break;
						case DEFLAG:
							mBoard.deflag(msg.getX(), msg.getY());
							response = mBoard.showBoard();
							break;
					}
					msg.setResponse(response);
					msg.getmProcessFinish().signal();
				} finally {
					msg.getmMsgLock().unlock();
				}
				
			}
		}
	}

}
