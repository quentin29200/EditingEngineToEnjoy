package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator;

import fr.istic.m1.aco.miniediteur.v2.Command.Command;
import fr.istic.m1.aco.miniediteur.v2.Command.CutCommand;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.CutMemento;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

public class CutCommandRegister extends CutCommand implements CommandRegister {

    private Command cmd;
    private Register reg;

    public CutCommandRegister(EditingEngine engine, Register reg) {
        super(engine);
        this.reg = reg;
    }


    public void execute() {
        this.reg.record(this);
        super.execute();
    }

    public Memento getMemento() {
        System.out.println("CutCommandRegister : Enregistrement dans le Memento");
        return new CutMemento();
    }

    public void setMemento(Memento m) {
        System.out.println("CutCommandRegister : Execute une commande enregistrée");
        this.execute();
    }
}
