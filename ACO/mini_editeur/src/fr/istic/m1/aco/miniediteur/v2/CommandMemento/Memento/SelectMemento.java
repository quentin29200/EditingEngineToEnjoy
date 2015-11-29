package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento;

/**
 * <b>SelectMemento is a Memento</b>
 * <p>
 * It provides a structure following the Memento to permits to save it and replay it later.
 * </p>
 *
 * @version 2.0
 */
public class SelectMemento implements Memento {

    /**
     * The name of the command
     * Permits to know the name of the Command and reload its associated features during the replay process.
     *
     * @see fr.istic.m1.aco.miniediteur.v2.Command.SelectCommand
     */
    private String name="SelectCommand";

    /**
     * The index of the beginning of the selection
     * Permits to know where, in the buffer, begin the selection.
     *
     * @see fr.istic.m1.aco.miniediteur.v2.Command.SelectCommand
     */
    private int select_start;

    /**
     * The length of the selection
     * Permits to know where, in the buffer, end the selection (by calculate it).
     *
     * @see fr.istic.m1.aco.miniediteur.v2.Command.SelectCommand
     */
    private int select_lenght;

    /**
     * Constructor
     * Initialize the selection with a start and an end index
     *
     * @param select_start
     *  The index of the beginning of the selection
     * @param select_lenght
     *  The length of the selection
     */
    public SelectMemento(int select_start, int select_lenght) {
        this.select_start = select_start;
        this.select_lenght = select_lenght;
    }

    /**
     * getSelect_start method
     * Return the index of the beginning of the selection. kind of getter.
     *
     * @return int
     *  The index of the beginning of the selection
     */
    public int getSelect_start() {
        return select_start;
    }

    /**
     * getSelect_lenght method
     * Return the length of the selection. kind of getter.
     *
     * @return int
     *  The length of the selection
     */
    public int getSelect_lenght() {
        return select_lenght;
    }

    /**
     * getCmd method
     * Return the name of the Command. kind of getter.
     *
     * @return String
     *  The name of the Command
     */
    public String getCmd() { return this.name; }
}
