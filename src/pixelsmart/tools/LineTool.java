package pixelsmart.tools;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pixelsmart.Project;
import pixelsmart.commands.CommandList;
import pixelsmart.commands.UpdateLayerDataCommand;
import pixelsmart.image.Image;
import pixelsmart.image.Layer;

public class LineTool extends DrawingTool {

    private int startMX, startMY;

    @Override
    public void startAction(Image image) {
        Layer layer = image.getActiveLayer();
        startMX = layer.getMouseX();
        startMY = layer.getMouseY();
    }

    @Override
    public void finishAction(Image image) {
        Layer layer = image.getActiveLayer();

        int mx = layer.getMouseX();
        int my = layer.getMouseY();

        BufferedImage newData = layer.copyData();

        Graphics2D g = newData.createGraphics();

        g.setColor(Project.getCurrent().getPrimaryBrushColor());
        BasicStroke stroke = new BasicStroke(getBrushSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g.setStroke(stroke);

        g.drawLine(startMX, startMY, mx, my);
        g.dispose();

        UpdateLayerDataCommand command = new UpdateLayerDataCommand(layer, newData);
        CommandList.getInstance().addCommand(command);
    }

}