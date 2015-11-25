package fr.istic.m1.aco.miniediteur.v2.Command;

import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

public class Paste implements Command{
    private EditingEngine engine;

    public Paste(EditingEngine engine) {
        this.engine = engine;
    }


    public void execute() {
        this.engine.paste();
    }
}
