package test.factory;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.ubs.factory.gui.FormFactory;
import com.ubs.factory.gui.InputType;

@SuppressWarnings("serial")
public class FormTest extends JFrame {
	
	public static void main(String[] args) {
		new FormTest();
	}
	
	public FormTest() {
		init();
	}
	
	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Map<String, InputType> map = new LinkedHashMap<String, InputType>();
		map.put("Name", InputType.TEXTFIELD);
		map.put("Vorname", InputType.TEXTFIELD);
		map.put("Bemerkung", InputType.TEXTAREA);
		add(new FormFactory().produceForm(map));
		pack();
		setVisible(true);
	}

}
