package fr.istic.m1.aco.miniediteur.v1.Receiver;

/**
 * <b>Clipboard</b>
 * <p>
 * It provides a LOCAL (not system) storage for our strings that we would like to paste later.
 * </p>
 *
 * @version 1.0
 */
public class Clipboard {

   /**
    * The content of the clipboard
    * Permits to full the local clipboard with a string content.
    */
   private String txtCb;

   /**
    * getCb method
    * Get the content of the clipboard
    *
    * @return String
    *    content of the clipboard
    */
   public String getCb() {
      return this.txtCb;
   }

   /**
    * setCb method
    * Set a new content to the clipboard when copying or cutting.
    *
    * @param value
    * 	new content of the clipboard
    */
   public void setCb(String value) {
      this.txtCb = value;
   }

}