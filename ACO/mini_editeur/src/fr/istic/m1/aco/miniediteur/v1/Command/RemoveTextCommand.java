package fr.istic.m1.aco.miniediteur.v1.Command;

import fr.istic.m1.aco.miniediteur.v1.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v1.Receiver.EditingEngine;

/**
 * <b>RemoveTextCommand is a ConcreteCommand</b>
 * <p>
 * It removes a character of the JTextArea and update the content of the buffer.
 * </p>
 *
 * @see fr.istic.m1.aco.miniediteur.v1.Receiver.Clipboard
 * @see fr.istic.m1.aco.miniediteur.v1.Invoker.IHMInvoker
 * @version 1.0
 */
public class RemoveTextCommand implements Command {

    /**
     * The editing engine
     * Permits to interact with the engine.
     *
     * @see EditingEngine
     */
    private EditingEngine engine;

    /**
     * The interface
     * Permits to update the content of the JTextArea.
     *
     * @see IHMInvoker
     */
    private IHMInvoker ihm;

    /**
     * Constructor of the ConcreteCommand
     * Bind the engine, the ConcreteCommand and the IHM
     *
     * @param engine
     *  Editing - Client
     * @param ihm
     *  IHM - Invoker
     */
    public RemoveTextCommand(EditingEngine engine, IHMInvoker ihm) {
        this.engine = engine;
        this.ihm = ihm;
    }

    /**
     * Execute method
     * Removes a character in the JTestArea and in the buffer.
     */
    public void execute() {
        this.engine.remove();
    }

}
