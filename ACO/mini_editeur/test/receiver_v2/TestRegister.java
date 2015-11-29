package receiver_v2;

import fr.istic.m1.aco.miniediteur.v2.Command.*;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator.CopyRegister;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator.EnterTextCommandRegister;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator.PasteRegister;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator.SelectRegister;
import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Buffer;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.RegisterImpl;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngineImpl;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

/**
 * Created by Quentin on 29/11/2015.
 */
public class TestRegister {

    @Test
    public void TestRegisterCopyPaste() {
        /** TEST 1 */
        // Declare an editing engine, a buffer and a clipboard
        EditingEngineImpl engine = new EditingEngineImpl();
        Buffer bf = engine.getBuffer();

        // Declare ihm
        IHMInvoker ihm = new IHMInvoker(engine);

        // Declare register
        RegisterImpl register = new RegisterImpl(engine, ihm);

        // Command
        CopyRegister copyr = new CopyRegister(engine, register);
        PasteRegister paster = new PasteRegister(engine, register);
        SelectRegister selectr = new SelectRegister(engine, ihm, register);

        // Initialisation des commandes
        HashMap<String, Command> cmds = new HashMap<>();
        Command select = new SelectCommand(engine, ihm);
        cmds.put("select", select);
        ihm.addcmds(cmds);

        // Set the content of the buffer at the begining
        String firstContentBufferString = "The roses are red, the violets are blue, the fish is flying and parrots are vegetables";
        StringBuffer firstBufferContent = new StringBuffer(firstContentBufferString);
        ihm.getTextArea().append(firstContentBufferString);
        bf.setAreaTxt(firstBufferContent);
        System.out.println("Contenu du buffer :" + ihm.getTextArea().getText());

        // SelectCommand word to paste
        ihm.getTextArea().select(firstContentBufferString.length() - 11, firstContentBufferString.length());

        // Start Recorded
        register.runRecord();

        // Execute Actions
        copyr.execute();
        ihm.getTextArea().setCaretPosition(firstContentBufferString.length() - 23);
        paster.execute();

        // Stop recorded
        register.stopRecord();

        // Replay Actions
        register.replay();

        // Test result
        String contentBufferAfter = bf.getAreaTxt().toString();
        System.out.println("Resultat du buffer :" + contentBufferAfter);
        String result = "The roses are red, the violets are blue, the fish is flying and vegetables vegetables parrots are vegetables";
        assertTrue(contentBufferAfter.equals(result));

    }

    @Test
    public void TestRegisterEntertxt() {
        /** TEST 2 */
        // Declare an editing engine, a buffer and a clipboard
        EditingEngineImpl engine = new EditingEngineImpl();
        Buffer bf = engine.getBuffer();

        // Declare ihm
        IHMInvoker ihm = new IHMInvoker(engine);

        // Declare register
        RegisterImpl register = new RegisterImpl(engine, ihm);

        // Command
        CopyRegister copyr = new CopyRegister(engine, register);
        PasteRegister paster = new PasteRegister(engine, register);
        SelectRegister selectr = new SelectRegister(engine, ihm, register);
        EnterTextCommandRegister enterTextr = new EnterTextCommandRegister(engine, ihm, register);

        // Initialisation des commandes
        HashMap<String, Command> cmds = new HashMap<>();
        Command select = new SelectCommand(engine, ihm);
        cmds.put("select", select);
        ihm.addcmds(cmds);

        // Set the content of the buffer at the begining
        String firstContentBufferString = "The roses are red, the violets are blue, the fish is flying and parrots are vegetables";
        StringBuffer firstBufferContent = new StringBuffer(firstContentBufferString);
        ihm.getTextArea().append(firstContentBufferString);
        bf.setAreaTxt(firstBufferContent);
        System.out.println("Contenu du buffer :" + ihm.getTextArea().getText());

        // SelectCommand word to paste
        ihm.getTextArea().setCaretPosition(firstContentBufferString.length() - 11);

        // Start Recorded
        register.runRecord();

        // Execute Actions
        ihm.setLastchar(" ");
        enterTextr.execute();
        ihm.setLastchar("v");
        enterTextr.execute();
        ihm.setLastchar("e");
        enterTextr.execute();
        ihm.setLastchar("r");
        enterTextr.execute();
        ihm.setLastchar("y");
        enterTextr.execute();

        // Stop recorded
        register.stopRecord();
        
        // Replay Actions
        register.replay();

        // Test result
        String contentBufferAfter = bf.getAreaTxt().toString();
        System.out.println("Resultat du buffer : "+contentBufferAfter);
        String result = "The roses are red, the violets are blue, the fish is flying and parrots are very very vegetables";

        assertTrue(contentBufferAfter.equals(result));
    }
}
