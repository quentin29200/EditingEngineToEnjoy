package fr.istic.m1.aco.miniediteur.v2.Command;

import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;

/**
 * Created by Quentin on 26/11/2015.
 */
public class StopRecord implements Command {
    private Register r;

    public StopRecord(Register r) {
        this.r = r;
    }

    public void execute() {
        this.r.stopRecord();
    }
}
