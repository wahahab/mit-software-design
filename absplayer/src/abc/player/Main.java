package abc.player;

import java.awt.DisplayMode;
import java.awt.event.PaintEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.print.MultiDocPrintService;
import javax.security.auth.kerberos.KerberosTicket;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;

import org.antlr.runtime.tree.Tree;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import abc.parser.AbcLexer;
import abc.parser.AbcParser;
import abc.parser.AbcWalkListener;
import abc.sound.Pitch;
import abc.sound.SequencePlayer;

/**
 * Main entry point of your application.
 */
public class Main {

	/**
	 * Plays the input file using Java MIDI API and displays
	 * header information to the standard output stream.
	 * 
	 * (Your code should not exit the application abnormally using
	 * System.exit().)
	 * 
	 * @param file the name of input abc file
	 * @throws IOException 
	 * @throws InvalidMidiDataException 
	 * @throws MidiUnavailableException 
	 */
	public static void play(String file) throws IOException, MidiUnavailableException, InvalidMidiDataException {
		StringBuilder builder = new StringBuilder();
		char[] buff = new char[1024];
		AbcMusic music;
		FileReader fileReader = new FileReader(file);
		SequencePlayer player;

		try {
			while(fileReader.read(buff) != -1) {
				builder.append(buff);
			}
		} finally {
			fileReader.close();
		}
		music = AbcMusic.fromFile(file);
		player = music.getPlayer();
		player.play();
	}

	private static void printMusicInfo(AbcMusic music) {
		System.out.println("Title: " + music.getTitle());
		System.out.println("Composer: " + music.getComposer());
		System.out.println("Key: " + music.getKey());
		System.out.println("Length: " + music.getLength());
		System.out.println("Meteor: " + music.getMeter());
		System.out.println("Tempo: " + music.getTempo());
	}

	public static void main(String[] args) throws IOException, MidiUnavailableException, InvalidMidiDataException {
		if (args.length < 1) {
			System.err.println("Usage: java Main /path/to/yourmusic.abc");
			return;
		}
		try {
			play(args[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
