package com.ubs.meeting.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Confirmation extends JDialog implements ActionListener {
	
	private String message;
	private JButton confirm;
	
	public Confirmation(String text, boolean modal) {
		setTitle("Bestätigung");
		setModal(modal);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		message = text;
		init();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		
		// Label
		add(new JLabel(message), BorderLayout.NORTH);
		
		// OK Button
		confirm = new JButton("Ok");
		confirm.addActionListener(this);
		
		add(confirm, BorderLayout.SOUTH);
		
		// Anzeigen
		pack();
		setSize(getWidth() + 10, getHeight() + 10);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == confirm) {
			
			// Ok Button geklickt -> Dialog schliessen
			dispose();
		}
	}

}
