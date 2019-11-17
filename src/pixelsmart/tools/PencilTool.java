package pixelsmart.tools;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

import pixelsmart.commands.CommandList;
import pixelsmart.commands.UpdateLayerDataCommand;
import pixelsmart.image.Layer;
import pixelsmart.ui.ImagePanel;

public class PencilTool extends DrawingTool {

	private Path2D.Double finalStrokeShape;

	@Override
	public void startAction(final ImagePanel panel) {
		finalStrokeShape = new Path2D.Double();
		finalStrokeShape.moveTo(panel.getMouseX(ImagePanel.RELATIVE_TO_LAYER),
				panel.getMouseY(ImagePanel.RELATIVE_TO_LAYER));
	}

	@Override
	public void updateAction(final ImagePanel panel) {
		final int mx = panel.getMouseX(ImagePanel.RELATIVE_TO_LAYER);
		final int my = panel.getMouseY(ImagePanel.RELATIVE_TO_LAYER);

		finalStrokeShape.lineTo(mx, my);
	}

	@Override
	public void finishAction(final ImagePanel panel) {
		final Layer layer = panel.getActiveLayer();
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
