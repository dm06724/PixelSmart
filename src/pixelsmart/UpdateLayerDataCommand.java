package pixelsmart;

import java.awt.image.BufferedImage;

public class UpdateLayerDataCommand implements Command {

    private BufferedImage previousData;
    private BufferedImage newData;
    private Layer layer;

    public UpdateLayerDataCommand(Layer layer, BufferedImage newData) {
        this.layer = layer;
        this.newData = newData;
    }

    @Override
    public void execute() {
        this.previousData = layer.getData();
        layer.setData(newData);
    }

    @Override
    public void undo() {
        if (previousData == null)
            return;
        layer.setData(previousData);
    }

}