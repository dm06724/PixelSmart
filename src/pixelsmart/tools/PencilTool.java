package pixelsmart.tools;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

import pixelsmart.Input;
import pixelsmart.commands.CommandList;
import pixelsmart.commands.UpdateLayerDataCommand;
import pixelsmart.image.Image;
import pixelsmart.image.Layer;

public class PencilTool extends DrawingTool {

	private int lastMouseX = -1, lastMouseY = -1;

	private Path2D.Double finalStrokeShape;

	@Override
	public void startAction(Image image) {

		finalStrokeShape = new Path2D.Double();
		finalStrokeShape.moveTo(Input.getMouseX(), Input.getMouseY());
	}

	@Override
	public void updateAction(Image image) {
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
	public void finishAction(Image image) {
		Layer layer = image.getActiveLayer();
		BufferedImage newData = layer.copyData();
		Graphics2D g = newData.createGraphics();

		g.setColor(getColor());
		BasicStroke stroke = new BasicStroke(getBrushSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g.setStroke(stroke);

		g.draw(finalStrokeShape);

		UpdateLayerDataCommand c = new UpdateLayerDataCommand(layer, newData);
		CommandList.getInstance().addCommand(c);

		g.dispose();
	}
}
