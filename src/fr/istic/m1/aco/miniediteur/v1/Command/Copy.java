package fr.istic.m1.aco.miniediteur.v1.Command;

import fr.istic.m1.aco.miniediteur.v1.Receiver.EditingEngine;

public class Copy implements Command{
    private EditingEngine engine;

    public Copy(EditingEngine engine) {
        this.engine = engine;
    }


    public void execute() {
        this.engine.copy();
    }
}
