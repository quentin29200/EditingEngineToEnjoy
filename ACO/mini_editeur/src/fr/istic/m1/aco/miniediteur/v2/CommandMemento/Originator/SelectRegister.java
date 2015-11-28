package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator;

import fr.istic.m1.aco.miniediteur.v2.Command.SelectCommand;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.SelectMemento;
import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

public class SelectRegister extends SelectCommand implements CommandRegister {
    private Register reg;
    private EditingEngine engine;

    public SelectRegister(EditingEngine engine, IHMInvoker ihm, Register reg) {
        super(engine, ihm);
        this.engine = engine;
        this.reg = reg;
    }

    public void execute() {
        this.reg.record(this);
        super.execute();
    }

    public Memento getMemento() {
        System.out.println("SelectRegister : Enregistrement dans le Memento");
        return new SelectMemento(this.engine.returnSelect().getBegin(), this.engine.returnSelect().getLength());
    }

    public void setMemento(Memento m) {
        System.out.println("SelectRegister : Execute une commande enregistrée");
        this.engine.select(((SelectMemento)m).getSelect_start(),((SelectMemento)m).getSelect_lenght());
        //this.execute();
    }
}
