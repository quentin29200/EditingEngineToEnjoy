package fr.istic.m1.aco.miniediteur.v1;

import fr.istic.m1.aco.miniediteur.v1.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v1.Receiver.EditingEngineImpl;

/**
 * <b>Editing is the client of the application (DP Command).</b>
 * <p>
 * The engine will interact with quite everything.
 * Used to run the application and set visible the IHM.
 * </p>
 *
 * @version 1.0
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

        // Interface creation
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IHMInvoker(e).setVisible(true);
            }
        });
    }
}
