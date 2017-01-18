package com.ubs.meeting.gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.ubs.meeting.data.Anmeldung;
import com.ubs.meeting.data.Tutorium;
import com.ubs.meeting.db.DBService;
import com.ubs.meeting.db.DBServiceHandler;

public class LoginFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7958962931130119705L;
	
	private JLabel name, vorname, email, geburtsdatum, adresse, status, tutorium, bemerkung;
	private JTextField nameIn, vornameIn, emailIn, geburtsdatumIn;
	private JTextArea adresseIn, bemerkungIn;
	private JRadioButton mitglied, student, nonM;
	private ButtonGroup statusG;
	private JPanel statusButtons;
	private JComboBox<Tutorium> tutoriumIn;
	
	private JButton submit;
	
	private Tutorium[] tutorien = {
			new Tutorium("Verschlüsselung"),
			new Tutorium("Linguistik"),
			new Tutorium("Java vs C#")
	};
	
	public LoginFrame() {
		try {
			init();
		} catch(Exception ex) {}
	}
	
	private void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// Allgemeines
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setTitle("Anmeldung Tutorium");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Komponenten initialisieren
		
		// JLabels
		name = new JLabel("Name");
		vorname = new JLabel("Vorname");
		email = new JLabel("Email");
		geburtsdatum = new JLabel("Geburtsdatum");
		adresse = new JLabel("Adresse");
		status = new JLabel("Status");
		tutorium = new JLabel("Tutorium");
		bemerkung = new JLabel("Bemerkung");
		
		// JTextFields
		nameIn = new JTextField(20);
		vornameIn = new JTextField(20);
		emailIn = new JTextField(20);
		geburtsdatumIn = new JTextField(20);
		
		// JTextAreas
		adresseIn = new JTextArea(3, 20);
		bemerkungIn = new JTextArea(3, 20);
		
		// JRadioButtons
		statusG = new ButtonGroup();
		mitglied = new JRadioButton("Mitglied");
		student = new JRadioButton("Student");
		nonM = new JRadioButton("Nicht Mitglied");
		statusG.add(mitglied);
		statusG.add(student);
		statusG.add(nonM);
		
		statusButtons = new JPanel(new FlowLayout());
		statusButtons.add(mitglied);
		statusButtons.add(student);
		statusButtons.add(nonM);
		
		// JComboBox
		tutoriumIn = new JComboBox<Tutorium>(tutorien);
		
		// JButtons
		submit = new JButton("Bestätigen");
		submit.addActionListener(this);
		
		// Layout
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		
		int i = 0;
		
		// Name
		c.gridx = 0; c.gridy = i;
		add(name, c);
		c.gridx = 1;
		add(nameIn, c);
		i++;
		
		// Vorname
		c.gridx = 0; c.gridy = i;
		add(vorname, c);
		c.gridx = 1;
		add(vornameIn, c);
		i++;
		
		// Email
		c.gridx = 0; c.gridy = i;
		add(email, c);
		c.gridx = 1;
		add(emailIn, c);
		i++;
		
		// Geburtsdatum
		c.gridx = 0; c.gridy = i;
		add(geburtsdatum, c);
		c.gridx = 1;
		add(geburtsdatumIn, c);
		i++;
		
		// Adresse
		c.gridx = 0; c.gridy = i;
		add(adresse, c);
		c.gridx = 1;
		add(new JScrollPane(adresseIn), c);
		i++;
		
		// Status
		c.gridx = 0; c.gridy = i;
		add(status, c);
		c.gridx = 1;
		add(statusButtons, c);
		i++;
		
		// Tutorium
		c.gridx = 0; c.gridy = i;
		add(tutorium, c);
		c.gridx = 1;
		add(tutoriumIn, c);
		i++;
		
		// Bemerkung
		c.gridx = 0; c.gridy = i;
		add(bemerkung, c);
		c.gridx = 1;
		add(new JScrollPane(bemerkungIn), c);
		i++;
		
		// Bemerkung
		c.gridx = 0; c.gridy = i;
		add(submit, c);
		i++;
		
		pack();
		setSize(getWidth() + 20, getHeight() + 20);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	// Leert die Inputfelder
	private void clearInputs() {
		nameIn.setText("");
		vornameIn.setText("");
		emailIn.setText("");
		geburtsdatumIn.setText("");
		adresseIn.setText("");
		bemerkungIn.setText("");
		/*mitglied.setSelected(false);
		student.setSelected(false);
		nonM.setSelected(false);*/
		statusG.clearSelection();
		tutoriumIn.setSelectedIndex(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submit) {
			// Neues Anmeldungsobjekt initialisieren
			Anmeldung a = new Anmeldung();
			
			// Mit Werten aus dem GUI befüllen
			a.setName(nameIn.getText());
			a.setVorname(vornameIn.getText());
			a.setEmail(emailIn.getText());
			a.setGb(geburtsdatumIn.getText());
			a.setAdresse(adresseIn.getText());
			
			// JRadioButtons
			String status = "N/A";
			if(mitglied.isSelected()) {
				status = mitglied.getText();
			}
			if(nonM.isSelected()) {
				status = nonM.getText();
			}
			if(student.isSelected()) {
				status = student.getText();
			}
			a.setStatus(status);
			
			a.setTutorium(tutoriumIn.getSelectedItem().toString());
			a.setBemerkung(bemerkungIn.getText());
			
			clearInputs();
			
			// DAO holen
			DBService<Anmeldung> service = (DBService<Anmeldung>) DBServiceHandler.getDBService();
			if(service.insert(a) > 0) {
				// Erfolgsdialog
				JOptionPane.showMessageDialog(this, "Anmeldung erfolgreich", "Erfolgreich", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				// Fehlerdialog
				JOptionPane.showMessageDialog(this, "Anmeldung fehlgeschlagen", "Fehlgeschlagen", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

}
