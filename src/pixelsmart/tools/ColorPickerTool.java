package pixelsmart.tools;

import java.awt.Color;

import pixelsmart.commands.CommandList;
import pixelsmart.commands.SetBrushColorCommand;
import pixelsmart.image.Layer;
import pixelsmart.ui.ImagePanel;

public class ColorPickerTool extends ToolAdapter {

    @Override
    public void finishAction(ImagePanel panel) {
        Layer layer = panel.getActiveLayer();

        if (layer == null) {
            return;
        }

        int mx = panel.getMouseX(ImagePanel.RELATIVE_TO_LAYER);
        int my = panel.getMouseY(ImagePanel.RELATIVE_TO_LAYER);
        Color newColor = layer.getPixelColor(mx, my);

        SetBrushColorCommand colorCommand = new SetBrushColorCommand(BrushColorType.PRIMARY, newColor);
        CommandList.getInstance().addCommand(colorCommand);
    }
}
