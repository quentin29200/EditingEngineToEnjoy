package fr.istic.m1.aco.miniediteur.v2.Receiver;

import fr.istic.m1.aco.miniediteur.v2.Receiver.Buffer;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Clipboard;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;

import java.util.Observable;


public class EditingEngineImpl extends Observable implements EditingEngine  {
   // =========
   // VARIABLES
   // =========
    private Buffer buffer;
    private Clipboard clipboard;
    private Select select;

   public EditingEngineImpl() {
      this.buffer = new Buffer();
      this.clipboard = new Clipboard();
      this.select = new Select();
   }

    public Buffer getBuffer() {
        return buffer;
    }

    /**
    * Method 		: selectionner()
    * Parameters 	: None
    * Objectives 	: Get the text which has been selected by the user
    */
   public void select(int begin, int length) {
      if(begin >= 0 && length>=0) {
         this.select.setBegin(begin);
         this.select.setLength(length);
      }

   }

    public Select returnSelect() {
        return this.select;
    }

   public String getselect(){
      // Dans le buffer (contenu de notre fichier courant)
      // On recupere l'indice de depart
      // et on recpere dans une variable le contenu du texte a partir de l'indice de
      // depart jusqu'a l'indice de depart + longueur
      int begin = this.select.getBegin();
      int length = this.select.getLength();
      if (length > 0 ) {
         return this.buffer.getAreaTxt().substring(begin,begin+length);
      } else {
         return "";
      }
   }

   public void enterchar(String c) {
       int begin = this.select.getBegin();
       System.out.println("begin" + this.select.getBegin());
      // String text;
       System.out.println("Add / Before Buffer : "+this.buffer.getAreaTxt().toString());
       if(c=="\n") {
           this.buffer.getAreaTxt().append(c);
       } else {
           if (this.select.getLength() == 0) {
               //text = this.buffer.getAreaTxt().substring(0, begin) + c + this.buffer.getAreaTxt().substring(begin);
               this.buffer.getAreaTxt().insert(begin, c);
               this.select.setBegin(begin + 1);
               System.out.println("Selection changée à " + this.select.getBegin());
           } else {
               //text = this.buffer.getAreaTxt().substring(0, begin) + c + this.buffer.getAreaTxt().substring(begin + this.select.getLength());
               this.buffer.getAreaTxt().replace(begin, begin + this.select.getLength(), c);
               this.select.setBegin(begin + 1);
               this.select.setLength(0);
           }
       }
       System.out.println("Add / After Buffer : " + this.buffer.getAreaTxt().toString());
       this.setChanged();
       notifyObservers();
   }

    public void remove() {
        int begin = this.select.getBegin();
        System.out.println("Remove / Before Buffer : "+this.buffer.getAreaTxt().toString());
        if (this.select.getLength() > 0) {
            this.buffer.getAreaTxt().replace(begin,begin + this.select.getLength(), "" );
            this.select.setLength(0);
        } else if (begin > 0) {
            this.buffer.getAreaTxt().replace(begin - 1, begin, "" );
            this.select.setBegin(begin - 1);
        }
        System.out.println("Remove / After Buffer : " + this.buffer.getAreaTxt().toString());
        notifyObservers();
    }

    public void copy() {
        // If something is selected
        if( this.select.getLength()>0 ) {
            // Set the clipboard with the value of the selection
            this.clipboard.setCb( this.getselect() );
            System.out.println("Copy the following string in the clipboard : " + this.getselect());
        }
    }

    public void cut() {
        // Transfers the currently selected range in the associated text area to the clipboard,
        // removing the contents from the text area. The current selection is reset. Does nothing for null selections.
        System.out.println(this.select.getLength());
        // If something is selected in the JTextArea
        // -----------------------------------------
        if( this.select.getLength() > 0 )
        {
            // Transfers the selected text into the clipboard
            // ----------------------------------------------
            this.clipboard.setCb(getselect());
            System.out.println("Content of the selection cut into the clipboard : " + this.getselect());

            // Removing the contents from the JTextArea
            // ----------------------------------------
            // Get the offset of the first character of the selection
            // Get the offset of the last character of the selection
            // Get the offset of the last character of the buffer
            // Get the content of the buffer (the JTextArea)
            // Set the content of the buffer without the content of the selection
            int offsetFirstCharSelect = this.select.getBegin();
            int offsetLastCharSelect = this.select.getBegin() + this.select.getLength();
            System.out.println("Offset begin selection : " + offsetFirstCharSelect + ", offset last char selection : " + offsetLastCharSelect);
            this.buffer.getAreaTxt().replace(offsetFirstCharSelect, offsetLastCharSelect, "" );
            this.select.setBegin(offsetFirstCharSelect + 1);

            // The current selection is reset
            // ------------------------------
            this.select.setLength(0);

            // Notify the content of the JTextArea
            this.setChanged();
            this.notifyObservers();
        }
    }
   
    public void paste() {
        // Offset of the caret
        int offsetCaret = this.select.getBegin();
        // Content of the clipboard
        String cbContent = this.clipboard.getCb();
        // Content of the buffer
        StringBuffer txt = this.buffer.getAreaTxt();
        // Offset of the first character of the buffer
        int offsetFirstCharBuffer = 0;
        // Offset of the last character of the buffer
        int offsetLastCharBuffer = this.buffer.getAreaTxt().length();

        // If there is something in the clipboard
        if( cbContent != null ) {
            // If there isn't any current selection
            if( this.select.getLength() == 0 ) {
                // Get the text between the caret offset and the end of the buffer
                String txtCaretIntoLastCharBuffer = txt.substring(offsetCaret, offsetLastCharBuffer);
                // Get the text between the begin of the buffer and the caret offset
                String txtIntoCaretOffset = txt.substring(offsetFirstCharBuffer, offsetCaret);
                // Concatenation to make the new content
                String contentBuffer = txtIntoCaretOffset + cbContent + txtCaretIntoLastCharBuffer;
                // Kind of cast you know ... To use the setAreaText(...) method
                StringBuffer newContentBuffer = new StringBuffer(contentBuffer);
                // Set the JAreaText with its new content
                this.buffer.setAreaTxt(newContentBuffer);
            }
            else {
                // Offset of the first character in the selection
                int offsetFirstCharSelect = this.select.getBegin();
                // Offset of the last character in the selection
                int offsetLastCharSelect = this.select.getBegin() + this.select.getLength();
                // Replace the selection by the content of the clipboard
                this.buffer.getAreaTxt().replace(offsetFirstCharSelect, offsetLastCharSelect, cbContent);
                // Force the offset of the caret to the begin of the buffer .. Can be edited ..
                this.select.setBegin(0);
                // Set the length of the selection to 0
                this.select.setLength(0);

            }
            // Notify the observers
            this.setChanged();
            this.notifyObservers();
        }
        else
        {
            System.out.println("--- The clipboard is empty.");
        }
    }
}