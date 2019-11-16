package pixelsmart.ui;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SliderWithText extends JFrame
{
	static JTextField text;
	static JSlider slider;
	static JLabel label;
	
	public SliderWithText() {
		text = new JTextField(2); // length of textbox
		add(text);
		slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1); // orientation, min, max, where it starts
		slider.setMajorTickSpacing(2); // increments of 5 from 0 to 20
		slider.setPaintTicks(true); // shows them on screen
		add(slider);
		label = new JLabel("Current brush value: 1");
		add(label);
		
		slider.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent ce)
			{
				text.setText(String.valueOf(slider.getValue()));
				label.setText("Current brush value: " + slider.getValue());
			}
		});

		text.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent ke)
			{
				String typed = text.getText();
				slider.setValue(0);
				if(!typed.matches("\\d+") || typed.length() > 3)
				{
					return;
				}
				int value = Integer.parseInt(typed);
				slider.setValue(value);
			}
		});
	}
/*
    public static void main(String[] args)
	{
		
		SliderWText gui = new SliderWText();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.add(text, BorderLayout.NORTH);
		gui.add(slider, BorderLayout.SOUTH);
		gui.add(label, BorderLayout.CENTER);
		gui.setSize(300, 100);
		gui.setVisible(true);
		gui.setTitle("Brush");

    }
    */
}
