package pixelsmart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;
public class StarShape extends JPanel implements Shape{

    int x, y;
    int diameter;
    int count;

    public StarShape(int x, int y) {
        this.x = x;
        this.y = y;
    } 
	@Override
	public GeneralPath getPath() {
        double xPoints[] = {9.0, 15.0, 0.0, 18.0, 3.0};
        double yPoints[] = {0.0, 18.0, 6.0, 6.0, 18.0};
    	GeneralPath star = new GeneralPath();
    	  star.moveTo(xPoints[0] + x, yPoints[0] + y);
          for (int i = 1; i < xPoints.length; i++) {
              star.lineTo(xPoints[i] + x, yPoints[i] + y);
          }
         star.closePath();
         star.transform(AffineTransform.getTranslateInstance(-13, -13));	
         return star;
	}

	@Override
	public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        try {
        	g2d.setColor(Project.getCurrent().getPrimaryBrushColor());
        }catch(NullPointerException ex) {
         	//System.out.println("Primary Color == NULL setting to default");
        	g2d.setColor(Color.BLACK);
        }
        g2d.fill(this.getPath());
		
	}
     
}
