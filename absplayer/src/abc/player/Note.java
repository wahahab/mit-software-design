package abc.player;

import abc.sound.Pitch;

public class Note implements Element {
	private String key;
	private float length;
	private int octave;
	private Integer accidental = 0;
	
	public Note(String k) {
		key = k;
		length = 1;
		octave = 0;
	}
	public Note(String k, float l) {
		// TODO Auto-generated constructor stub
		key = k;
		length = l;
	}
	
	public void setLength(float length) {
		this.length = length;
	}
	public float getLength() {
		return length;
	}
	public String getKey() {
		return key;
	}
	public void setOctave(int octave) {
		this.octave = octave;
	}
	public int getOctave() {
		return octave;
	}
	public void setAccidental(Integer accidental) {
		this.accidental = accidental;
	}
	public Integer getAccidental() {
		return accidental;
	}
	public Pitch getPitch() {
		Character key;
		
		key = getKey().charAt(0);
		octave = getOctave();
		if (Character.isLowerCase(key)) {
			octave++;
			key = Character.toUpperCase(key);
		}
		return (new Pitch(key)).transpose(octave * Pitch.OCTAVE);
	}
}
