package minesweeper.server;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

enum MsgType {
	DIG,
	FLAG,
	DEFLAG,
}

public class BoardMessage {
	private MsgType msgType;
	private Integer x;
	private Integer y;
	private String response = "";
	private Lock mMsgLock;
	private Condition mProcessFinish;
	
	public BoardMessage(Lock msgLock, Condition processFinish) {
		mMsgLock = msgLock;
		mProcessFinish = processFinish;
	}
	
	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}
	public MsgType getMsgType() {
		return msgType;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getX() {
		return x;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public Integer getY() {
		return y;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Lock getmMsgLock() {
		return mMsgLock;
	}
	public Condition getmProcessFinish() {
		return mProcessFinish;
	}
	
}
