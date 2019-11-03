package pixelsmart;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class LineTool implements Tool {

    private int startMX, startMY;

    @Override
    public void startAction() {
        Layer layer = Image.getCurrent().getActiveLayer();
        startMX = layer.getMouseX();
        startMY = layer.getMouseY();
    }

    @Override
    public void updateAction() {

    }

    @Override
    public void finishAction() {
        Image img = Image.getCurrent();
        Layer layer = img.getActiveLayer();

        int mx = layer.getMouseX();
        int my = layer.getMouseY();

        BufferedImage newData = layer.copyData();

        Graphics2D g = layer.getData().createGraphics();
        g.drawLine(startMX, startMY, mx, my);
        g.dispose();

        UpdateLayerDataCommand command = new UpdateLayerDataCommand(layer, newData);
        CommandList.getInstance().addCommand(command);
    }

}