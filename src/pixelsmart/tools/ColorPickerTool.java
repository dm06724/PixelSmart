package pixelsmart.tools;

import java.awt.Color;

import pixelsmart.BrushColorType;
import pixelsmart.Input;
import pixelsmart.commands.CommandList;
import pixelsmart.commands.SetBrushColorCommand;
import pixelsmart.image.Image;
import pixelsmart.image.Layer;

public class ColorPickerTool extends ToolAdapter {

	public ColorPickerTool() {

	}

	@Override
	public void finishAction(Image image) {
		Layer layer = image.getActiveLayer();
		Color newColor = layer.getPixelColor(Input.getMouseX(), Input.getMouseY());

		SetBrushColorCommand colorCommand = new SetBrushColorCommand(BrushColorType.PRIMARY, newColor);
		CommandList.getInstance().addCommand(colorCommand);
	}
}
