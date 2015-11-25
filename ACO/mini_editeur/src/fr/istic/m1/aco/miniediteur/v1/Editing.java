package fr.istic.m1.aco.miniediteur.v1;

import fr.istic.m1.aco.miniediteur.v1.Command.Command;
import fr.istic.m1.aco.miniediteur.v1.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v1.Receiver.EditingEngineImpl;

public class Editing {
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
