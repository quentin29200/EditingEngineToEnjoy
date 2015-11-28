package fr.istic.m1.aco.miniediteur.v1.Receiver;

/**
 * <b>Buffer</b>
 * <p>
 * It provides a storage for our JTextArea content which is easier to manipulate.
 * </p>
 *
 * @version 1.0
 */
public class Buffer {

	/**
	 * The content of the JTextArea
	 * Permits to bind the JTextArea with a string content.
	 */
  	private StringBuffer areaTxt;

	/**
	 * Constructor of the Buffer
	 * Initialize an empty StringBuffer.
	 */
	public Buffer() {
		this.areaTxt = new StringBuffer();
		this.areaTxt.append("");
	}

	/**
	 * toString method
	 * Display the content of the JTextArea to the user.
	 *
	 * @return String message
	 */
	public String toString() {
		return areaTxt.toString();
	}

	/**
	 * getAreaTxt method
	 * Get the content of the JTextArea
	 *
	 * @return StringBuffer content of the JTextArea
	 */
	public StringBuffer getAreaTxt() {
	return areaTxt;
}

	/**
	 * setAreaTxt method
	 * Set a new content to the JTextArea
	 *
	 * @param areaTxt
	 * 	new content of the JTextArea
	 */
	public void setAreaTxt(StringBuffer areaTxt) {
		this.areaTxt = areaTxt;
	}
}