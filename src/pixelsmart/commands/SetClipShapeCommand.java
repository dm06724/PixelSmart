package pixelsmart.commands;

import java.awt.Shape;

import pixelsmart.ui.ImagePanel;

public class SetClipShapeCommand implements Command {

    private Shape previousShape;
    private Shape newShape;

    public SetClipShapeCommand(Shape shape) {
        this.newShape = shape;
    }

    @Override
    public void execute() {
        this.previousShape = ImagePanel.get().getClip(ImagePanel.RELATIVE_TO_IMAGE);
        ImagePanel.get().setClip(this.newShape);
    }

    @Override
    public void undo() {
        ImagePanel.get().setClip(previousShape);
    }

}