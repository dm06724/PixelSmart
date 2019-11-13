package pixelsmart.shapes;

import java.awt.geom.GeneralPath;

public interface Shape {
	GeneralPath getPath(int startX, int startY);
}
