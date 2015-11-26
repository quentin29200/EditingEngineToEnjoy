package fr.istic.m1.aco.miniediteur.v2.Command;

import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;

/**
 * Created by Quentin on 26/11/2015.
 */
public class StartRecord implements Command {
    private Register r;

    public StartRecord(Register r) {
        this.r = r;
    }

    public void execute() {
        this.r.runRecord();
    }
}
