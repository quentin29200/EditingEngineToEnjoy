package fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker;

import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator.CommandRegister;

/**
 * Created by Quentin on 21/11/2015.
 */
public interface Register {
    public void record(CommandRegister c);
    public void runRecord();
    public void stopRecord();
    public void replay();
    public boolean isRecorded();

}
