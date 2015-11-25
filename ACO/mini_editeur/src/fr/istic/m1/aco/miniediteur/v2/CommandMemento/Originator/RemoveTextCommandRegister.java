package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator;

import fr.istic.m1.aco.miniediteur.v2.Command.RemoveTextCommand;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.RemoveTextCommandMemento;
import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

public class RemoveTextCommandRegister extends RemoveTextCommand implements CommandRegister {
    private Register reg;

    public RemoveTextCommandRegister(EditingEngine engine, IHMInvoker ihm, Register reg) {
        super(engine, ihm);
        this.reg = reg;
    }


    public void execute() {
        super.execute();
    }

    public Memento getMemento() {
        System.out.println("RemoveTextCommandRegister : Enregistrement dans le Memento");
        return new RemoveTextCommandMemento();
    }

    public void setMemento(Memento m) {
        System.out.println("RemoveTextCommandRegister : Execute une commande enregistrée");
        this.execute();
    }

}
