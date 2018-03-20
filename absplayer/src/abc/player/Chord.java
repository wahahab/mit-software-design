package abc.player;

import java.util.List;

public class Chord implements Element {
	private List<Note> mNotes;
	
	public Chord(List<Note> notes) {
		mNotes = notes;
	}
	
	public List<Note> getmNotes() {
		return mNotes;
	}
}
