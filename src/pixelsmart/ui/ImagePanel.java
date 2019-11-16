package pixelsmart.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pixelsmart.Input;
import pixelsmart.Project;
import pixelsmart.image.Image;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = -5952682079799751735L;
	private static BufferedImage transBackground;
	private Image image;
	private static TexturePaint backPaint;
	
	public ImagePanel() {
		super(new BorderLayout(0, 0));
		
		try {
			transBackground = ImageIO.read(new File("TransparentBackground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		backPaint = new TexturePaint(transBackground, new Rectangle2D.Double(0, 0, 2, 2));
		
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		
		g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
		if (image == null) {
			return;
		}
		
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		int x = mouseOffsetX();
		int y = mouseOffsetY();
		
		backPaint = new TexturePaint(transBackground, new Rectangle2D.Double(x, y, 50, 50));
		
		g2d.setPaint(backPaint);
		
		g2d.fill(new Rectangle2D.Double(x, y, width, height));
		
		g2d.setPaint(null);
		g2d.drawImage(Project.getCurrent().getImage().getAggregatedData(), x, y, null);
	}
	
	private int mouseOffsetX() {
		return (this.getWidth() - image.getWidth()) / 2;
	}
	
	private int mouseOffsetY() {
		return (this.getHeight() - image.getHeight()) / 2;
	}
	
	public int getMouseX() {
		return Input.getMouseX() - mouseOffsetX();
	}
	
	public int getMouseY() {
		return Input.getMouseY() - mouseOffsetY();
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
}
