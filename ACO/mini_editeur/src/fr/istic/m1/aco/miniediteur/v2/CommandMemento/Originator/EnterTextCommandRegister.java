package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator;

import fr.istic.m1.aco.miniediteur.v2.Command.Command;
import fr.istic.m1.aco.miniediteur.v2.Command.EnterTextCommand;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.EnterTextCommandMemento;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

/**
 * <b>EnterTextCommandRegister is a CommandRegister.</b>
 * <p>
 * During the register process, the engine will send to the Register the Memento to save it.
 * During the replay process, the engine will ask to the Register to send him the the saved Memento.
 * </p>
 * <p>
 * It extends EnterTextCommand to execute it and save/get it.
 * It implements CommandRegister to use the methods of CommandRegister.
 * </p>
 *
 * @version 2.0
 */
public class EnterTextCommandRegister extends EnterTextCommand implements CommandRegister {

    /**
     * The concrete command
     * Permits to instanciate the context of the Command.
     *
     * @see Command
     */
    private Command cmd;

    /**
     * The IHM
     * To get/set the entered character.
     *
     * @see IHMInvoker
     */
    private IHMInvoker ihm;

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
	public EnterTextCommandRegister(EditingEngine engine, IHMInvoker ihm, Register reg) {
		super(engine, ihm);
        this.reg = reg;
        this.ihm = ihm;
        this.engine = engine;
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
        System.out.println("EnterTextCommandRegister : Enregistrement dans le Memento");
        System.out.println("Dernier char rentré dans le memento : "+this.ihm.getLastchar().hashCode());
        // If the last character is the "Enter" key, we replaced it by a space
        // in the buffer to avoid code character problems
        if (this.ihm.getLastchar().hashCode()==10) {
            return new EnterTextCommandMemento(" ");
        } else {
            return new EnterTextCommandMemento(this.ihm.getLastchar());
        }
    }

    /**
     * setMemento method
     * Add the context Memento to the Command and then execute it.
     *
     * @param m
     *  The new Memento
     */
    public void setMemento(Memento m) {
        String c = ((EnterTextCommandMemento)m).getLastchar();
        System.out.println("EnterTextCommandRegister : Execute une commande enregistrée");
        this.engine.enterchar(c);
    }

}
