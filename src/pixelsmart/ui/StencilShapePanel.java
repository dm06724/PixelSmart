package pixelsmart.ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pixelsmart.tools.StencilTool;
import pixelsmart.tools.ToolManager;

public class StencilShapePanel extends JPanel {
	private static final long serialVersionUID = 1748750030463477712L;
	JButton circle;
	JButton splash;
	JButton star;
	JLabel label;
	
	public StencilShapePanel() {
		
		circle = new JButton("Circle");
		
		circle.addActionListener(e -> {
			ToolManager.getInstance().setTool(new StencilTool("circle1"));
			System.out.println("circle");
		});
		
		splash = new JButton("Splash");
		
		splash.addActionListener(e -> {
			ToolManager.getInstance().setTool(new StencilTool("splash1"));
			System.out.println("splash");
		});
		
		star = new JButton("Star");

		star.addActionListener(e -> {
			ToolManager.getInstance().setTool(new StencilTool("star"));
			System.out.println("star");
		});
		
		label = new JLabel("Stencil Shape:");
		label.setAlignmentY(label.CENTER_ALIGNMENT);
		
		this.add(label);
		
		this.add(circle);
		this.add(splash);
		this.add(star);
	}
}
