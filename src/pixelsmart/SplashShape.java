package pixelsmart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

public class SplashShape extends JPanel implements Shape  {
    int x, y;
    int particleDiameter;
    int max,min;
    int count;
    public SplashShape(int count, int particleDiameter,int x,int y ) {
    	this.count = count;
        this.particleDiameter = particleDiameter;
        max = 15;
        min = 1;
        this.x = x;			
        this.y = y;
    }
	@Override
	public void paintComponent(Graphics g) {
       // super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        try {
        	g2d.setColor(Project.getCurrent().getPrimaryBrushColor());
        }catch(NullPointerException ex) {
        	g2d.setColor(Color.BLACK);
        }
        GeneralPath x = this.getPath();

        g2d.fill(x);
	}
	@Override
	public GeneralPath getPath() {
    	GeneralPath splash = new GeneralPath();
    	for(int z =0;z<count;z++) {
            // creating random pos. for spread
        	double rand1 = (Math.random() * ((max - min) + 1)) + min;
        	double rand2 = (Math.random() * ((max - min) + 1)) + min;
        	Ellipse2D test = new Ellipse2D.Double(rand1+x,rand2+y,particleDiameter , particleDiameter);
        	
        	splash.append(test, false);
        }
    	
    	splash.closePath();
    	splash.transform(AffineTransform.getTranslateInstance(-13, -13));	
   	return splash;
	}

}
