package pixelsmart.tools;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

import pixelsmart.Project;
import pixelsmart.commands.CommandList;
import pixelsmart.commands.UpdateLayerDataCommand;
import pixelsmart.image.Image;
import pixelsmart.image.Layer;
import pixelsmart.shapes.Shape;

public class StencilTool extends DrawingTool {

	private Path2D.Double finalStrokeShape;
	private Shape shapeBehavior;

	@Override
	public void startAction(final Image image) {
		finalStrokeShape = new Path2D.Double();
	}

	@Override
	public void updateAction(final Image image) {
		final int mx = image.getActiveLayer().getMouseX();
		final int my = image.getActiveLayer().getMouseY();

		finalStrokeShape.append(shapeBehavior.getPath(mx, my), false);
	}

	@Override
	public void finishAction(final Image image) {
		final Layer layer = image.getActiveLayer();
		final BufferedImage newData = layer.copyData();
		final Graphics2D g = newData.createGraphics();

		g.setColor(Project.getCurrent().getPrimaryBrushColor());
		final BasicStroke stroke = new BasicStroke(Project.getCurrent().getBrushSize());
		g.setStroke(stroke);

		g.draw(finalStrokeShape);
		g.fill(finalStrokeShape);

		final UpdateLayerDataCommand c = new UpdateLayerDataCommand(layer, newData);
		CommandList.getInstance().addCommand(c);

		g.dispose();
	}
}
