package pixelsmart.tools;

import java.awt.Color;

public class EraserTool extends PencilTool {
	private static final Color clear = new Color(0, 0, 0, 0);

	@Override
	protected Color getColor() {
		return clear;
	}
}