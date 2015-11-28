package fr.istic.m1.aco.miniediteur.v2.Command;

import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

/**
 * <b>SelectCommand is a ConcreteCommand</b>
 * <p>
 * It selects a string in the JTextArea.
 * </p>
 *
 * @see fr.istic.m1.aco.miniediteur.v2.Receiver.Clipboard
 * @see fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker
 * @version 2.0
 */
public class SelectCommand implements Command {

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
     */
    private IHMInvoker ihm;

    /**
     * The index of the beginning of the selection.
     * Permits to know where does the selection begin.
     */
    private int start;

    /**
     * The length of the selection.
     * Permits to know where does the selection finish.
     */
    private int length;

    /**
     * Constructor of the ConcreteCommand
     * Bind the engine, the ConcreteCommand and the IHM
     *
     * @param engine
     *  Editing - Client
     * @param ihm
     *  IHM - Invoker
     */
    public SelectCommand(EditingEngine engine, IHMInvoker ihm) {
        this.engine = engine;
        this.ihm = ihm;
    }

    /**
     * setSelect method
     * Set the beginning and the length of the selection by using their index.
     *
     * @param start
     *  Beginning of the selection
     * @param length
     *  Length of the selection
     */
    public void setSelect(int start, int length) {
        this.start = start;
        this.length = length;
    }

    /**
     * Execute method
     * Select a string, a character or an empty content (caret).
     */
    public void execute() { this.engine.select(this.start,this.length); }
}
