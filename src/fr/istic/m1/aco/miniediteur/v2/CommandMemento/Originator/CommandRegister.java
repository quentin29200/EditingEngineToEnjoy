package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator;

import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;

/**
 * Created by Quentin on 21/11/2015.
 */
public interface CommandRegister {
    public void execute();
    // Create and return the memento of the command
    public Memento getMemento();
    // Add context memento to the command and execute
    public void setMemento(Memento m);
}
