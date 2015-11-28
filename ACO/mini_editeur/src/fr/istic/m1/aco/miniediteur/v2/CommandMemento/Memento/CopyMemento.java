package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento;

import fr.istic.m1.aco.miniediteur.v2.Command.Command;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator.CommandRegister;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator.CopyRegister;

/**
 * Created by Quentin on 21/11/2015.
 */
public class CopyMemento implements Memento {
    private String name="CopyCommand";

    public String getCmd() {
        return this.name;
    }
}
