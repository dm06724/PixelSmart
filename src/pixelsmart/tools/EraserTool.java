package pixelsmart.tools;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

import pixelsmart.commands.CommandList;
import pixelsmart.commands.UpdateLayerDataCommand;
import pixelsmart.image.Layer;
import pixelsmart.ui.ImagePanel;

public class EraserTool extends DrawingTool {
	private Path2D.Double finalStrokeShape;

	@Override
	public void startAction(ImagePanel panel) {
		if (panel.getActiveLayer() == null) {
			return;
		}

		finalStrokeShape = new Path2D.Double();
		int mx = panel.getMouseX(ImagePanel.RELATIVE_TO_LAYER);
		int my = panel.getMouseY(ImagePanel.RELATIVE_TO_LAYER);
		finalStrokeShape.moveTo(mx, my);
	}

	@Override
	public void updateAction(ImagePanel panel) {
		if (panel.getActiveLayer() == null) {
			return;
		}

		int mx = panel.getMouseX(ImagePanel.RELATIVE_TO_LAYER);
		int my = panel.getMouseY(ImagePanel.RELATIVE_TO_LAYER);

		finalStrokeShape.lineTo(mx, my);
	}

	@Override
	public void finishAction(ImagePanel panel) {
		if (panel.getActiveLayer() == null) {
			return;
		}

		Layer layer = panel.getActiveLayer();
		BufferedImage newData = layer.copyData();
		Graphics2D g = newData.createGraphics();

		g.setClip(panel.getClip(ImagePanel.RELATIVE_TO_LAYER));

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		BasicStroke stroke = new BasicStroke(getBrushSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g.setStroke(stroke);

		g.draw(finalStrokeShape);

		UpdateLayerDataCommand c = new UpdateLayerDataCommand(layer, newData);
		CommandList.getInstance().addCommand(c);

		g.dispose();
	}
}