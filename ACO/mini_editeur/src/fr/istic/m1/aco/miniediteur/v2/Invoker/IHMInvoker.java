/**
 * ==================================================================================
 * PACKAGE
 * ==================================================================================
 */
package fr.istic.m1.aco.miniediteur.v2.Invoker;

/**
 * ==================================================================================
 * IMPORTS
 * ==================================================================================
 */
import fr.istic.m1.aco.miniediteur.v2.Command.*;
import fr.istic.m1.aco.miniediteur.v2.CommandMemento.Originator.*;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.Register;
import fr.istic.m1.aco.miniediteur.v2.Receiver.Cartaker.RegisterImpl;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngine;
import fr.istic.m1.aco.miniediteur.v2.Receiver.EditingEngineImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


/**
 * ==================================================================================
 * CLASS START
 * ==================================================================================
 */
public class IHMInvoker extends JFrame  implements Observer
{
	private JTextArea textArea;
    private char lastchar;
	private boolean isRecorded;

	/* List of command */
	private Command cut;
	private Command copy;
	private Command paste;
	private Select select;
	private Command enterTextCommand;
	private Command removeTextCommand;
	private Command startregister;
	private Command stopregister;
	private Command replayregister;

	/* List of CommandRegister */
	private CommandRegister cutrec;
	private CommandRegister copyrec;
	private CommandRegister pasterec;
	private CommandRegister entertxtrec;
	private CommandRegister removetxtrec;

	/**
	 *  INITIALIZE IHM
	 *
	 */

