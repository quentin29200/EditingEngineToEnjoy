package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator;

import fr.istic.m1.aco.miniediteur.v2.Command.Command;
import fr.istic.m1.aco.miniediteur.v2.Command.EnterTextCommand;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.EnterTextCommandMemento;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

public class EnterTextCommandRegister extends EnterTextCommand implements CommandRegister {

    private Command cmd;
    private IHMInvoker ihm;
    private Register reg;
    private EditingEngine engine;

	public EnterTextCommandRegister(EditingEngine engine, IHMInvoker ihm, Register reg) {
		super(engine, ihm);
        this.reg = reg;
        this.ihm = ihm;
        this.engine = engine;
	}

	public void execute() {
		super.execute();
	}

    public Memento getMemento() {
        System.out.println("EnterTextCommandRegister : Enregistrement dans le Memento");
        return new EnterTextCommandMemento(this.ihm.getLastchar());
    }

    public void setMemento(Memento m) {
        char c = ((EnterTextCommandMemento)m).getLastchar();
        System.out.println("EnterTextCommandRegister : Execute une commande enregistrée");
        this.engine.enterchar(c);
    }

}
