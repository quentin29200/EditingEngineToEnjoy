package fr.istic.m1.aco.miniediteur.v2.Invoker;

import fr.istic.m1.aco.miniediteur.v2.Command.*;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator.*;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngineImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
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
 * @version 2.0
 */
public class IHMInvoker extends JFrame  implements Observer {

	/**
	 * The JTextArea
	 * Contains the text written by the user.
	 */
	private JTextArea textArea;

	/**
	 * The last character written by the user.
	 */
    private String lastchar;

	/**
	 * To know if the user launch runRecord() or not.
	 */
	private boolean isRecorded;

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
	 * The startregister Command - Call the Command interface.
	 */
	private Command startregister;

	/**
	 * The stopregister Command - Call the Command interface.
	 */
	private Command stopregister;

	/**
	 * The replayregister Command - Call the Command interface.
	 */
	private Command replayregister;

    /**
     * The cutrec Command - Call the CommandRegister interface.
     */
	private CommandRegister cutrec;

    /**
     * The copyrec Command - Call the CommandRegister interface.
     */
	private CommandRegister copyrec;

    /**
     * The pasterec Command - Call the CommandRegister interface.
     */
	private CommandRegister pasterec;

    /**
     * The entertxtrec Command - Call the CommandRegister interface.
     */
	private CommandRegister entertxtrec;

    /**
     * The removetxtrec Command - Call the CommandRegister interface.
     */
	private CommandRegister removetxtrec;

    /**
     * Constructor of the user interface.
     * Bind the engine and the IHM.
     *
     * @param e
     *  EditingEngineImpl - Implements EditingEngine
     */
	public IHMInvoker(EditingEngineImpl e) {

        // We don't run the record process since the beginning
		this.isRecorded = false;

		// Button icons loading
		Icon img_copy = new ImageIcon(this.getClass().getResource("img/copier.png"));
		Icon img_paste = new ImageIcon(this.getClass().getResource("img/coller.png"));
		Icon img_cut = new ImageIcon(this.getClass().getResource("img/couper.png"));
		Icon img_rec = new ImageIcon(this.getClass().getResource("img/rec.png"));
		Icon img_replay = new ImageIcon(this.getClass().getResource("img/repeter.png"));
        Icon img_stop = new ImageIcon(this.getClass().getResource("img/stop.png"));

		// Buttons creation
		JButton button_cut = new JButton();
		JButton button_paste = new JButton();
		JButton button_copy = new JButton();
		JButton button_record = new JButton();
		JButton button_replay = new JButton();

        // Frame settings
	    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	    setTitle("EditingEngine to enjoy !");
	    setFont(new java.awt.Font("Mangal", 0, 14)); // NOI18N
	    setLocation(new java.awt.Point(300, 300));
	    setMaximumSize(new java.awt.Dimension(557, 410));
	    setMinimumSize(new java.awt.Dimension(557, 410));
	    setName("superFrame"); // NOI18N
	    setResizable(false);

        // Button CUT
		button_cut.setIcon(img_cut);
	    button_cut.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
				button_cutActionPerformed(evt);
	        }
	    });

        // Button PASTE
        button_paste.setIcon(img_paste);
		button_paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_pasteActionPerformed(evt);
			}
		});

        // Button COPY
        button_copy.setIcon(img_copy);
		button_copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				button_copyActionPerformed(evt);
			}
		});

        // Button REPLAY
        button_replay.setIcon(img_replay);
        button_replay.setEnabled(false);
		button_replay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				replayregister.execute();
			}
		});

        // Button RECORD
		button_record.setIcon(img_rec);
        button_record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (isRecorded) {
                    button_record.setIcon(img_rec);
					stopregister.execute();
					isRecorded = false;
                    button_replay.setEnabled(true);
                } else {
                    button_record.setIcon(img_stop);
					button_replay.setEnabled(false);
					startregister.execute();
					isRecorded = true;
                }
            }
        });

        // JPanels and layout creation
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        JPanel panel = new JPanel();

        // Layout will work in line
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        // Add buttons to the panel
        panel.add(button_copy);
        panel.add(button_cut);
        panel.add(button_paste);
        panel.add(button_record);
        panel.add(button_replay);
        content.add(panel);

        // JPanel creation and settings
        JPanel panelArea = new JPanel();
        textArea = new JTextArea(30, 60);
        textArea.setLineWrap(true);
        panelArea.add(textArea);
        content.add(panelArea);

        // Caret settings
		CaretListener caret = new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				int i = Math.min(e.getDot(), e.getMark());
				int j = Math.max(e.getDot(), e.getMark());
				int l = j - i;
				select.setSelect(i, l);
				select.execute();
			}
		};
		textArea.addCaretListener(caret);
		textArea.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				e.consume();
				lastchar = String.valueOf(e.getKeyChar());
				if ((int)e.getKeyChar() == KeyEvent.VK_ENTER) {
					System.out.println("Yolo Enter");
					lastchar = "\n";
				}
				if((int)e.getKeyChar() == KeyEvent.VK_BACK_SPACE ||(int)e.getKeyChar() == KeyEvent.VK_DELETE) {
					return;
				}
				if(!e.isActionKey()) {
					System.out.println("Yili");
					if (isRecorded) {
						entertxtrec.execute();
					} else {
						enterTextCommand.execute();
					}
				}

			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					if (isRecorded) {
                        removetxtrec.execute();
					} else {
                        removeTextCommand.execute();
					}
				}
			}
		});

        // Add the previous element to the panel
        this.getContentPane().add(content);

        // Set visible the frame and the elements themselves
	    pack();
	}

    /**
     *
     * addcmds method
     * Initialize the commands of the IHM.
     *
     * @param h
     *  Contains a string and the associated ConcreteCommands
     */
	public void addcmds(HashMap<String, Command> h) {
		this.enterTextCommand = h.get("enterTextCommand");
		this.removeTextCommand = h.get("removeTextCommand");
		this.select = (SelectCommand)h.get("select");
		this.cut = h.get("cut");
		this.copy = h.get("copy");
		this.paste = h.get("paste");
		this.startregister = h.get("startregister");
		this.stopregister = h.get("stopregister");
		this.replayregister = h.get("replayregister");
	}

    /**
     *
     * addcmdsrec method
     * Initialize the recordable commands of the IHM.
     *
     * @param h
     *  Contains a string and the associated ConcreteCommands
     */
	public void addcmdsrec(HashMap<String, CommandRegister> h) {
		this.entertxtrec = h.get("entertxtrec");
		this.removetxtrec = h.get("removetxtrec");
		this.cutrec = h.get("cutrec");
		this.copyrec = h.get("copyrec");
		this.pasterec = h.get("pasterec");
	}

    // List of the actionPerformed methods to execute the ConcreteCommand
	private void button_pasteActionPerformed(ActionEvent evt) {
		if (this.isRecorded) { pasterec.execute(); } else { paste.execute(); }
    }
	private void button_copyActionPerformed(ActionEvent evt) {
		if (this.isRecorded) { copyrec.execute(); } else { copy.execute(); }
	}
	private void button_cutActionPerformed(ActionEvent evt) {
		if (this.isRecorded) { cutrec.execute(); } else { cut.execute(); }
	}

    // Return the index of the beginning of the selection
    public int getselectstart() { return this.textArea.getSelectionStart(); }

    // Return the index of the ending of the selection
    public int getselectend() { return this.textArea.getSelectionEnd(); }

    // Return the content of the JTextArea
    public JTextArea getTextArea() { return textArea; }

    // Return the last character written by the user
    public String getLastchar() { return lastchar; }

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
