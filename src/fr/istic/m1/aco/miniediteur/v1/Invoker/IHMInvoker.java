/**
 * ==================================================================================
 * PACKAGE
 * ==================================================================================
 */
package fr.istic.m1.aco.miniediteur.v1.Invoker;

/**
 * ==================================================================================
 * IMPORTS
 * ==================================================================================
 */
import fr.istic.m1.aco.miniediteur.v1.Command.*;
import fr.istic.m1.aco.miniediteur.v1.Receiver.EditingEngine;
import fr.istic.m1.aco.miniediteur.v1.Receiver.EditingEngineImpl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 * ==================================================================================
 * CLASS START
 * ==================================================================================
 */
public class IHMInvoker extends JFrame  implements Observer
{
	private JTextArea textArea;
	private EditingEngineImpl engine;
    private char lastchar;
	private Command cut;
	private Command copy;
	private Command paste;
	private Select select;
	private Command enterTextCommand;
	private Command removeTextCommand;


	public IHMInvoker(EditingEngineImpl e) {
		// Initialisation du moteur
		this.engine = e;
		this.engine.addObserver(this);

		// Initialisation des commandes
		this.enterTextCommand = new EnterTextCommand(this.engine, this);
		this.removeTextCommand = new RemoveTextCommand(this.engine, this);
		this.select = new Select(this.engine, this);
		this.cut = new Cut(this.engine);
		this.copy = new Copy(this.engine);
		this.paste = new Paste(this.engine);

		// Initialisation de l'interface
		JButton button_cut = new JButton();
		JButton button_paste = new JButton();
		JButton button_copy = new JButton();
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

	    button_cut.setText("Cut");
	    button_cut.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            button_cutActionPerformed(evt);
	        }
	    });

	    button_paste.setText("Paste");
		button_paste.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button_pasteActionPerformed(evt);
			}
		});

	    button_copy.setText("Copy");
		button_copy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button_copyActionPerformed(evt);
			}
		});

		textArea.setLineWrap(true);
		textArea.setToolTipText("");
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

	    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setHorizontalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	            .addContainerGap()
	            .addComponent(button_copy, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addGap(126, 126, 126)
	            .addComponent(button_cut, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	            .addComponent(button_paste, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addContainerGap())
	        .addGroup(layout.createSequentialGroup()
	            .addGap(24, 24, 24)
	            .addComponent(textArea, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addContainerGap(32, Short.MAX_VALUE))
	    );
	    layout.setVerticalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	            .addContainerGap(66, Short.MAX_VALUE)
	            .addComponent(textArea, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addGap(65, 65, 65)
	            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                .addComponent(button_paste, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addComponent(button_copy, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addComponent(button_cut, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
	            .addContainerGap())
	    );

	    pack();
	}

    private void button_pasteActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println("button_cutActionPerformed called in IHMInvoker YEAAAAAH");
		paste.execute();
    }
	private void button_copyActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println("button_cutActionPerformed called in IHMInvoker YEAAAAAH");
		copy.execute();
	}
	private void button_cutActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println("button_cutActionPerformed called in IHMInvoker YEAAAAAH");
		cut.execute();
	}

	private void menu_edit_cutActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
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
			this.textArea.setText(this.engine.getBuffer().toString());
		}
	}
}
