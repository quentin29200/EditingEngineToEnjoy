package fr.istic.m1.aco.miniediteur.v1.Command;

import fr.istic.m1.aco.miniediteur.v1.Receiver.EditingEngine;

/**
 * <b>CopyCommand is a ConcreteCommand</b>
 * <p>
 * It copies the content of the selection and put it in the Clipboard.
 * </p>
 *
 * @see fr.istic.m1.aco.miniediteur.v1.Receiver.Clipboard
 * @version 1.0
 */
public class CopyCommand implements Command {

    /**
     * The editing engine
     * Permits to interact with the engine.
     *
     * @see EditingEngine
     */
    private EditingEngine engine;

    /**
     * Constructor of the ConcreteCommand
     * Bind the engine and the ConcreteCommand
     *
     * @param engine
     *  Editing - Client
     */
    public CopyCommand(EditingEngine engine) { this.engine = engine; }

    /**
     * Execute method
     * Execute the copy.
     */
    public void execute() { this.engine.copy(); }
}
