package receiver_v1;

import static org.junit.Assert.*;
import org.junit.Test;
import fr.istic.m1.aco.miniediteur.v1.Receiver.Buffer;

public class TestBuffer {

	// Instanciate to test
	private static Buffer bf;

	@Test
	public void testSetAndGetTxtArea() {
		try {
			// Vars
			Buffer bf = new Buffer();
			StringBuffer sb = new StringBuffer("Once upon a time someone who used the F12 button.");
			StringBuffer sb_false = new StringBuffer("Skiing is like life, you always paint the walls.");

			// Set
			bf.setAreaTxt(sb);

			// Get
			assertTrue(null, bf.getAreaTxt().equals(sb));
			assertFalse(null, bf.getAreaTxt().equals(sb_false));
		}
		catch(Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testToString() {
		try {
			// Vars
			String areaString = "Then a wild Roucoups appears and attacks Sacha.";
			StringBuffer areaTxt = new StringBuffer(areaString);
			String areaString_false = "Have you ever been ashamed to know that penguins can not shake hands?";

			// ToString
			assertTrue(null, areaTxt.toString().equals(areaString));
			assertFalse(null, areaTxt.toString().equals(areaString_false));
		}
		catch(Exception e) {
			fail(e.getMessage());
		}
	}


}
