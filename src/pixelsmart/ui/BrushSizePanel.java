package pixelsmart.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import pixelsmart.tools.ToolManager;

public class BrushSizePanel extends JPanel {
	private static final long serialVersionUID = 2243280863502885654L;
	static JTextField text;
	static JSlider slider;
	static JLabel label;

	public BrushSizePanel() {

		slider = new JSlider(JSlider.HORIZONTAL, 1, 50, 10); // orientation, min, max, where it starts
		slider.setMajorTickSpacing(2); // increments of 5 from 0 to 20
		slider.setPaintTicks(true); // shows them on screen
		slider.addChangeListener(e -> {
			text.setText(String.valueOf(slider.getValue()));
			label.setText("Current brush value: " + slider.getValue());
			ToolManager.getInstance().setBrushSize(slider.getValue());
		});
		text = new JTextField(2); // length of textbox
		text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				String typed = text.getText();
				slider.setValue(0);
				if (!typed.matches("\\d+") || typed.length() > 3) {
					return;
				}
				int value = Integer.parseInt(typed);
				slider.setValue(value);
			}
		});

		label = new JLabel("Current brush value: 10");

		this.add(text);
		this.add(slider);
		this.add(label);
	}
}
