package fr.istic.m1.aco.miniediteur.v2.Command;

import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;

/**
 * <b>StopRecordCommand is a ConcreteCommand</b>
 * <p>
 * It permits to stop to save a sequence of commands.
 * </p>
 *
 * @version 2.0
 */
public class StopRecordCommand implements Command {

    /**
     * The register
     * Record a sequence of Commands (through Memento).
     *
     * @see fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register
     */
    private Register r;

    /**
     * Constructor of the ConcreteCommand
     * Bind the IHM and the Memento to notify to stop to save the list of Commands with their parameters.
     *
     * @param r
     *  Register
     */
    public StopRecordCommand(Register r) {
        this.r = r;
    }

    /**
     * Execute method
     * Stop to save the sequence.
     */
    public void execute() {
        this.r.stopRecord();
    }
}
