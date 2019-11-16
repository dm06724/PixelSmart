package pixelsmart.shapes;

import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class Circle implements Shape {
	int diameter;

	public Circle(int diameter) {
		this.diameter = diameter;
	}

	public GeneralPath getPath(int startX, int startY) {
		GeneralPath circlePath = new GeneralPath();
		Ellipse2D.Double circle = new Ellipse2D.Double(startX, startY, diameter, diameter);
		circlePath.append(circle, false);
		return circlePath;
	}
}
