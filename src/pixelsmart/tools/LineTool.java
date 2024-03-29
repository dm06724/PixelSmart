package pixelsmart.tools;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import pixelsmart.commands.CommandList;
import pixelsmart.commands.UpdateLayerDataCommand;
import pixelsmart.image.Layer;
import pixelsmart.ui.ImagePanel;

public class LineTool extends DrawingTool {

    private int startMX, startMY;

    @Override
    public void startAction(final ImagePanel panel) {
        if (panel.getActiveLayer() == null) {
            return;
        }

        startMX = panel.getMouseX(ImagePanel.RELATIVE_TO_LAYER);
        startMY = panel.getMouseY(ImagePanel.RELATIVE_TO_LAYER);
        layerAppliedTo = panel.getActiveLayer();
    }

    @Override
    public void finishAction(final ImagePanel panel) {
        Layer layer = panel.getActiveLayer();

        if (layer == null) {
            return;
        }

        int mx = panel.getMouseX(ImagePanel.RELATIVE_TO_LAYER);
        int my = panel.getMouseY(ImagePanel.RELATIVE_TO_LAYER);

        BufferedImage newData = layer.copyData();

        Graphics2D g = newData.createGraphics();

        g.setClip(panel.getClip(ImagePanel.RELATIVE_TO_LAYER));

        g.setColor(ToolManager.getInstance().getPrimaryBrushColor());
        BasicStroke stroke = new BasicStroke(getBrushSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g.setStroke(stroke);

        g.drawLine(startMX, startMY, mx, my);
        g.dispose();

        UpdateLayerDataCommand command = new UpdateLayerDataCommand(layer, newData);
        CommandList.getInstance().addCommand(command);
        layerAppliedTo = null;
    }

    public void drawTemporaryImage(Graphics2D g) {
		// TODO Auto-generated method stub
    	
		int mx = ImagePanel.get().getMouseX(ImagePanel.RELATIVE_TO_LAYER);
        int my = ImagePanel.get().getMouseY(ImagePanel.RELATIVE_TO_LAYER);

        g.setClip(ImagePanel.get().getClip(ImagePanel.RELATIVE_TO_LAYER));

        g.setColor(ToolManager.getInstance().getPrimaryBrushColor());
        BasicStroke stroke = new BasicStroke(getBrushSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g.setStroke(stroke);

        g.drawLine(startMX, startMY, mx, my);
	}

}