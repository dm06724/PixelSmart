package pixelsmart.shapes;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class Star implements Shape {
    int diameter;
    int count;

    public GeneralPath getPath(int startX, int startY) {
        double xPoints[] = { 9.0, 15.0, 0.0, 18.0, 3.0 };
        double yPoints[] = { 0.0, 18.0, 6.0, 6.0, 18.0 };
        GeneralPath star = new GeneralPath();
        star.moveTo(xPoints[0] + startX, yPoints[0] + startY);
        for (int i = 1; i < xPoints.length; i++) {
            star.lineTo(xPoints[i] + startX, yPoints[i] + startY);
        }
        star.closePath();
        star.transform(AffineTransform.getTranslateInstance(-13, -13));
        return star;
    }
}
