package pixelsmart.commands;

import pixelsmart.tools.ToolManager;

public class SetBrushSizeCommand implements Command {

    private int size;
    private int previousSize;

    public SetBrushSizeCommand(int size) {
        this.size = size;
        this.previousSize = Integer.MIN_VALUE;
    }

    @Override
    public void execute() {
        previousSize = ToolManager.getInstance().getBrushSize();
        ToolManager.getInstance().setBrushSize(size);
    }

    @Override
    public void undo() {
        if (previousSize < 0) {
            return;
        }
        ToolManager.getInstance().setBrushSize(previousSize);
        previousSize = Integer.MIN_VALUE;
    }

}