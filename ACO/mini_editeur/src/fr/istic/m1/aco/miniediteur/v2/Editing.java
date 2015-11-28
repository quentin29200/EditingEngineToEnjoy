package fr.istic.m1.aco.miniediteur.v2;

import fr.istic.m1.aco.miniediteur.v2.Command.*;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator.*;
import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.RegisterImpl;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngineImpl;

import java.util.HashMap;

/**
 * <b>Editing is the client of the application (DP Command).</b>
 * <p>
 * The engine will interact with quite everything.
 * Used to run the application and set visible the IHM.
 * </p>
 *
 * @version 2.0
 */
public class Editing {

    /**
     * Main method of the application.
     * Run all the application.
     *
     * @param args
     *  Tab of arguments passed in CLI (unused in our case).
     */
    public static void main(String args[]) {
        // Engine creation
        EditingEngineImpl e = new EditingEngineImpl();

        // IHM creation
        IHMInvoker ihm = new IHMInvoker(e);
        e.addObserver(ihm);

        // Register creation
        Register r = new RegisterImpl(e, ihm);

        // Init HashMap
        HashMap<String, Command> cmds = new HashMap<>();
        HashMap<String, CommandRegister> cmdsrec = new HashMap<>();

        // Initialisation des commandes
        Command enterTextCommand = new EnterTextCommand(e, ihm);
        cmds.put("enterTextCommand",enterTextCommand);
        Command removeTextCommand = new RemoveTextCommand(e, ihm);
        cmds.put("removeTextCommand",removeTextCommand);
        Command select = new SelectCommand(e, ihm);
        cmds.put("select",select);
        Command cut = new CutCommand(e);
        cmds.put("cut",cut);
        Command copy = new CopyCommand(e);
        cmds.put("copy",copy);
        Command paste = new PasteCommand(e);
        cmds.put("paste",paste);
        Command startregister = new StartRecordCommand(r);
        cmds.put("startregister",startregister);
        Command stopregister = new StopRecordCommand(r);
        cmds.put("stopregister",stopregister);
        Command replayregister = new ReplayCommand(r);
        cmds.put("replayregister",replayregister);

        ihm.addcmds(cmds);

        // Initialisation des commandes enregistrables
        CommandRegister entertxtrec = new EnterTextCommandRegister(e, ihm, r);
        cmdsrec.put("entertxtrec",entertxtrec);
        CommandRegister removetxtrec = new RemoveTextCommandRegister(e, ihm, r);
        cmdsrec.put("removetxtrec",removetxtrec);
        CommandRegister cutrec = new CutCommandRegister(e, r);
        cmdsrec.put("cutrec",cutrec);
        CommandRegister copyrec = new CopyRegister(e, r);
        cmdsrec.put("copyrec",copyrec);
        CommandRegister pasterec = new PasteRegister(e, r);
        cmdsrec.put("pasterec",pasterec);
        CommandRegister selectrec = new SelectRegister(e,ihm, r);
        cmdsrec.put("selectrec",selectrec);

        ihm.addcmdsrec(cmdsrec);


        // run IHM
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ihm.setVisible(true);
            }
        });
    }
}
