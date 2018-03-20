package abc.player;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.parse.ANTLRParser.block_return;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import abc.parser.AbcLexer;
import abc.parser.AbcParser;
import abc.parser.AbcWalkListener;
import abc.sound.Pitch;
import abc.sound.SequencePlayer;

public class AbcMusic {
	private static final int TICKS_PER_BEAT = 16;
	public final static Integer NATRUAL_ACCIDENTAL = 3;
	
	private int index;
	private String title;
	private String composer = "Unknown";
	private float meter = 4 / 4f;
	private int tempo;
	private float tempoLength;
	private String key;
	private Float length = null;
	private List<Element> notes = new ArrayList<>();
	private Map<String, List<Element>> voiceElementMap = new HashMap<>();
	private String voice = null;
	private Map<Character, Integer> keySignatureMap = new HashMap<>();
	
	public static AbcMusic fromString(String musicString) {
		AbcParser parser;
		AbcLexer lexer;
		CharStream stream = new ANTLRInputStream(musicString);
		AbcWalkListener listener = new AbcWalkListener();
		
		lexer = new AbcLexer(stream);
		parser = new AbcParser(new CommonTokenStream(lexer));
		parser.reportErrorsAsExceptions();
		ParseTreeWalker.DEFAULT.walk(listener, parser.root());
		return listener.getMusic();
	}
	public static AbcMusic fromFile(String path) {
		AbcParser parser;
		AbcLexer lexer;
		CharStream stream;
		try {
			stream = new ANTLRFileStream(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		AbcWalkListener listener = new AbcWalkListener();
		
		lexer = new AbcLexer(stream);
		parser = new AbcParser(new CommonTokenStream(lexer));
//		parser.reportErrorsAsExceptions();
		ParseTreeWalker.DEFAULT.walk(listener, parser.root());
		return listener.getMusic();
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setComposer(String composer) {
		this.composer = composer;
	}
	public void setMeter(float meter) {
		this.meter = meter;
	}
	public void setKey(String key) {
		char musicKey = key.charAt(0);
		
		this.key = key;
		switch(musicKey) {
		case 'D':
			keySignatureMap.put('F', 1);
			keySignatureMap.put('C', 1);
			break;
		case 'G':
			keySignatureMap.put('F', 1);
			break;
		}
	}
	public void setLength(float length) {
		this.length = length;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	public void setTempoLength(float tempoLength) {
		this.tempoLength = tempoLength;
	}
	public void addElement(Element ele) {
		if (voice != null) {
			voiceElementMap.get(voice).add(ele);
			return ;
		}
		notes.add(ele);
	}
	public Float getLength() {
		if (length == null) {
			
		}
		return length;
	}
	public int getTempo() {
		return tempo;
	}
	public float getTempoLength() {
		return tempoLength;
	}
	public List<Element> getNotes() {
		return notes;
	}
	public String getTitle() {
		return title;
	}
	public String getComposer() {
		return composer;
	}
	public String getKey() {
		return key;
	}
	public float getMeter() {
		return meter;
	}
	public void addVoice(String voiceName) {
		voiceElementMap.put(voiceName, new ArrayList<>());
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public SequencePlayer getPlayer() throws MidiUnavailableException, InvalidMidiDataException {
		int beatsPerMinute = tempo;
		SequencePlayer player = new SequencePlayer(beatsPerMinute, TICKS_PER_BEAT);
		List<Element> elements;
		
		if (voiceElementMap.isEmpty()) {
			addElementsToPlayer(notes, player);
		} else {
			for (String voice : voiceElementMap.keySet()) {
				elements = voiceElementMap.get(voice);
				addElementsToPlayer(elements, player);
			}
		}
		return player;
	}
	private Pitch getPitchFromNote (Note note) {
		Character key = note.getKey().charAt(0);
		int semitones = 0;
		
		if (!note.getAccidental().equals(NATRUAL_ACCIDENTAL)) {
			semitones = keySignatureMap.getOrDefault(key, 0);
			semitones += note.getAccidental();
		}
		return note.getPitch().transpose(semitones);
	}
	private int getNumberOfTicks (float l) {
		return (int) ((length / tempoLength) * TICKS_PER_BEAT * l);
	}
	private void addElementsToPlayer(List<Element> elements,
			SequencePlayer player) {
		Pitch pitch;
		Character key;
		Integer octave;
		Note note;
		Rest rest;
		Tuplet tuplet;
		Chord chord;
		int curTick = 0;
		int numTicks;
		int semitones;
		
		for (Element ele : elements) {
			if (ele instanceof Note) {
				note = (Note) ele;
				numTicks = getNumberOfTicks(note.getLength());
				pitch = getPitchFromNote(note);
				player.addNote(pitch.toMidiNote(),
						curTick, numTicks);
				curTick += numTicks;
			} else if (ele instanceof Rest) {
				rest = (Rest) ele;
				numTicks = getNumberOfTicks(rest.getmLength());
				curTick += numTicks;
			} else if (ele instanceof Tuplet) {
				tuplet = (Tuplet) ele;
				for (Note n : tuplet.getmNotes()) {
					numTicks = TICKS_PER_BEAT / tuplet.getmNum();
					pitch = getPitchFromNote(n);
					player.addNote(pitch.toMidiNote(),
							curTick, numTicks);
					curTick += numTicks;
				}
			} else if (ele instanceof Chord) {
				int minTicks = Integer.MAX_VALUE;
				
				chord = (Chord) ele;
				for (Note n : chord.getmNotes()) {
					pitch = getPitchFromNote(n);
					numTicks = getNumberOfTicks(n.getLength());
					player.addNote(pitch.toMidiNote(), curTick, numTicks);
					if (numTicks < minTicks) {
						minTicks = numTicks;
					}
				}
				curTick += minTicks;
			}
		}
	}
//	public List<Pitch> getPitches() {
//		ArrayList<Pitch> pitches = new ArrayList<>();
//		
//		for (String voice : voiceElementMap.keySet()) {
//			pitches.addAll(getPitchesOfVoice(voice));
//		}
//		pitches.addAll(getPitchesOfVoice(null));
//		return pitches;
//	}
//	private List<Pitch> getPitchesOfVoice(String voice) {
//		List<Element> elements;
//		List<Pitch> pitches = new ArrayList<>();
//		Pitch pitch;
//		
//		if (voice == null) {
//			elements = notes;
//		} else {
//			elements = voiceElementMap.get(voice);
//		}
//		for (Element element : elements) {
//			pitch = new Pitch(c);
//			pitches.add();
//		}
//		return pitches;
//	}
}
