package pixelsmart.ui;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import pixelsmart.tools.StencilTool;
import pixelsmart.tools.ToolManager;

public class StencilShapePanel extends JPanel {
	private static final long serialVersionUID = 1748750030463477712L;
	JButton circle;
	JButton splash;
	JButton star;

	public StencilShapePanel() {

		ImageIcon circleImage = new ImageIcon("res/images/circle.png");
		ImageIcon splashImage = new ImageIcon("res/images/splash.png");
		ImageIcon starImage = new ImageIcon("res/images/star.png");

		circle = new JButton(circleImage);
		splash = new JButton(splashImage);
		star = new JButton(starImage);

		circle.addActionListener(e -> {
			ToolManager.getInstance().setTool(new StencilTool("circle1"));
		});

		splash.addActionListener(e -> {
			ToolManager.getInstance().setTool(new StencilTool("splash1"));
		});

		star.addActionListener(e -> {
			ToolManager.getInstance().setTool(new StencilTool("star"));
		});

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(circle);
		this.add(splash);
		this.add(star);
	}
}
