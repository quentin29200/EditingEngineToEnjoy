package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento;

import fr.istic.m1.aco.miniediteur.v2.Command.Command;

/**
 * Created by Quentin on 21/11/2015.
 */
public class EnterTextCommandMemento implements Memento {
    private String name="EnterTextCommand";
    private String lastchar;

    public EnterTextCommandMemento(String lastchar) {
        this.lastchar = lastchar;
    }

    public String getLastchar() {
        return lastchar;
    }

    public String getCmd() {
        return this.name;
    }
}
