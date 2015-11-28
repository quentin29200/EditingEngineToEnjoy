package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento;

import fr.istic.m1.aco.miniediteur.v2.Command.Command;

/**
 * Created by Quentin on 21/11/2015.
 */
public class SelectMemento implements Memento {
    private String name="Select";
    private int select_start;
    private int select_lenght;

    public SelectMemento(int select_start, int select_lenght) {
        this.select_start = select_start;
        this.select_lenght = select_lenght;
    }

    public int getSelect_start() {
        return select_start;
    }

    public int getSelect_lenght() {
        return select_lenght;
    }

    public String getCmd() {
        return this.name;
    }
}
