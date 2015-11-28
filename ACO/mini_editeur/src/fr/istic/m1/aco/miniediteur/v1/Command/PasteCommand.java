package fr.istic.m1.aco.miniediteur.v1.Command;

import fr.istic.m1.aco.miniediteur.v1.Receiver.EditingEngine;

/**
 * <b>PasteCommand is a ConcreteCommand</b>
 * <p>
 * It pastes the content of the selection in the JTextArea.
 * </p>
 *
 * @see fr.istic.m1.aco.miniediteur.v1.Receiver.Clipboard
 * @version 1.0
 */
public class PasteCommand implements Command {

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
    public PasteCommand(EditingEngine engine) {
        this.engine = engine;
    }

    /**
     * Execute method
     * Execute the paste.
     */
    public void execute() {
        this.engine.paste();
    }
}
