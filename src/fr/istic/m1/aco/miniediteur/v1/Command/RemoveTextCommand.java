package fr.istic.m1.aco.miniediteur.v1.Command;

import fr.istic.m1.aco.miniediteur.v1.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v1.Receiver.EditingEngine;

public class RemoveTextCommand implements Command {

    private EditingEngine engine;
    private IHMInvoker ihm;

    public RemoveTextCommand(EditingEngine engine, IHMInvoker ihm) {
        this.engine = engine;
        this.ihm = ihm;
    }

    public void execute() {
        this.engine.remove();
    }

}
