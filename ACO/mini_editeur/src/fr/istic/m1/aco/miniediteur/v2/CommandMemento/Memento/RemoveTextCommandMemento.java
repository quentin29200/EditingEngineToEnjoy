package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento;

import fr.istic.m1.aco.miniediteur.v2.Command.Command;

/**
 * Created by Quentin on 21/11/2015.
 */
public class RemoveTextCommandMemento implements Memento {
    private String name="RemoveTextCommand";

    public String getCmd() {
        return this.name;
    }
}
