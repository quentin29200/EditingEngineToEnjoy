package fr.istic.m1.aco.miniediteur.v1.Receiver;

/**
 * <b>EditingEngine is the interface of the engine.</b>
 * <p>
 * An engine has to do able to implements all those methods.
 * </p>
 *
 * @version 1.0
 */
public interface EditingEngine {

   /**
    * copy method
    * Add a string in the clipboard without removing text in the JTextArea.
    */
   public void copy();

   /**
    * paste method
    * Edit the content of the Buffer and the JTextArea following the content of the clipboard.
    */
   public void paste();

   /**
    * cut method
    * Add a string in the clipboard and remove the selected text in the JTextArea.
    */
   public void cut();

   /**
    * select method
    * Highlight a string content which could be copy, cut or paste.
    *
    * @param start
    *   Beginning of the selection
    * @param length
    *   Length of the selection
    */
   public void select(int start, int length);

   /**
    * enterchar method
    * Add a new character in the JTextArea and add it into the buffer too.
    *
    * @param c
    *   Character entered
    */
   public void enterchar(String c);

   /**
    * remove method
    * Remove the selection if the selection isn't empty or the character just before the caret.
    */
   public void remove();

   /**
    * getBuffer method
    * Return the buffer to manipulate it.
    *
    * @return Buffer
    *    The buffer binded to the JTextArea.
    */
   public Buffer getBuffer();

   /**
    * returnSelect method
    * Return the selected text (if exists, nothing else) to manipulate it.
    *
    * @return Select
    *    The text selected by the user.
    */
   public Select returnSelect();
}