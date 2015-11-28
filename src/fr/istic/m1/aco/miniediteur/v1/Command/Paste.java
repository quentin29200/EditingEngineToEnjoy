package fr.istic.m1.aco.miniediteur.v1.Command;

import fr.istic.m1.aco.miniediteur.v1.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v1.Receiver.EditingEngine;

public class Paste implements Command{
    private EditingEngine engine;

    public Paste(EditingEngine engine) {
        this.engine = engine;
    }


    public void execute() {
        this.engine.paste();
    }
}
