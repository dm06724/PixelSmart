package pixelsmart.tools;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

import pixelsmart.commands.CommandList;
import pixelsmart.commands.UpdateLayerDataCommand;
import pixelsmart.image.Layer;
import pixelsmart.shapes.Shape;
import pixelsmart.ui.ImagePanel;

public class StencilTool extends DrawingTool {

    private Path2D.Double finalStrokeShape;
    private Shape shapeBehavior;

    @Override
    public void startAction(final ImagePanel panel) {
        if (panel.getActiveLayer() == null) {
            return;
        }

        finalStrokeShape = new Path2D.Double();
    }

    @Override
    public void updateAction(final ImagePanel panel) {
        if (panel.getActiveLayer() == null) {
            return;
        }

        final int mx = panel.getMouseX(ImagePanel.RELATIVE_TO_LAYER);
        final int my = panel.getMouseY(ImagePanel.RELATIVE_TO_LAYER);

        finalStrokeShape.append(shapeBehavior.getPath(mx, my), false);
    }

    @Override
    public void finishAction(final ImagePanel panel) {
        final Layer layer = panel.getActiveLayer();

        if (layer == null) {
            return;
        }

        final BufferedImage newData = layer.copyData();
        final Graphics2D g = newData.createGraphics();

        g.setColor(ToolManager.getInstance().getPrimaryBrushColor());
        final BasicStroke stroke = new BasicStroke(ToolManager.getInstance().getBrushSize());
        g.setStroke(stroke);

        g.draw(finalStrokeShape);
        g.fill(finalStrokeShape);

        final UpdateLayerDataCommand c = new UpdateLayerDataCommand(layer, newData);
        CommandList.getInstance().addCommand(c);

        g.dispose();
    }
}
