package abc.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.TerminalNode;

import abc.parser.AbcParser.AccidentalsContext;
import abc.parser.AbcParser.BodyContext;
import abc.parser.AbcParser.ChordContext;
import abc.parser.AbcParser.ComposerHeaderContext;
import abc.parser.AbcParser.EndingContext;
import abc.parser.AbcParser.FractionContext;
import abc.parser.AbcParser.HeadersContext;
import abc.parser.AbcParser.IndexHeaderContext;
import abc.parser.AbcParser.KeyHeaderContext;
import abc.parser.AbcParser.LengthHeaderContext;
import abc.parser.AbcParser.MeterHeaderContext;
import abc.parser.AbcParser.NoteContext;
import abc.parser.AbcParser.Note_lengthContext;
import abc.parser.AbcParser.OtherHeaderContext;
import abc.parser.AbcParser.Repeat_endContext;
import abc.parser.AbcParser.Repeat_startContext;
import abc.parser.AbcParser.RestContext;
import abc.parser.AbcParser.SectionContext;
import abc.parser.AbcParser.TempoHeaderContext;
import abc.parser.AbcParser.TitleHeaderContext;
import abc.parser.AbcParser.TupletContext;
import abc.parser.AbcParser.VoiceHeaderContext;
import abc.player.AbcMusic;
import abc.player.Chord;
import abc.player.Element;
import abc.player.Note;
import abc.player.Rest;
import abc.player.Tuplet;

public class AbcWalkListener extends AbcBaseListener {
	
	private AbcMusic music = new AbcMusic();
	private Map<String, Integer> accidentalMap = new HashMap<>();
	private Boolean inChord = false;
	private Boolean inTuplet = false;
	private Boolean inRepeatSection = false;
	private Boolean inBody = false;
	private List<Note> notesInChord;
	private List<Note> notesInTuplet;
	private List<Element> elementsInRepeatSection = null;
	private String currentVoice;
	
	public AbcMusic getMusic() {
		return music;
	}
	
	@Override
	public void exitIndexHeader(IndexHeaderContext ctx) {
		// TODO Auto-generated method stub
		super.exitIndexHeader(ctx);
		music.setIndex(numberToInt(ctx.NUMBER()));
	}
	@Override
	public void exitTitleHeader(TitleHeaderContext ctx) {
		// TODO Auto-generated method stub
		super.exitTitleHeader(ctx);
		music.setTitle(ctx.name().getText());
	}
	@Override
	public void exitComposerHeader(ComposerHeaderContext ctx) {
		// TODO Auto-generated method stub
		super.exitComposerHeader(ctx);
		music.setComposer(ctx.name().getText());
	}
	@Override
	public void exitMeterHeader(MeterHeaderContext ctx) {
		// TODO Auto-generated method stub
		super.exitMeterHeader(ctx);
		float meter = 1;
		
		if (ctx.BIG_C() == null) {
			meter = fractionToFloat(ctx.fraction());
		}
		music.setMeter(meter);
	}
	@Override
	public void exitLengthHeader(LengthHeaderContext ctx) {
		// TODO Auto-generated method stub
		super.exitLengthHeader(ctx);
		music.setLength(fractionToFloat(ctx.fraction()));
	}
	@Override
	public void exitTempoHeader(TempoHeaderContext ctx) {
		// TODO Auto-generated method stub
		super.exitTempoHeader(ctx);
		music.setTempoLength(fractionToFloat(ctx.fraction()));
		music.setTempo(numberToInt(ctx.NUMBER()));
	}
	@Override
	public void exitKeyHeader(KeyHeaderContext ctx) {
		// TODO Auto-generated method stub
		super.exitKeyHeader(ctx);
		music.setKey(ctx.key().getText());
	}
	@Override
	public void enterChord(ChordContext ctx) {
		// TODO Auto-generated method stub
		super.enterChord(ctx);
		inChord = true;
		notesInChord = new ArrayList<>();
	}
	@Override
	public void exitChord(ChordContext ctx) {
		// TODO Auto-generated method stub
		super.exitChord(ctx);
		inChord = false;
		music.addElement(new Chord(notesInChord));
	}
	@Override
	public void enterTuplet(TupletContext ctx) {
		// TODO Auto-generated method stub
		super.enterTuplet(ctx);
		inTuplet = true;
		notesInTuplet = new ArrayList<>();
	}
	@Override
	public void exitTuplet(TupletContext ctx) {
		// TODO Auto-generated method stub
		super.exitTuplet(ctx);
		inTuplet = false;
		music.addElement(new Tuplet(notesInTuplet,
				numberToInt(ctx.NUMBER())));
	}
	@Override
	public void exitRepeat_start(Repeat_startContext ctx) {
		// TODO Auto-generated method stub
		super.exitRepeat_start(ctx);
		inRepeatSection = true;
		elementsInRepeatSection = new ArrayList<>();
	}
	@Override
	public void exitRepeat_end(Repeat_endContext ctx) {
		// TODO Auto-generated method stub
		super.exitRepeat_end(ctx);
		inRepeatSection = false;
		for (Element ele : elementsInRepeatSection) {
			music.addElement(ele);
		}
		elementsInRepeatSection = null;
	}
	@Override
	public void exitEnding(EndingContext ctx) {
		// TODO Auto-generated method stub
		super.exitEnding(ctx);
		inRepeatSection = false;
		if (elementsInRepeatSection == null) {
			elementsInRepeatSection = new ArrayList<>();
		}
	}
	@Override
	public void exitNote(NoteContext ctx) {
		// TODO Auto-generated method stub
		super.exitNote(ctx);
		float length = getLength(ctx.note_length());
		String key;
		Note note;
		int octave;
		AccidentalsContext accidentalsContext = ctx.accidentals();
		Integer accidental = 0;
		String noteKey;
		
		if (ctx.key() != null) {
			key = ctx.key().getText();
			octave = -ctx.COMMA().size();
		} else {
			key = ctx.lower_key().getText();
			octave = ctx.SINGLE_Q().size();
		}
		// check for accidentals
		noteKey = String.valueOf(octave) + key;
		if (accidentalsContext != null) {
			if (accidentalsContext.EQUAL() != null) {
				accidental = AbcMusic.NATRUAL_ACCIDENTAL;
			} else if (accidentalsContext.FLAT().size() > 0) {
				accidental = -accidentalsContext.FLAT().size();
			} else if (accidentalsContext.UPPER().size() > 0) {
				accidental = accidentalsContext.UPPER().size();
			}
			accidentalMap.put(noteKey, accidental);
		}
		accidental = accidentalMap.getOrDefault(noteKey, 0);
		note = new Note(key, length);
		note.setOctave(octave);
		note.setAccidental(accidental);
		if (inChord) {
			notesInChord.add(note);
		} else if (inTuplet) {
			notesInTuplet.add(note);
		} else {
			music.addElement(note);
		}
		if (inRepeatSection) {
			elementsInRepeatSection.add(note);
		}
	}
	@Override
	public void exitRest(RestContext ctx) {
		// TODO Auto-generated method stub
		super.exitRest(ctx);
		float length = getLength(ctx.note_length());
		
		music.addElement(new Rest(length));
	}
	@Override
	public void exitSection(SectionContext ctx) {
		// TODO Auto-generated method stub
		super.exitSection(ctx);
		accidentalMap = new HashMap<>();
	}
	
