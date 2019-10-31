package pixelsmart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

public class Shapes {}

class Circle extends JPanel{
    int x, y;
    int diameter;
    public Circle(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        try {
        	g2d.setColor(Project.getCurrent().getPrimaryBrushColor());
        }catch(NullPointerException ex) {
        	System.out.println("Primary Color == NULL setting to default");
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

class Splash extends JPanel{
    int x, y;
    int diameter;
    int max,min;
    int count;
    public Splash(int count, int diameter,int x,int y) {
    	this.count = count;
        this.diameter = diameter;
        max = 15;
        min = 1;
        this.x = x;			
        this.y = y;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        try {
        	g2d.setColor(Project.getCurrent().getPrimaryBrushColor());
        }catch(NullPointerException ex) {
         	System.out.println("Primary Color == NULL setting to default");
        	g2d.setColor(Color.BLACK);
        }
        g2d.fill(this.getPath());
    }
    public GeneralPath getPath() {
    	GeneralPath splash = new GeneralPath();
    	for(int z =0;z<count;z++) {
            // creating random pos. for spread
        	double rand1 = (Math.random() * ((max - min) + 1)) + min;
        	double rand2 = (Math.random() * ((max - min) + 1)) + min;
        	Ellipse2D test = new Ellipse2D.Double(rand1+x,rand2+y,diameter , diameter);
        	splash.append(test, false);
        }
    	return splash;
    }
}

class Star extends JPanel{
    int x, y;
    int diameter;
    int count;

    public Star(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        try {
        	g2d.setColor(Project.getCurrent().getPrimaryBrushColor());
        }catch(NullPointerException ex) {
         	System.out.println("Primary Color == NULL setting to default");
        	g2d.setColor(Color.BLACK);
        }
        g2d.fill(this.getPath());
    }
    
    public GeneralPath getPath() {
        double xPoints[] = {9.0, 15.0, 0.0, 18.0, 3.0};
        double yPoints[] = {0.0, 18.0, 6.0, 6.0, 18.0};
    	GeneralPath star = new GeneralPath();
    	  star.moveTo(xPoints[0] + x, yPoints[0] + y);
          for (int i = 1; i < xPoints.length; i++) {
              star.lineTo(xPoints[i] + x, yPoints[i] + y);
          }
         star.closePath();
         return star;
    }
     
}
