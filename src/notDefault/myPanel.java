package notDefault;

import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

public class myPanel extends JPanel {
	
	private BufferedImage img;
	
	public myPanel()
	{
		super();
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		if(img!=null)
		{
			g.drawImage(img, 0, 0, null);
		}
		g.fill3DRect(0, 0, 64, 64, true);
	}
	
}
