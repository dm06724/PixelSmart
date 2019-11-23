package pixelsmart.commands;

import java.awt.Shape;

import pixelsmart.ui.ImagePanel;

public class SetClipShapeCommand implements Command {

    private ImagePanel panel;
    private Shape previousShape;
    private Shape newShape;

    public SetClipShapeCommand(ImagePanel panel, Shape shape) {
        this.panel = panel;
        this.newShape = shape;
    }

    @Override
    public void execute() {
        this.previousShape = panel.getClip(ImagePanel.RELATIVE_TO_IMAGE);
        panel.setClip(this.newShape);
    }

    @Override
    public void undo() {
        panel.setClip(previousShape);
    }

}