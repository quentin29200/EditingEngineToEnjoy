package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator;

import fr.istic.m1.aco.miniediteur.v2.Command.SelectCommand;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.SelectMemento;
import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

/**
 * <b>SelectRegister is a CommandRegister.</b>
 * <p>
 * During the register process, the engine will send to the Register the Memento to save it.
 * During the replay process, the engine will ask to the Register to send him the the saved Memento.
 * </p>
 * <p>
 * It extends SelectCommand to execute it and save/get it.
 * It implements CommandRegister to use the methods of CommandRegister.
 * </p>
 *
 * @version 2.0
 */
public class SelectRegister extends SelectCommand implements CommandRegister {

    /**
     * The register
     * Used to saved or get the Memento corresponding to the Command.
     *
     * @see Register
     */
    private Register reg;

    /**
     * The engine
     * To get or update the content of the JTextArea.
     *
     * @see EditingEngine
     */
    private EditingEngine engine;

    /**
     * Constructor
     * Bind the CommandRegister, the IHM and the engine.
     *
     * @param engine
     *  EditingEngine - the engine of the application
     * @param ihm
     *  IHMInvoker - Invoker and IHM of the application
     * @param reg
     *  Register - Register the Commands
     */
    public SelectRegister(EditingEngine engine, IHMInvoker ihm, Register reg) {
        super(engine, ihm);
        this.engine = engine;
        this.reg = reg;
    }

    /**
     * execute method
     * Register the Command in a Memento and execute it.
     */
    public void execute() {
        this.reg.record(this);
        super.execute();
    }

    /**
     * getMemento method
     * Create and return the Memento of the Command.
     *
     * @return Memento
     *  The created Memento of the Command.
     */
    public Memento getMemento() {
        System.out.println("SelectRegister : Enregistrement dans le Memento");
        return new SelectMemento(this.engine.returnSelect().getBegin(), this.engine.returnSelect().getLength());
    }

    /**
     * setMemento method
     * Add the context Memento to the Command and then execute it.
     *
     * @param m
     *  The new Memento
     */
    public void setMemento(Memento m) {
        System.out.println("SelectRegister : Execute une commande enregistrée");
        this.engine.select(((SelectMemento)m).getSelect_start(),((SelectMemento)m).getSelect_lenght());
    }
}
