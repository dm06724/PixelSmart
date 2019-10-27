package pixelsmart;

import java.awt.Color;

public class ColorPickerTool implements Tool {

	public ColorPickerTool() {

	}

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
		Layer layer = Project.getCurrent().getImage().getActiveLayer();
		Color newColor = layer.getPixelColor(Input.getMouseX(), Input.getMouseY());

		SetBrushColorCommand colorCommand = new SetBrushColorCommand(BrushColorType.PRIMARY, newColor);
		CommandList.getInstance().addCommand(colorCommand);
	}

}
