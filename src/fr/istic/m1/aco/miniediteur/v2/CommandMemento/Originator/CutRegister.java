package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator;

import fr.istic.m1.aco.miniediteur.v2.Command.Command;
import fr.istic.m1.aco.miniediteur.v2.Command.Cut;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.CutMemento;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

public class CutRegister extends Cut implements CommandRegister {

    private Command cmd;
    private Register reg;

    public CutRegister(EditingEngine engine, Register reg) {
        super(engine);
        this.reg = reg;
    }


    public void execute() {
        super.execute();
    }

    public Memento getMemento() {
        System.out.println("CutRegister : Enregistrement dans le Memento");
        return new CutMemento();
    }

    public void setMemento(Memento m) {
        System.out.println("CutRegister : Execute une commande enregistrée");
        this.execute();
    }
}