	// helpers
	private float fractionToFloat(FractionContext ctx) {
		return numberToInt(ctx.NUMBER(0)) / (float) numberToInt(ctx.NUMBER(1)); 
	}
	private Integer numberToInt(TerminalNode number) {
		return Integer.parseInt(number.getText());
	}
	private float getLength(Note_lengthContext note_length) {
		float length = 1;
		
		if (note_length != null) {
			if (note_length.fraction() != null) {
				length = fractionToFloat(note_length.fraction());
			} else if (note_length.NUMBER() != null
					&& note_length.SLASH() == null) {
				length = numberToInt(note_length.NUMBER());
			} else if (note_length.NUMBER() != null
					&& note_length.SLASH() != null) {
				if (note_length.getChild(0) == note_length.SLASH()) {
					length = 1f / numberToInt(note_length.NUMBER());
				} else {
					length = numberToInt(note_length.NUMBER());
				}
			}
		}
		return length;
	}
	@Override
	public void exitHeaders(HeadersContext ctx) {
		// TODO Auto-generated method stub
		super.exitHeaders(ctx);
		VoiceHeaderContext voiceHeaderContext;
		
		for (OtherHeaderContext otherHeaderContext : ctx.otherHeader()) {
			voiceHeaderContext = otherHeaderContext.voiceHeader();
			if (voiceHeaderContext != null) {
				music.addVoice(voiceHeaderContext.name().getText());
			}
		}
	}
	@Override
	public void enterBody(BodyContext ctx) {
		// TODO Auto-generated method stub
		super.enterBody(ctx);
		inBody = true;
		// if length is omitted, give it value
		if (music.getLength() == null) {
			music.setLength(music.getMeter() < 0.75f ? 1 / 16f : 1 / 8f);
		}
	}
	@Override
	public void exitVoiceHeader(VoiceHeaderContext ctx) {
		// TODO Auto-generated method stub
		super.exitVoiceHeader(ctx);
		if (inBody) {
			music.setVoice(ctx.name().getText());	
		}
	}
}
