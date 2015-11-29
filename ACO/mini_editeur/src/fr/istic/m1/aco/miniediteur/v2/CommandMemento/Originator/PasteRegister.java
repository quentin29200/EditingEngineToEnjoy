package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator;

import fr.istic.m1.aco.miniediteur.v2.Command.PasteCommand;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.PasteMemento;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Select;

/**
 * <b>PasteRegister is a CommandRegister.</b>
 * <p>
 * During the register process, the engine will send to the Register the Memento to save it.
 * During the replay process, the engine will ask to the Register to send him the the saved Memento.
 * </p>
 * <p>
 * It extends PasteCommand to execute it and save/get it.
 * It implements CommandRegister to use the methods of CommandRegister.
 * </p>
 *
 * @version 2.0
 */
public class PasteRegister extends PasteCommand implements CommandRegister {

    /**
     * The register
     * Used to saved or get the Memento corresponding to the Command.
     *
     * @see Register
     */
    private Register reg;

    /**
     * The object corresponding to the selection
     * Permits to instanciate the context of the selection.
     *
     * @see Select
     */
    private Select s;

    /**
     * Constructor
     * Bind the CommandRegister and the engine.
     *
     * @param engine
     *  EditingEngine - the engine of the application
     * @param reg
     *  Register - Register the Commands
     */
    public PasteRegister(EditingEngine engine, Register reg) {
        super(engine);
        this.reg = reg;
        this.s = engine.returnSelect();
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
        System.out.println("PasteRegister : Enregistrement dans le Memento");
        return new PasteMemento();
    }

    /**
     * setMemento method
     * Add the context Memento to the Command and then execute it.
     *
     * @param m
     *  The new Memento
     */
    public void setMemento(Memento m) {
        System.out.println("PasteRegister : Execute une commande enregistrée");
        this.execute();
    }
}
