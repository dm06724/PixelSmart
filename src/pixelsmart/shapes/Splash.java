package pixelsmart.shapes;

import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class Splash implements Shape {
    int particleDiameter;
    final int min = 1, max = 15;
    int count;

    public Splash(int count, int particleDiameter) {
        this.count = count;
        this.particleDiameter = particleDiameter;
    }

    @Override
    public GeneralPath getPath(int startX, int startY) {
        GeneralPath splash = new GeneralPath();
        for (int z = 0; z < count; z++) {
            // creating random pos. for spread
            double rand1 = (Math.random() * ((max - min) + 1)) + min;
            double rand2 = (Math.random() * ((max - min) + 1)) + min;
            Ellipse2D test = new Ellipse2D.Double(rand1 + startX, rand2 + startY, particleDiameter, particleDiameter);

            splash.append(test, false);
        }

        splash.closePath();
        splash.transform(AffineTransform.getTranslateInstance(-13, -13));
        return splash;
    }

}
