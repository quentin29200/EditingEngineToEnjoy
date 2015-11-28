package fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker;

import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator.CommandRegister;

/**
 * <b>Register is the interface of the register.</b>
 * <p>
 * A register has to do able to implements all those methods.
 * </p>
 *
 * @version 2.0
 */
public interface Register {

    /**
     * record method
     * Record the sequence of Commands.
     *
     * @param c
     *  The sequence of saved ConcreteCommands
     */
    public void record(CommandRegister c);

    /**
     * runRecord method
     * Run the recording of the sequence of Commands.
     */
    public void runRecord();

    /**
     * stopRecord method
     * Stop the recording of the sequence of Commands.
     */
    public void stopRecord();

    /**
     * replay method
     * Replay the recording of the sequence of Commands.
     */
    public void replay();

    /**
     * record method
     * Check if the user is running the record process.
     *
     * @return boolean
     *  If the user is running the record or not.
     */
    public boolean isRecorded();

}
