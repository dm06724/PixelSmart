package notDefault;

import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

public class myPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BufferedImage img;
	
	public myPanel()
	{
		super();
		img = new BufferedImage(450, 300, BufferedImage.TYPE_INT_ARGB);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		if(img!=null)
		{
			g.drawImage(img, 0, 0, null);
		}
	}
	
	public BufferedImage getImage()
	{
		return img;
	}
	
	public void setImage(BufferedImage img)
	{
		this.img = img;
	}
	
	public BufferedImage copyImage()
	{
		ColorModel cm = img.getColorModel();
		WritableRaster ra = img.copyData(null);
		
		return new BufferedImage(cm, ra, img.isAlphaPremultiplied(), null);
	}
}
