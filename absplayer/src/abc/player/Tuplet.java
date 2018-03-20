package abc.player;

import java.util.List;

public class Tuplet implements Element {
	private List<Note> mNotes;
	private Integer mNum;
	
	public Tuplet(List<Note> notes, Integer num) {
		mNotes = notes;
		mNum = num;
	}
	
	public List<Note> getmNotes() {
		return mNotes;
	}
	public Integer getmNum() {
		return mNum;
	}
}
