package receiver_v1;

import fr.istic.m1.aco.miniediteur.v1.Receiver.EditingEngineImpl;
import org.junit.Test;
import fr.istic.m1.aco.miniediteur.v1.Receiver.Buffer;
import static org.junit.Assert.*;

public class TestEditingEngineImpl {

	@Test
	public void testCutPaste() {
		// Declare an editing enginen a buffer and a clipboard
		EditingEngineImpl engine = new EditingEngineImpl();
		Buffer bf = engine.getBuffer();

		// Set the content of the buffer at the begining
		String firstContentBufferString = "The roses are red, the violets are blue, the fish is flying and parrots are vegetables";
		StringBuffer firstBufferContent = new StringBuffer(firstContentBufferString);
		bf.setAreaTxt(firstBufferContent);

		// SelectCommand word to paste
		engine.select(firstContentBufferString.length()-23,8);

		// Call the copy method
		engine.cut();

		// Recove area content
		String contentBufferBeforePaste = bf.getAreaTxt().toString();
		System.out.println(engine.getBuffer().toString());

		// Test
		String resultbuffer = "The roses are red, the violets are blue, the fish is flying and are vegetables";
		assertTrue(null, contentBufferBeforePaste.equals(resultbuffer));

		// Caret at end
		engine.select(contentBufferBeforePaste.length(),0);

		// Call the paste method
		engine.paste();

		// Recove area content
		String contentBufferAfterPaste = bf.getAreaTxt().toString();
		System.out.println(engine.getBuffer().toString());

		// Test
		String resultbuffer2 = "The roses are red, the violets are blue, the fish is flying and are vegetables parrots";
		assertTrue(null, contentBufferAfterPaste.equals(resultbuffer2));
	}

	@Test
	public void testCopyPaste() {
		// Declare an editing enginen a buffer and a clipboard
		EditingEngineImpl engine = new EditingEngineImpl();
		Buffer bf = engine.getBuffer();

		// Set the content of the buffer at the begining
		String firstContentBufferString = "The roses are red, the violets are blue, the fish is flying and parrots are vegetables";
		StringBuffer firstBufferContent = new StringBuffer(firstContentBufferString);
		bf.setAreaTxt(firstBufferContent);

		// SelectCommand word to paste
		engine.select(firstContentBufferString.length()-11,11);

		// Call the copy method
		engine.copy();

		// Caret at end
		engine.select(firstContentBufferString.length(),0);

		// Call the paste method
		engine.paste();

		// Recove area content
		String contentBufferAfterPaste = bf.getAreaTxt().toString();
		System.out.println(engine.getBuffer().toString());

		// Test
		String resultbuffer = "The roses are red, the violets are blue, the fish is flying and parrots are vegetables vegetables";
		assertTrue(null, contentBufferAfterPaste.equals(resultbuffer));
	}

	@Test
	public void testRemove() {
		// Test 1 remove the last char
		// Declare an editing enginen a buffer and a clipboard
		EditingEngineImpl engine = new EditingEngineImpl();
		Buffer bf = engine.getBuffer();

		// Set the content of the buffer at the begining
		String firstContentBufferString = "The roses are red, the violets are blue, the fish is flying and parrots are vegetables";
		engine.select(firstContentBufferString.length(),0);
		StringBuffer firstBufferContent = new StringBuffer(firstContentBufferString);
		bf.setAreaTxt(firstBufferContent);

		engine.remove();

		String contentBufferAfterPaste = bf.getAreaTxt().toString();

		String resultbuffer = "The roses are red, the violets are blue, the fish is flying and parrots are vegetable";
		assertTrue(null, contentBufferAfterPaste.equals(resultbuffer));

		// Test 2 remove select
		bf.setAreaTxt(new StringBuffer(firstContentBufferString));
		engine.select(firstContentBufferString.length()-22,8);
		engine.remove();
		String contentBufferAfterPaste2 = bf.getAreaTxt().toString();

		String resultbuffer2 = "The roses are red, the violets are blue, the fish is flying and are vegetables";
		assertTrue(null, contentBufferAfterPaste2.equals(resultbuffer2));
	}

	@Test
	public void testSelect() { } // Déjà testé dans les autres fonctions

	@Test
	public void testEnterChar() {
		// Test 1 add char
		// Declare an editing enginen a buffer and a clipboard
		EditingEngineImpl engine = new EditingEngineImpl();
		Buffer bf = engine.getBuffer();

		// Set the content of the buffer at the begining
		String firstContentBufferString = "The roses are red, the violets are blue, the fish is flying and parrots are vegetables";
		engine.select(firstContentBufferString.length(),0);
		StringBuffer firstBufferContent = new StringBuffer(firstContentBufferString);
		bf.setAreaTxt(firstBufferContent);

		engine.enterchar("A");

		String contentBufferAfterPaste = bf.getAreaTxt().toString();

		String resultbuffer = "The roses are red, the violets are blue, the fish is flying and parrots are vegetablesA";
		assertTrue(null, contentBufferAfterPaste.equals(resultbuffer));

		// Test 2 Replace SelectCommand by Char
		bf.setAreaTxt(new StringBuffer(firstContentBufferString));
		engine.select(firstContentBufferString.length()-10,10);
		engine.enterchar("A");
		String contentBufferAfterPaste2 = bf.getAreaTxt().toString();

		String resultbuffer2 = "The roses are red, the violets are blue, the fish is flying and parrots are A";
		assertTrue(null, contentBufferAfterPaste2.equals(resultbuffer2));
	}

}
