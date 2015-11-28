package fr.istic.m1.aco.miniediteur.v1.Invoker;

import fr.istic.m1.aco.miniediteur.v1.Command.*;
import fr.istic.m1.aco.miniediteur.v1.Command.SelectCommand;
import fr.istic.m1.aco.miniediteur.v1.Receiver.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 * <b>IHMInvoker is an Invoker (DP Command)</b>
 * <p>
 * Used as the user interface.
 * Extends JFrame for the interface.
 * Implements Observer to call the actions on click.
 * </p>
 *
 * @version 1.0
 */
public class IHMInvoker extends JFrame  implements Observer {

	/**
	 * The JTextArea
	 * Contains the text written by the user.
	 */
	private JTextArea textArea;

	/**
	 * The editing engine
	 * Permits to interact with the engine.
	 *
	 * @see EditingEngine
	 */
	private EditingEngineImpl engine;

	/**
	 * The last character written by the user.
	 */
    private char lastchar;

	/**
	 * The cut Command - Call the Command interface.
	 */
	private Command cut;

    /**
     * The copy Command - Call the Command interface.
     */
	private Command copy;

    /**
     * The paste Command - Call the Command interface.
     */
	private Command paste;

    /**
     * The select Command - Call the Command interface.
     */
	private SelectCommand select;

    /**
     * The enterTextCommand Command - Call the Command interface.
     */
	private Command enterTextCommand;

    /**
     * The removeTextCommand Command - Call the Command interface.
     */
	private Command removeTextCommand;

    /**
     * Constructor of the user interface.
     * Bind the engine and the IHM.
     *
     * @param e
     *  EditingEngineImpl - Implements EditingEngine
     */
	public IHMInvoker(EditingEngineImpl e) {

		// Engine initialisation
		this.engine = e;
		this.engine.addObserver(this);

		// Commands initialisation
		this.enterTextCommand = new EnterTextCommand(this.engine, this);
		this.removeTextCommand = new RemoveTextCommand(this.engine, this);
		this.select = new SelectCommand(this.engine, this);
		this.cut = new CutCommand(this.engine);
		this.copy = new CopyCommand(this.engine);
		this.paste = new PasteCommand(this.engine);

		// Buttons and JTestArea intanciation
		JButton button_cut = new JButton();
		JButton button_paste = new JButton();
		JButton button_copy = new JButton();
		this.textArea = new JTextArea();

        // Add a vertical scrollbar in case there is too much content
        JScrollPane verticalScroll = new JScrollPane(this.textArea);
        verticalScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(verticalScroll);

        // Frame settings
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("EditingEngine to enjoy !");
        this.setFont(new java.awt.Font("Mangal", 0, 14));
        this.setLocation(new java.awt.Point(300, 300));
        this.setMaximumSize(new Dimension(557, 410));
        this.setMinimumSize(new Dimension(557, 410));
        this.setName("superFrame");
        this.setResizable(false);

        // Add the cut button and the call to its action
	    button_cut.setText("Cut");
	    button_cut.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) { button_cutActionPerformed(evt); }
	    });

        // Add the paste button and the call to its action
	    button_paste.setText("Paste");
		button_paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) { button_pasteActionPerformed(evt); }
		});

        // Add the copy button and the call to its action
	    button_copy.setText("Copy");
		button_copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) { button_copyActionPerformed(evt); }
		});

        // Go to the next line when the content is too long.
		textArea.setLineWrap(true);

        // Add an ActionListener on the caret
		CaretListener caret = new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				int i = Math.min(e.getDot(), e.getMark());
				int j = Math.max(e.getDot(), e.getMark());
				int l = j - i;
				select.setSelect(i, l);
                select.execute();
			}
		};

        // Link the caretListener and the JTextArea
		textArea.addCaretListener(caret);

        // Method to execute the enterTextCommand or the removeTextCommand action
		textArea.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                e.consume();
                lastchar = e.getKeyChar();
                if (lastchar != '\b') {
                    enterTextCommand.execute();
                }
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    removeTextCommand.execute();
                }
            }
        });

        // Join the elements in a GroupLayout
	    GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(button_copy, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                .addGap(126, 126, 126)
                                .addComponent(button_cut, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button_paste, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(textArea, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(32, Short.MAX_VALUE))
        );

        // Using a global Layout
	    layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(66, Short.MAX_VALUE)
                                .addComponent(textArea, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button_paste, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button_copy, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button_cut, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        // Set visible the elements of the application
	    pack();
	}

    // List of the actionPerformed methods to execute the ConcreteCommand
    private void button_pasteActionPerformed(ActionEvent evt) { paste.execute(); }
	private void button_copyActionPerformed(ActionEvent evt) { copy.execute(); }
	private void button_cutActionPerformed(ActionEvent evt) { cut.execute(); }

    // Return the index of the beginning of the selection
	public int getselectstart() { return this.textArea.getSelectionStart(); }

    // Return the index of the ending of the selection
	public int getselectend() { return this.textArea.getSelectionEnd(); }

    // Return the content of the JTextArea
	public JTextArea getTextArea() { return textArea; }

    // Return the last character written by the user
    public char getLastchar() { return lastchar; }

    // Update the content of the buffer, the JTextArea and the position of the caret
    public void update(Observable o, Object arg) {
		System.out.println("Update IHM");
		if(o instanceof EditingEngine){
			EditingEngine engine = (EditingEngine)o;
			final String txt = engine.getBuffer().getAreaTxt().toString();
			final int start = engine.returnSelect().getBegin();
			final int end = start + engine.returnSelect().getLength();

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					textArea.setText(txt);
					textArea.setCaretPosition(start);
					textArea.moveCaretPosition(end);
					System.out.println("position curseur moteur" + start);
					System.out.println("position curseur ihm" + end);
				}
			});
		}
	}
}
