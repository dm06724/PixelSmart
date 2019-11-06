package pixelsmart;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

public class EraserTool implements Tool {

	private int lastMouseX = -1, lastMouseY = -1;

	private Path2D.Double finalStrokeShape;

	@Override
	public void startAction() {
	
		finalStrokeShape = new Path2D.Double();
		finalStrokeShape.moveTo(Input.getMouseX(), Input.getMouseY());
	}

	@Override
	public void updateAction() {
		int mx = Input.getMouseX();
		int my = Input.getMouseY();

		finalStrokeShape.lineTo(mx, my); // TODO add a method of visualizing so the user can see what they are drawing

		if (lastMouseX < 0 || lastMouseY < 0) {
			lastMouseX = mx;
			lastMouseY = my;
		}

		lastMouseX = mx;
		lastMouseY = my;
	}

	@Override
	public void finishAction() {
		Layer layer = Image.getCurrent().getActiveLayer();
		BufferedImage newData = layer.copyData();
		Graphics2D g = newData.createGraphics();

		g.setColor(Project.getCurrent().getSecondaryBrushColor());
		BasicStroke stroke = new BasicStroke(20);
		g.setStroke(stroke);

		g.draw(finalStrokeShape);

		UpdateLayerDataCommand c = new UpdateLayerDataCommand(layer, newData);
		CommandList.getInstance().addCommand(c);

		g.dispose();
	}
}