package fr.istic.m1.aco.miniediteur.v1.Receiver;

import java.util.Observable;

/**
 * <b>EditingEngineImpl is the implementation of the interface of the engine.</b>
 * <p>
 * The engine will interact with quite everything.
 * It extends Observable to call the ConcreteCommand methods.
 * It implements EditingEngine interface.
 * </p>
 *
 * @version 1.0
 */
public class EditingEngineImpl extends Observable implements EditingEngine  {

    /**
     * The buffer
     * Its content is binded with the content of the JTextArea.
     */
    private Buffer buffer;

    /**
     * The clipboard
     * Its content will be used to paste strings.
     */
    private Clipboard clipboard;

    /**
     * The select
     * If select.length > 0  : Its content can be copy or cut.
     */
    private Select select;

    /**
     * Constructor of the EditingEngineImpl
     * Initialize a new Buffer, a new Clipboard and a new SelectCommand object.
     */
    public EditingEngineImpl() {
      this.buffer = new Buffer();
      this.clipboard = new Clipboard();
      this.select = new Select();
    }

    /**
     * getBuffer method
     * Get the content of the buffer.
     *
     * @return Buffer
     *  content of the buffer
     */
    public Buffer getBuffer() {
        return buffer;
    }

    /**
     * select method
     * Permits to select some text.
     */
    public void select(int begin, int length) {
      if(begin >= 0 && length>=0) {
         this.select.setBegin(begin);
         this.select.setLength(length);
      }
    }

    /**
     * getselect method
     * Get the content of the selection.
     *
     * @return String
     *  content of the selection (not the object)
     */
    public String getselect(){
        // Start index of the selection
        int begin = this.select.getBegin();
        // Length of the selection (to calculate the end index of the selection)
        int length = this.select.getLength();
        // If there is a string or a character selected
        if (length > 0 ) {
            // Return only the substring corresponding to the selection
            return this.buffer.getAreaTxt().substring(begin,begin+length);
        } else {
            // Return an empty string
            return "";
        }
    }

    /**
     * returnSelect method
     * Get the object SelectCommand used with our engine.
     *
     * @return SelectCommand
     *  Object SelectCommand
     */
    public Select returnSelect() {
        return this.select;
    }

    /**
     * enterchar method
     * Add a new character (and not a String) in the JTextArea.
     * Add it to the buffer and handle the caret/selection position.
     */
    public void enterchar(char c) {
       int begin = this.select.getBegin();
      // String text;
       if (this.select.getLength() == 0) {
           //text = this.buffer.getAreaTxt().substring(0, begin) + c + this.buffer.getAreaTxt().substring(begin);
           this.buffer.getAreaTxt().insert(begin, Character.toString(c));
           this.select.setBegin(begin + 1);
           System.out.println("Selection changée à "+this.select.getBegin());
       } else {
           //text = this.buffer.getAreaTxt().substring(0, begin) + c + this.buffer.getAreaTxt().substring(begin + this.select.getLength());
           this.buffer.getAreaTxt().replace(begin, begin + this.select.getLength(), Character.toString(c) );
           this.select.setBegin(begin + 1);
           this.select.setLength(0);
           System.out.println("Add / Before Buffer : "+this.buffer.getAreaTxt().toString());
       }
       System.out.println("Add / After Buffer : " + this.buffer.getAreaTxt().toString());
       this.setChanged();
       notifyObservers();
    }

    /**
     * remove method
     * Remove the content of the selection (if exists) or the character just before the caret.
     */
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

    /**
     * copy method
     * CopyCommand the content of the selection and put it inside the clipboard.
     * It doesn't remove the content of the current selection.
     */
    public void copy() {
        // If something is selected
        if( this.select.getLength()>0 ) {
            // Set the clipboard with the value of the selection
            this.clipboard.setCb( this.getselect() );
            System.out.println("CopyCommand the following string in the clipboard : " + this.getselect());
        }
    }

    /**
     * cut method
     * CutCommand the content of the selection and put it inside the clipboard.
     * It removes the content of the current selection.
     */
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

    /**
     * paste method
     * PasteCommand the content of the clipboard and put it inside the buffer.
     * It doesn't remove the content of the current clipboard.
     */
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