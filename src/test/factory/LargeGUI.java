package test.factory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
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

@SuppressWarnings("serial")
public class LargeGUI extends JFrame implements ActionListener {
	
	private AutoForm auto;
	
	public static void main(String[] args) {
		new LargeGUI();
	}
	
	public LargeGUI() {
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
		setTitle("Grosses GUI");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Formular initialisieren
		Validator val = new NameValidator();
		List<FormOptions> fields = new ArrayList<FormOptions>();
		
		// Settings definieren
		TextSettings text = new TextSettings();
		text.setMaxLength(20);
		
		ComboSettings combo = new ComboSettings();
		
		RadioSettings radio = new RadioSettings();
		
		// Felder definieren
		
		for(int i = 1; i <= 20; i++) {
			fields.add(new FormOptions("text" + i, "Text " + i, InputType.TEXTFIELD, true, text, val));
		}
		
		for(int i = 1; i <= 5; i++) {
			radio.setObjects(getStrings());
			fields.add(new FormOptions("radio" + i, "Radio " + i, InputType.RADIO, true, radio, null));
		}
		
		for(int i = 1; i <= 2; i++) {
			combo.setObjects(getStrings());
			fields.add(new FormOptions("comboRequired" + i, "Combo " + i, InputType.COMBOBOX, true, combo, null));
		}
		
		for(int i = 1; i <= 5; i++) {
			combo.setObjects(getStrings());
			fields.add(new FormOptions("combo" + i, "Combo " + i, InputType.COMBOBOX, false, combo, null));
		}
		
		auto = FormFactory.produceForm(fields, "Grosses GUI", this);
		
		JScrollPane pane = new JScrollPane(auto);
		
		add(pane);
		
		pack();
		setSize(getWidth() + 10, getHeight() + 10);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private Object[] getStrings() {
		Random r = new Random();
		Object[] objs = new Object[]{"" + r.nextInt(10000), "" + r.nextInt(10000), "" + r.nextInt(10000), "" + r.nextInt(10000)};
		
		return objs;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Map<String, Object> values = auto.getValues();
		for(String k : values.keySet()) {
			Object val = values.get(k);
			System.out.println(k + " : " + val);
		}
		auto.clear();
	}

}
