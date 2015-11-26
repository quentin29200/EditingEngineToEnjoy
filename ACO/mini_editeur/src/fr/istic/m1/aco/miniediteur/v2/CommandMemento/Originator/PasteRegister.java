package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator;

import fr.istic.m1.aco.miniediteur.v2.Command.Paste;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.PasteMemento;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

public class PasteRegister extends Paste implements CommandRegister{
    private Register reg;

    public PasteRegister(EditingEngine engine, Register reg) {
        super(engine);
        this.reg = reg;
    }

    public void execute() {
        this.reg.record(this);
        super.execute();
    }

    public Memento getMemento() {
        System.out.println("PasteRegister : Enregistrement dans le Memento");
        return new PasteMemento();
    }

    public void setMemento(Memento m) {
        System.out.println("PasteRegister : Execute une commande enregistrée");
        this.execute();
    }
}
