package fr.istic.m1.aco.miniediteur.v2.Command;

import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;

/**
 * <b>ReplayCommand is a ConcreteCommand</b>
 * <p>
 * It permits to replay a sequence of recorded commands.
 * </p>
 *
 * @version 2.0
 */
public class ReplayCommand implements Command {

    /**
     * The register
     * Record a sequence of Commands (through Memento).
     *
     * @see fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register
     */
    private Register r;

    /**
     * Constructor of the ConcreteCommand
     * Bind the IHM and the Memento previously saved.
     *
     * @param r
     *  Register
     */
    public ReplayCommand(Register r) {
        this.r = r;
    }

    /**
     * Execute method
     * Replay the previously saved sequence.
     */
    public void execute() {
        this.r.replay();
    }
}
