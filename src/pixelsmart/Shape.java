package pixelsmart;

import java.awt.Graphics;
import java.awt.geom.GeneralPath;

public interface Shape {
	 
	   GeneralPath getPath();
	   void paintComponent(Graphics g);
	   
	}
