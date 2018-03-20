package abc.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.ParseTree;
import org.antlr.runtime.tree.Tree;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.parse.ANTLRParser.parserRule_return;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.junit.Test;

import abc.player.AbcMusic;
import junit.framework.TestCase;

public class ParserTest extends TestCase {
	
	private static final String TEST_HEADER = "X:1\n" + 
			"T:Alphabet Song\n" +  
			"K:D\n";

	@Test
	public void testParserWork () throws IOException, InterruptedException {
		File abcDir = new File("./sample_abc");
		AbcLexer lexer;
		AbcParser parser;
		
		for (File abcFile : abcDir.listFiles()) {
			lexer = new AbcLexer(new ANTLRFileStream(abcFile.getAbsolutePath()));
			parser = new AbcParser(
					new org.antlr.v4.runtime.CommonTokenStream(lexer));
			try {
				parser.reportErrorsAsExceptions();
				System.out.println(parser.root().toStringTree());
			} catch (Exception e) {
				fail();
			}
		}
	}
//	@Test
//	public void testWalker() {
//		AbcMusic music;
//		try {
//			music = AbcMusic.fromFile("./sample_abc/abc_song");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail();
//		}
//		assertEquals(, music.getNotes().size());
//	}
	
	
//	@Test
//	public void testParseOctaveNote() {
//		String notesWithOctive = TEST_HEADER
//				+ "C, D, E, F,|G, A, B,, C|D E F G|A B c d|e f g a|"
//				+ "b c'' d' e'|f' g' a' b'|]";
//		AbcMusic music = AbcMusic.fromString(notesWithOctive);
//		
//		assertEquals(-1, music.getNotes().get(0).getOctave());
//		assertEquals(-2, music.getNotes().get(6).getOctave());
//		assertEquals(0, music.getNotes().get(7).getOctave());
//		assertEquals(2, music.getNotes().get(21).getOctave());
//		notesWithOctive = TEST_HEADER
//				+ "c''''''' G,, G C |";
//		music = AbcMusic.fromString(notesWithOctive);
//		assertEquals(7, music.getNotes().get(0).getOctave());
//		assertEquals(-2, music.getNotes().get(1).getOctave());
//		assertEquals(0, music.getNotes().get(2).getOctave());
//	}
//	@Test
//	public void testParseLengthNote() {
//		String notesWithLength = TEST_HEADER
//				+ "A1/4 A/4 A/ A A2 A3 A4 A6 A8"
//				+ "| A,1/4 A,/4 A,/ A, A,2 A,3 A,4 A,6 A,8 |]";
//		AbcMusic music = AbcMusic.fromString(notesWithLength);
//		
//		assertEquals(1 / 4f, music.getNotes().get(0).getLength());
//		assertEquals(1 / 4f, music.getNotes().get(1).getLength());
//		assertEquals(1f, music.getNotes().get(2).getLength());
//		assertEquals(2f, music.getNotes().get(4).getLength());
//		assertEquals(1f, music.getNotes().get(3).getLength());
//	}
//	
}
