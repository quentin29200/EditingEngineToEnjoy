package fr.istic.m1.aco.miniediteur.v2.CommandMemento.Memento;

/**
 * <b>Memento is the interface which represents a Command during Memento interaction.</b>
 * <p>
 * During the replay process, the engine will need to know what Command have been saved
 * and Memento provides it.
 * </p>
 *
 * @version 2.0
 */
public interface Memento {

    /**
     * getCmd method
     * Return the name of the Command from a saved Memento.
     *
     * @return String
     *  The name of the Command
     */
    public String getCmd();
}
