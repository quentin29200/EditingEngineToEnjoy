package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator;

import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;

/**
 * <b>CommandRegister is the interface which represents the registered Commands.</b>
 * <p>
 * During the register process, the engine will send to the Register the Memento to save it.
 * During the replay process, the engine will ask to the Register to send him the the saved Memento.
 * </p>
 *
 * @version 2.0
 */
public interface CommandRegister {

    /**
     * execute method
     * Execute the register.
     */
    public void execute();

    /**
     * getMemento method
     * Create and return the Memento of the Command.
     *
     * @return Memento
     *  The created Memento of the Command.
     */
    public Memento getMemento();

    /**
     * setMemento method
     * Add the context Memento to the Command and then execute it.
     *
     * @param m
     *  The new Memento
     */
    public void setMemento(Memento m);
}
