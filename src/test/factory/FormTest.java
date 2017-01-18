package test.factory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import com.ubs.factory.components.Validator;
import com.ubs.factory.components.combobox.ComboSettings;
import com.ubs.factory.components.radio.RadioSettings;
import com.ubs.factory.components.textfield.TextSettings;
import com.ubs.factory.controller.FormFactory;
import com.ubs.factory.controller.FormOptions;
import com.ubs.factory.controller.InputType;
import com.ubs.factory.gui.AutoForm;

@SuppressWarnings("serial")
public class FormTest extends JFrame implements ActionListener {
	
	private AutoForm form;
	
	private Validator nameValidator = new Validator() {
		
		@Override
		public boolean validate(Object o) {
			String s = (String)o;
			if(s.length() >= 4) {
				return true;
			}
			else {
				return false;
			}
		}
	};
	
	public static void main(String[] args) {
		new FormTest();
	}
	
	public FormTest() {
		init();
	}
	
	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		List<FormOptions> options = new ArrayList<FormOptions>();
		
		TextSettings nameSettings = new TextSettings();
		nameSettings.setMaxLength(20);
		FormOptions name = new FormOptions("name", "Name", InputType.TEXTFIELD, true, nameSettings, nameValidator);
		options.add(name);
		
		FormOptions vorname = new FormOptions("vorname", "Vorname", InputType.TEXTFIELD, false, nameSettings, nameValidator);
		options.add(vorname);
		
		RadioSettings radioS = new RadioSettings();
		radioS.setObjects(new Object[]{"uno", "duo", "tres"});
		FormOptions status = new FormOptions("status", "Status", InputType.RADIO, true, radioS, null);
		options.add(status);
		
		ComboSettings tutoriumSettings = new ComboSettings();
		tutoriumSettings.setObjects(new Object[]{"Eins", "Zwei", "Drei"});
		FormOptions tutorium = new FormOptions("tutorium", "Tutorium", InputType.COMBOBOX, false, tutoriumSettings, null);
		options.add(tutorium);
		
		form = FormFactory.produceForm(options, "Formular 101", this);
		add(form);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Valid: " + form.isValid());
		Map<String, Object> values = form.getValues();
		for(String key : values.keySet()) {
			Object val = values.get(key);
			System.out.println("Key: " + key + " | Value: " + val);
		}
	}

}
