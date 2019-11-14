package pixelsmart.commands;

import java.awt.Color;

import pixelsmart.BrushColorType;
import pixelsmart.Project;

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
            this.previousColor = Project.getCurrent().getPrimaryBrushColor();
            Project.getCurrent().setPrimaryBrushColor(color);
        } else if (this.type == BrushColorType.SECONDARY) {
            this.previousColor = Project.getCurrent().getSecondaryBrushColor();
            Project.getCurrent().setSecondaryBrushColor(color);
        }
    }

    @Override
    public void undo() {
        if (previousColor == null)
            return;

        if (this.type == BrushColorType.PRIMARY) {
            Project.getCurrent().setPrimaryBrushColor(previousColor);
        } else if (this.type == BrushColorType.SECONDARY) {
            Project.getCurrent().setSecondaryBrushColor(previousColor);
        }
    }

}