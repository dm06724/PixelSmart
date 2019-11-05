package pixelsmart;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = -5952682079799751735L;

	public ImagePanel() {
		super(new BorderLayout(0, 0));

		this.addMouseListener(Input.getInstance());
		this.addMouseMotionListener(Input.getInstance());
	}

	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		if (Image.getCurrent() == null) {
			return;
		}
		g.drawImage(Image.getCurrent().getAggregatedData(), 0, 0, null);
	}
}
