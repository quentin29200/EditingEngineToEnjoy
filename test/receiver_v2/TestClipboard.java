package receiver_v2;

import static org.junit.Assert.*;
import org.junit.Test;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Clipboard;

public class TestClipboard {
	
	// Var
	private Clipboard cb = new Clipboard();

	@Test
	public void testSetAndGetTxtCb() {
		try {
			cb.setCb("xyz");
			assertTrue("Message de GET", cb.getCb().equals("xyz"));
			assertFalse("Message de GET", cb.getCb().equals("abc"));
		}
		catch(Exception e) {
			fail(e.getMessage());
		}
	}

}