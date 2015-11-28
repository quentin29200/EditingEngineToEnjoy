package fr.istic.m1.aco.miniediteur.v1.Receiver;

/**
 * <b>SelectCommand is the object which will handle the content of the selection.</b>
 * <p>
 * It permits to handle the stirng selection in the JTextArea.
 * Transmits it to the buffer or the clipboard by function of the called ConcreteCommand.
 * </p>
 *
 * @see fr.istic.m1.aco.miniediteur.v1.Receiver.Clipboard
 * @see fr.istic.m1.aco.miniediteur.v1.Invoker.IHMInvoker
 * @version 1.0
 */
public class Select {

    /**
     * The index of the beginning of the selection.
     * Permits to know where does the selection begin.
     */
    private int begin;

    /**
     * The length of the selection.
     * Permits to know where does the selection finish.
     */
    private int length;

    /**
     * getBegin method
     * Get the beginning index of the selection.
     *
     * @return int
     *  Index of the begin
     */
    public int getBegin() {
        return begin;
    }

    /**
     * setBegin method
     * Set the beginning index of the selection.
     *
     * @param begin
     *  Beginning of the selection
     */
    public void setBegin(int begin) {
        this.begin = begin;
    }

    /**
     * getLength method
     * Get the length of the selection.
     *
     * @return int
     *  Index of the begin
     */
    public int getLength() {
        return length;
    }

    /**
     * setSelect method
     * Set the length of the selection.
     *
     * @param length
     *   Length of the selection
     */
    public void setLength(int length) {
        if (length > 0) {
            this.length = length;
        } else {
            this.length = 0;
        }
    }
}
