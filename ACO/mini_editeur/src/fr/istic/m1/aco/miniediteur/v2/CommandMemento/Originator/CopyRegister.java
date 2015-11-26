package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator;

import fr.istic.m1.aco.miniediteur.v2.Command.Command;
import fr.istic.m1.aco.miniediteur.v2.Command.Copy;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.CopyMemento;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

public class CopyRegister extends Copy implements CommandRegister {

    private Command cmd;
    private Register reg;

    public CopyRegister(EditingEngine engine, Register reg) {
        super(engine);
        this.reg = reg;
    }

    public void execute() {
        this.reg.record(this);
        super.execute();
    }
    
    public Memento getMemento() {
        System.out.println("CopyRegister : Enregistrement dans le Memento");
        return new CopyMemento();
    }

    public void setMemento(Memento m) {
        System.out.println("CopyRegister : Execute une commande enregistrée");
        this.execute();
    }
}
