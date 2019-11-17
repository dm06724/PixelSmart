package pixelsmart.commands;

import java.awt.Color;

import pixelsmart.BrushColorType;
import pixelsmart.tools.ToolManager;

public class SetBrushColorCommand implements Command {

    private BrushColorType type;
    private Color color;
    private Color previousColor;

    public SetBrushColorCommand(BrushColorType type, Color color) {
        this.type = type;
        this.color = color;
    }

    @Override
    public void execute() {
        if (this.type == BrushColorType.PRIMARY) {
            this.previousColor = ToolManager.getInstance().getPrimaryBrushColor();
            ToolManager.getInstance().setPrimaryBrushColor(color);
        } else if (this.type == BrushColorType.SECONDARY) {
            this.previousColor = ToolManager.getInstance().getSecondaryBrushColor();
            ToolManager.getInstance().setSecondaryBrushColor(color);
        }
    }

    @Override
    public void undo() {
        if (previousColor == null)
            return;

        if (this.type == BrushColorType.PRIMARY) {
            ToolManager.getInstance().setPrimaryBrushColor(previousColor);
        } else if (this.type == BrushColorType.SECONDARY) {
            ToolManager.getInstance().setSecondaryBrushColor(previousColor);
        }
    }

}