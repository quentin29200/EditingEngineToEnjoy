package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento;

/**
 * <b>EnterTextCommandMemento is a Memento</b>
 * <p>
 * It provides a structure following the Memento to permits to save it and replay it later.
 * </p>
 *
 * @version 2.0
 */
public class EnterTextCommandMemento implements Memento {

    /**
     * The name of the command
     * Permits to know the name of the Command and reload its associated features during the replay process.
     *
     * @see fr.istic.m1.aco.miniediteur.v2.Command.EnterTextCommand
     */
    private String name="EnterTextCommand";

    /**
     * The lastchar written (keydown) by the user
     * Permits to return the last content written by the user.
     *
     * @see fr.istic.m1.aco.miniediteur.v2.Command.EnterTextCommand
     */
    private String lastchar;

    /**
     * Constructor
     * Change the value of the last character written by the user.
     * A call to the EnterTextCommandMemento will change the last char.
     *
     * @param lastchar
     *  The value of the last character
     */
    public EnterTextCommandMemento(String lastchar) {
        this.lastchar = lastchar;
    }

    /**
     * getLastchar method
     * Return the value of the last character written by the user. kind of getter.
     *
     * @return String
     *  The value of the last character
     */
    public String getLastchar() { return lastchar; }

    /**
     * getCmd method
     * Return the name of the Command. kind of getter.
     *
     * @return String
     *  The name of the Command
     */
    public String getCmd() { return this.name; }
}
