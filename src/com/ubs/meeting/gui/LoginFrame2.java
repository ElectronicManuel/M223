package com.ubs.meeting.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.ubs.factory.components.Validator;
import com.ubs.factory.components.combobox.ComboSettings;
import com.ubs.factory.components.radio.RadioSettings;
import com.ubs.factory.components.textfield.TextSettings;
import com.ubs.factory.controller.FormFactory;
import com.ubs.factory.controller.FormOptions;
import com.ubs.factory.controller.InputType;
import com.ubs.factory.gui.AutoForm;
import com.ubs.meeting.data.Anmeldung;
import com.ubs.meeting.db.DBService;
import com.ubs.meeting.db.DBServiceHandler;

import test.factory.NameValidator;

@SuppressWarnings("serial")
public class LoginFrame2 extends JFrame implements ActionListener {

	private AutoForm auto;
	
	public LoginFrame2() {
		init();
	}
	
	private void init() {
		// Allgemeines
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setTitle("Anmeldung Tutorium");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Formular initialisieren
		Validator val = new NameValidator();
		List<FormOptions> fields = new ArrayList<FormOptions>();
		
		// Settings definieren
		TextSettings text = new TextSettings();
		text.setMaxLength(20);
		
		ComboSettings combo = new ComboSettings();
		combo.setObjects(new String[]{"Java", "SQL", "C#"});
		
		RadioSettings radio = new RadioSettings();
		radio.setObjects(new String[]{"Student", "Mitglied", "Nicht Mitglied"});
		
		// Felder definieren
		
		// Name
		fields.add(new FormOptions("name", "Name", InputType.TEXTFIELD, true, text, val));
		
		// Vorname
		fields.add(new FormOptions("vorname", "Vorname", InputType.TEXTFIELD, true, text, val));
		
		// Email
		fields.add(new FormOptions("email", "Email", InputType.TEXTFIELD, false, text, null));
		
		// Geburtsdatum
		fields.add(new FormOptions("gb", "Geburtsdatum", InputType.TEXTFIELD, false, text, null));
		
		// Adresse
		fields.add(new FormOptions("adresse", "Adresse", InputType.TEXTFIELD, false, text, null));
		
		// Status
		fields.add(new FormOptions("status", "Status", InputType.RADIO, true, radio, null));
		
		// Tutorium
		fields.add(new FormOptions("tutorium", "Tutorium", InputType.COMBOBOX, true, combo, null));
		
		// Bemerkung
		fields.add(new FormOptions("bemerkung", "Bemerkung", InputType.TEXTFIELD, false, text, null));
		
		auto = FormFactory.produceForm(fields, "Anmeldung Tutorium", this);
		
		add(auto);
		
		pack();
		setSize(getWidth() + 10, getHeight() + 10);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		// Neues Anmeldungsobjekt initialisieren
		Anmeldung a = new Anmeldung();
		
		Map<String, Object> values = auto.getValues();

		// Mit Werten aus dem GUI befüllen
		a.setName((String) values.get("name"));
		a.setVorname((String) values.get("vorname"));
		a.setEmail((String) values.get("email"));
		a.setGb((String) values.get("gb"));
		a.setAdresse((String) values.get("adresse"));
		a.setStatus((String) values.get("status"));
		a.setTutorium((String) values.get("tutorium"));
		a.setBemerkung((String) values.get("bemerkung"));

		// GUI Leeren
		auto.clear();

		// DAO holen
		DBService<Anmeldung> service = (DBService<Anmeldung>) DBServiceHandler.getDBService();
		if (service.insert(a) > 0) {
			// Erfolgsdialog
			JOptionPane.showMessageDialog(this, "Anmeldung erfolgreich", "Erfolgreich",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			// Fehlerdialog
			JOptionPane.showMessageDialog(this, "Anmeldung fehlgeschlagen", "Fehlgeschlagen",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
