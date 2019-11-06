package pixelsmart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;
public class CircleShape extends JPanel implements Shape{
	   int x, y;
	    int diameter;
	    public CircleShape(int x, int y, int diameter) {
	        this.x = x;
	        this.y = y;
	        this.diameter = diameter;
	    }
  
	    @Override
		public void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g;
	        
	        try {
	        	g2d.setColor(Project.getCurrent().getPrimaryBrushColor());
	        }catch(NullPointerException ex) {
	        	//System.out.println("Primary Color == NULL setting to default");
	        	g2d.setColor(Color.BLACK);
	        }
	        g2d.fill(this.getPath());
	    }
	    public GeneralPath getPath() {
	    	GeneralPath circlePath = new GeneralPath();
	    	Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);
	    	circlePath.append(circle, false);
	    	return circlePath;
	    }
	}
	
	