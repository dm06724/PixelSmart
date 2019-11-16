package pixelsmart.tools;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

import pixelsmart.commands.CommandList;
import pixelsmart.commands.UpdateLayerDataCommand;
import pixelsmart.image.Image;
import pixelsmart.image.Layer;

public class EraserTool extends DrawingTool {
	private Path2D.Double finalStrokeShape;

	@Override
	public void startAction(Image image) {
		finalStrokeShape = new Path2D.Double();
		finalStrokeShape.moveTo(image.getActiveLayer().getMouseX(), image.getActiveLayer().getMouseY());
	}

	@Override
	public void updateAction(Image image) {
		int mx = image.getActiveLayer().getMouseX();
		int my = image.getActiveLayer().getMouseY();

		finalStrokeShape.lineTo(mx, my);
	}

	@Override
	public void finishAction(Image image) {
		Layer layer = image.getActiveLayer();
		BufferedImage newData = layer.copyData();
		Graphics2D g = newData.createGraphics();

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		BasicStroke stroke = new BasicStroke(getBrushSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g.setStroke(stroke);

		g.draw(finalStrokeShape);

		UpdateLayerDataCommand c = new UpdateLayerDataCommand(layer, newData);
		CommandList.getInstance().addCommand(c);

		g.dispose();
	}
}