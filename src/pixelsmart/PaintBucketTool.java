package pixelsmart;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.Point;

public class PaintBucketTool implements Tool {

	BufferedImage newData;
	Layer layer;
	int tc, rc;
	
    @Override
    public void startAction() {
        // not needed
    }

    @Override
    public void updateAction() {
        // not needed
    }

    @Override
    public void finishAction() {
    	int mx = Input.getMouseX();
    	int my = Input.getMouseY();
    	layer = Project.getCurrent().getImage().getActiveLayer();
    	newData = layer.copyData();
		tc = newData.getRGB(mx, my);
    	rc = Project.getCurrent().getPrimaryBrushColor().getRGB();
		if(tc == rc) return;
		floodFill(mx, my);
		UpdateLayerDataCommand dc = new UpdateLayerDataCommand(layer, newData);
		CommandList.getInstance().addCommand(dc);
    }
    
    public void floodFill(int x, int y) {
    	Queue<Point> queue = new LinkedList<Point>();    	
    	queue.add(new Point(x, y));
    	
    	while(!queue.isEmpty()) {
    		Point p = queue.remove();
    		if(p.x < 0) continue;
    		if(p.y < 0) continue;
    		if(p.x >= newData.getWidth() || p.y >= newData.getHeight()) continue;
    		if(newData.getRGB(p.x, p.y) != tc) continue;
    		newData.setRGB(p.x, p.y, rc);
    		//System.out.println("X: " + p.x + " Y: " + p.y + " RGB: " + rc);
    		queue.add(new Point(p.x + 1, p.y));
    		queue.add(new Point(p.x - 1, p.y));
    		queue.add(new Point(p.x, p.y + 1));
    		queue.add(new Point(p.x, p.y - 1));
    	}
    }
}