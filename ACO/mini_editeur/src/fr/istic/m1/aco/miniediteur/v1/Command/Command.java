package fr.istic.m1.aco.miniediteur.v1.Command;

/**
 * <b>Command is the interface which represents a ConcreteCommand.</b>
 * <p>
 * A ConcreteCommand (like copy, cut, paste, ...) call the execute method
 * and do his job.
 * </p>
 *
 * @version 1.0
 */
public interface Command {

    /**
     * execute method
     * Execute the content of the ConcreteCommand.
     */
	public void execute();
}
