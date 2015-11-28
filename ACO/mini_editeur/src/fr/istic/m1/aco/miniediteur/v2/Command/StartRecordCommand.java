package fr.istic.m1.aco.miniediteur.v2.Command;

import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;

/**
 * <b>StartRecordCommand is a ConcreteCommand</b>
 * <p>
 * It permits to start to save a sequence of commands.
 * </p>
 *
 * @version 2.0
 */
public class StartRecordCommand implements Command {

    /**
     * The register
     * Record a sequence of Commands (through Memento).
     *
     * @see fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register
     */
    private Register r;

    /**
     * Constructor of the ConcreteCommand
     * Bind the IHM and the Memento to save the list of Commands with their parameters.
     *
     * @param r
     *  Register
     */
    public StartRecordCommand(Register r) {
        this.r = r;
    }

    /**
     * Execute method
     * Start to save the sequence.
     */
    public void execute() {
        this.r.runRecord();
    }
}
