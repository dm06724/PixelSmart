package pixelsmart;

public class MoveTool implements Tool {

    private int mouseOffsetX, mouseOffsetY;

    @Override
    public void startAction() {
        int layerX = Image.getCurrent().getActiveLayer().getX();
        int layerY = Image.getCurrent().getActiveLayer().getY();

        mouseOffsetX = layerX - Input.getMouseX();
        mouseOffsetY = layerY - Input.getMouseY();
    }

    @Override
    public void updateAction() {
        Layer layer = Image.getCurrent().getActiveLayer();

        int lx = Input.getMouseX() + mouseOffsetX;
        int ly = Input.getMouseY() + mouseOffsetY;

        layer.setPosition(lx, ly);
    }

    @Override
    public void finishAction() {

    }

}