package fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker;

import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator.*;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * <b>RegisterImpl is the implementation of the interface of the register.</b>
 * <p>
 * The register will interact with the engine and the IHM.
 * It implements Register interface.
 * </p>
 *
 * @version 2.0
 */
public class RegisterImpl implements Register {

    /**
     * The engine
     * The register has to be able to interact with it to have access to the Commands.
     */
    private EditingEngine engine;

    /**
     * The IHM
     * The register has to be able to interact with it to have access to the Commands.
     */
    private IHMInvoker ihm;

    /**
     * The list of Memento
     * Needed to replay the sequence
     */
    private LinkedList<Memento> mementos;

    /**
     * To know if the user is running the register.
     */
    private boolean isRecorded;

    /**
     * Constructor of RegisterImpl
     * Instanciate the engine, the IHM, an empty list of Memento and put false to isRecorded.
     *
     * @param engine
     *  EditingEngine
     * @param ihm
     *  IHMInvoker
     */
    public RegisterImpl(EditingEngine engine, IHMInvoker ihm) {
        this.engine = engine;
        this.ihm = ihm;
        this.mementos = new LinkedList<Memento>();
        this.isRecorded = false;
    }

    /**
     * runRecord method
     * Run the recording of the sequence of Commands.
     */
    public void runRecord() {
        System.out.println("Start recorded");
        this.mementos.clear();
        this.isRecorded = true;
    }

    /**
     * stopRecord method
     * Stop the recording of the sequence of Commands.
     */
    public void stopRecord() {
        System.out.println("Stop recorded");
        this.isRecorded = false;
    }

    /**
     * record method
     * Record the sequence of Commands.
     *
     * @param c
     *  The sequence of saved ConcreteCommands
     */
    public void record(CommandRegister c) {
        // If button REC is active
        if (isRecorded) {
            // Add the memento of the command in the list
            this.mementos.add(c.getMemento());
        }
    }

    /**
     * record method
     * Check if the user is running the record process.
     *
     * @return boolean
     *  If the user is running the record or not.
     */
    public boolean isRecorded() {
        return isRecorded;
    }

    /**
     * replay method
     * Replay the recording of the sequence of Commands.
     */
    public void replay() {
        // If the record is stop
        if (!isRecorded) {
            Iterator<Memento> it = mementos.iterator();
            System.out.println("youhou");
            while (it.hasNext()) {
                Memento m = it.next();
                String cmd = m.getCmd();
                System.out.println("Commande à executer : "+cmd);
                switch (cmd) {
                    case "CopyCommand":
                        CopyRegister cpr = new CopyRegister(this.engine, this);
                        cpr.setMemento(m);
                        break;
                    case "CutCommand":
                        CutCommandRegister cur = new CutCommandRegister(this.engine, this);
                        cur.setMemento(m);
                        break;
                    case "PasteCommand":
                        PasteRegister pr = new PasteRegister(this.engine, this);
                        pr.setMemento(m);
                        break;
                    case "EnterTextCommand":
                        EnterTextCommandRegister entr = new EnterTextCommandRegister(this.engine, this.ihm, this);
                        entr.setMemento(m);
                        break;
                    case "RemoveTextCommand":
                        RemoveTextCommandRegister rmr = new RemoveTextCommandRegister(this.engine, this.ihm, this);
                        rmr.setMemento(m);
                        break;
                    case "SelectCommand":
                        SelectRegister sr = new SelectRegister(this.engine, this.ihm, this);
                        sr.setMemento(m);
                        break;
                    default:
                        System.out.println("Erreur du Register : Commande non reconnue.");
                }
            }
        }
    }

}
