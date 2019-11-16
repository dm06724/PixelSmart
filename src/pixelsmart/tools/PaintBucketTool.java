package pixelsmart.tools;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import pixelsmart.Input;
import pixelsmart.Project;
import pixelsmart.commands.CommandList;
import pixelsmart.commands.UpdateLayerDataCommand;
import pixelsmart.image.Image;
import pixelsmart.image.Layer;

public class PaintBucketTool extends ToolAdapter {

	BufferedImage newData;
	Layer layer;
	int tc, rc;

	@Override
	public void finishAction(Image image) {
		int mx = Input.getMouseX();
		int my = Input.getMouseY();
		layer = image.getActiveLayer();
		newData = layer.copyData();
		tc = newData.getRGB(mx, my);
		rc = Project.getCurrent().getPrimaryBrushColor().getRGB();
		if (tc == rc)
			return;
		floodFill(mx, my);
		UpdateLayerDataCommand dc = new UpdateLayerDataCommand(layer, newData);
		CommandList.getInstance().addCommand(dc);
	}

	public void floodFill(int x, int y) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(x, y));

		while (!queue.isEmpty()) {
			Point p = queue.remove();
			if (p.x < 0)
				continue;
			if (p.y < 0)
				continue;
			if (p.x >= newData.getWidth() || p.y >= newData.getHeight())
				continue;
			if (newData.getRGB(p.x, p.y) != tc)
				continue;
			newData.setRGB(p.x, p.y, rc);
			// System.out.println("X: " + p.x + " Y: " + p.y + " RGB: " + rc);
			queue.add(new Point(p.x + 1, p.y));
			queue.add(new Point(p.x - 1, p.y));
			queue.add(new Point(p.x, p.y + 1));
			queue.add(new Point(p.x, p.y - 1));
		}
	}
}