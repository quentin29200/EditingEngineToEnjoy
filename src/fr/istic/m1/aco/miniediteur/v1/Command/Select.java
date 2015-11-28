package fr.istic.m1.aco.miniediteur.v1.Command;

import fr.istic.m1.aco.miniediteur.v1.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v1.Receiver.EditingEngine;

public class Select implements Command {
    private EditingEngine engine;
    private IHMInvoker ihm;
    private int start;
    private int length;

    public Select(EditingEngine engine, IHMInvoker ihm) {
        this.engine = engine;
        this.ihm = ihm;
    }

    public void setSelect(int start, int length) {
        this.start = start;
        this.length = length;
    }


    public void execute() {
        // Exectuer la sélection avec les paramètre début et longeur (depuis l'IHM)
        this.engine.select(this.start,this.length);}
}
