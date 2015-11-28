package fr.istic.m1.aco.miniediteur.v2;

import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngineImpl;

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
