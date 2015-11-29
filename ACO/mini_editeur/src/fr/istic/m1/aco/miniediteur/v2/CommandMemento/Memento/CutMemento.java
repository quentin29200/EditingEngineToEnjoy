package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento;

/**
 * <b>CutMemento is a Memento</b>
 * <p>
 * It provides a structure following the Memento to permits to save it and replay it later.
 * </p>
 *
 * @version 2.0
 */
public class CutMemento implements Memento {

    /**
     * The name of the command
     * Permits to know the name of the Command and reload its associated features during the replay process.
     *
     * @see fr.istic.m1.aco.miniediteur.v2.Command.CutCommand
     */
    private String name="CutCommand";

    /**
     * getCmd method
     * Return the name of the Command. kind of getter.
     *
     * @return String
     *  The name of the Command
     */
    public String getCmd() { return this.name; }
}
