package fr.istic.m1.aco.miniediteur.v2.Command;

import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

/**
 * <b>CutCommand is a ConcreteCommand</b>
 * <p>
 * It cuts the content of the selection and put it in the Clipboard.
 * The length of the selection equals 0 after the execution.
 * </p>
 *
 * @see fr.istic.m1.aco.miniediteur.v2.Receiver.Clipboard
 * @version 2.0
 */
public class CutCommand implements Command {

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
    public CutCommand(EditingEngine engine) {
        this.engine = engine;
    }

    /**
     * Execute method
     * Execute the cut.
     */
    public void execute() {
        this.engine.cut();
    }
}
