package fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker;

import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.Command.EnterTextCommand;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator.*;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Quentin on 23/11/2015.
 */
public class RegisterImpl implements Register {
    private EditingEngine engine;
    private IHMInvoker ihm;

    // List of Memento
    private LinkedList<Memento> mementos;
    // Boolean Record is active or not
    private boolean isRecorded;

    public RegisterImpl(EditingEngine engine, IHMInvoker ihm) {
        this.engine = engine;
        this.ihm = ihm;
        this.mementos = new LinkedList<Memento>();
        this.isRecorded = false;
    }

    public void runRecord() {
        this.mementos.clear();
        this.isRecorded = true;
    }

    public void stopRecord() {
        this.isRecorded = false;
    }

    public void record(CommandRegister c) {
        // If button REC is active
        if (isRecorded) {
            // Add the memento of the command in the list
            this.mementos.add(c.getMemento());
        }
    }

    public void replay() {
        // If the record is stop
        if (!isRecorded) {
            Iterator<Memento> it = mementos.iterator();
            while (it.hasNext()) {
                Memento m = it.next();
                String cmd = m.getCmd();
                switch (cmd) {
                    case "Copy":
                        CopyRegister cpr = new CopyRegister(this.engine, this);
                        cpr.setMemento(m);
                        break;
                    case "Cut":
                        CutRegister cur = new CutRegister(this.engine, this);
                        cur.setMemento(m);
                        break;
                    case "Paste":
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
                    case "Select":
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
