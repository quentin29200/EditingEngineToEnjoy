package fr.istic.m1.aco.miniediteur.v2.Command;

import fr.istic.m1.aco.miniediteur.v2.Invoker.IHMInvoker;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

public class EnterTextCommand implements Command {

	private EditingEngine engine;
	private IHMInvoker ihm;

	public EnterTextCommand(EditingEngine engine, IHMInvoker ihm) {
		this.engine = engine;
		this.ihm = ihm;
	}


	public void execute() {
		System.out.println("EnterTxtCommand // caret à : "+this.ihm.getTextArea().getCaretPosition());
		System.out.println("Text of area : "+this.ihm.getTextArea().getText());
		String c = this.ihm.getLastchar();
		System.out.println("Char enter : "+c);
		this.engine.enterchar(c);
	}

}
