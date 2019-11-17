package pixelsmart.tools;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

import pixelsmart.commands.CommandList;
import pixelsmart.commands.UpdateLayerDataCommand;
import pixelsmart.image.Image;
import pixelsmart.image.Layer;

public class PencilTool extends DrawingTool {

	private Path2D.Double finalStrokeShape;

	@Override
	public void startAction(final Image image) {
		finalStrokeShape = new Path2D.Double();
		finalStrokeShape.moveTo(image.getActiveLayer().getMouseX(), image.getActiveLayer().getMouseY());
	}

	@Override
	public void updateAction(final Image image) {
		final int mx = image.getActiveLayer().getMouseX();
		final int my = image.getActiveLayer().getMouseY();

		finalStrokeShape.lineTo(mx, my);
	}

	@Override
	public void finishAction(final Image image) {
		final Layer layer = image.getActiveLayer();
		final BufferedImage newData = layer.copyData();
		final Graphics2D g = newData.createGraphics();

		g.setColor(getColor());
		final BasicStroke stroke = new BasicStroke(getBrushSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g.setStroke(stroke);

		g.draw(finalStrokeShape);

		final UpdateLayerDataCommand c = new UpdateLayerDataCommand(layer, newData);
		CommandList.getInstance().addCommand(c);

		g.dispose();
	}
}
