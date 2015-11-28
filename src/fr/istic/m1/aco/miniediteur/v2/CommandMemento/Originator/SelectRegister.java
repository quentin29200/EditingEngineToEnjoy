package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator;

import fr.istic.m1.aco.miniediteur.v2.Command.Select;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.SelectMemento;
import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

public class SelectRegister extends Select implements CommandRegister {
    private Register reg;

    public SelectRegister(EditingEngine engine, IHMInvoker ihm, Register reg) {
        super(engine, ihm);
        this.reg = reg;
    }

    public void execute() {
        super.execute();
    }

    public Memento getMemento() {
        System.out.println("SelectRegister : Enregistrement dans le Memento");
        return new SelectMemento();
    }

    public void setMemento(Memento m) {
        System.out.println("SelectRegister : Execute une commande enregistrée");
        this.execute();
    }
}