	public IHMInvoker(EditingEngineImpl e) {
		this.isRecorded = false;

		// Chargement des images
		Icon img_copy = new ImageIcon(this.getClass().getResource("img/copier.png"));
		Icon img_paste = new ImageIcon(this.getClass().getResource("img/coller.png"));
		Icon img_cut = new ImageIcon(this.getClass().getResource("img/couper.png"));
		Icon img_rec = new ImageIcon(this.getClass().getResource("img/rec.png"));
		Icon img_replay = new ImageIcon(this.getClass().getResource("img/repeter.png"));
        Icon img_stop = new ImageIcon(this.getClass().getResource("img/stop.png"));

		// Initialisation de l'interface
		JButton button_cut = new JButton();
		JButton button_paste = new JButton();
		JButton button_copy = new JButton();
		JButton button_record = new JButton();
		JButton button_replay = new JButton();
		this.textArea = new JTextArea();
		JMenuBar menu = new JMenuBar();
		JMenu menu_file = new JMenu();
		JMenuItem menu_file_quit = new JMenuItem();
		JMenu menu_edit = new JMenu();
		JMenuItem menu_edit_copy = new JMenuItem();
		JMenuItem menu_edit_cut = new JMenuItem();
		JMenuItem menu_edit_paste = new JMenuItem();
		JMenu menu_about = new JMenu();
		JMenuItem menu_about_aboutus = new JMenuItem();

	    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	    setTitle("EditingEngine to enjoy !");
	    setFont(new java.awt.Font("Mangal", 0, 14)); // NOI18N
	    setLocation(new java.awt.Point(300, 300));
	    setMaximumSize(new java.awt.Dimension(557, 410));
	    setMinimumSize(new java.awt.Dimension(557, 410));
	    setName("superFrame"); // NOI18N
	    setResizable(false);

	    //button_cut.setText("Cut");
		button_cut.setIcon(img_cut);
	    button_cut.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
				button_cutActionPerformed(evt);
	        }
	    });

	    //button_paste.setText("Paste");
        button_paste.setIcon(img_paste);
		button_paste.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button_pasteActionPerformed(evt);
			}
		});

	    //button_copy.setText("Copy");
        button_copy.setIcon(img_copy);
		button_copy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button_copyActionPerformed(evt);
			}
		});

        button_replay.setIcon(img_replay);
        button_replay.setEnabled(false);
		button_replay.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				replayregister.execute();
			}
		});

		button_record.setIcon(img_rec);
        button_record.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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


        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        JPanel panel = new JPanel();
        //On définit le layout en lui indiquant qu'il travaillera en ligne
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(button_copy);
        panel.add(button_cut);
        panel.add(button_paste);
        panel.add(button_record);
        panel.add(button_replay);
        content.add(panel);

        /* TEXT AREA */
        JPanel panelArea = new JPanel();
        textArea = new JTextArea(30, 60);
        textArea.setLineWrap(true);
		textArea.setToolTipText("");
        panelArea.add(textArea);
        content.add(panelArea);
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
				lastchar = e.getKeyChar();
				if (lastchar != '\b') {
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

	    menu.setBackground(new java.awt.Color(153, 153, 153));
	    menu.setForeground(new java.awt.Color(255, 255, 255));
	    menu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
	    menu.setPreferredSize(new java.awt.Dimension(253, 30));

	    menu_file.setText("File");
	    menu_file.setToolTipText("");
	    menu_file.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
	    menu_file.setIconTextGap(30);

	    menu_file_quit.setText("Quit");
	    menu_file.add(menu_file_quit);

	    menu.add(menu_file);

	    menu_edit.setText("Edit");
	    menu_edit.setIconTextGap(30);

	    menu_edit_copy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_COPY, 0));
	    menu_edit_copy.setText("Copy");
	    menu_edit.add(menu_edit_copy);

	    menu_edit_cut.setText("Cut");
	    menu_edit_cut.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            menu_edit_cutActionPerformed(evt);
	        }
	    });
	    menu_edit.add(menu_edit_cut);

	    menu_edit_paste.setText("Paste");
	    menu_edit.add(menu_edit_paste);

	    menu.add(menu_edit);

	    menu_about.setText("About");
	    menu_about.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	    menu_about.setIconTextGap(30);

	    menu_about_aboutus.setText("About us ...");
	    menu_about.add(menu_about_aboutus);

	    menu.add(menu_about);

	    setJMenuBar(menu);

        this.getContentPane().add(content);

	    pack();
	}

	/**
	 * INITIALIZE COMMANDS AND COMMANDS REGISTER FROM HASHMAP
	 *
	 */

	public void addcmds(HashMap<String, Command> h) {
		// Initialisation des commandes
		this.enterTextCommand = h.get("enterTextCommand");
		this.removeTextCommand = h.get("removeTextCommand");
		this.select = (Select)h.get("select");
		this.cut = h.get("cut");
		this.copy = h.get("copy");
		this.paste = h.get("paste");
		this.startregister = h.get("startregister");
		this.stopregister = h.get("stopregister");
		this.replayregister = h.get("replayregister");
	}

	public void addcmdsrec(HashMap<String, CommandRegister> h) {
		// Initialisation des commandes enregistrables
		this.entertxtrec = h.get("entertxtrec");
		this.removetxtrec = h.get("removetxtrec");
		this.cutrec = h.get("cutrec");
		this.copyrec = h.get("copyrec");
		this.pasterec = h.get("pasterec");
	}


	/**
	 *  LISTENERS FOR BUTTUNS
	 *
	 */

	private void button_pasteActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println("button_cutActionPerformed called in IHMInvoker");
		if (this.isRecorded) {
			pasterec.execute();
		} else {
			paste.execute();
		}
    }
	private void button_copyActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println("button_cutActionPerformed called in IHMInvoker");
		if (this.isRecorded) {
			copyrec.execute();
		} else {
			copy.execute();
		}
	}
	private void button_cutActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println("button_cutActionPerformed called in IHMInvoker");
		if (this.isRecorded) {
			cutrec.execute();
		} else {
			cut.execute();
		}
	}

	private void menu_edit_cutActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println("menu_edit_cutActionPerformed called in IHMInvoker YEAAAAAH");
    }

	public int getselectstart() {
		return this.textArea.getSelectionStart();
	}

	public int getselectend() {
		return this.textArea.getSelectionEnd();
	}

	public JTextArea getTextArea() {
		return textArea;
	}

    public char getLastchar() {
        return lastchar;
    }

    public void update(Observable o, Object arg) {
		System.out.println("Update IHM");
		if(o instanceof EditingEngine){
			this.textArea.setText(((EditingEngine) o).getBuffer().toString());
		}
	}
}
