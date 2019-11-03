package pixelsmart;

public class MoveTool implements Tool{

    private int mouseOffsetX, mouseOffsetY;

    @Override
    public void startAction() {
        // TODO Auto-generated method stub

        int layerX = Project.getCurrent().getImage().getActiveLayer().getX();
        int layerY = Project.getCurrent().getImage().getActiveLayer().getY();

        mouseOffsetX = layerX - Input.getMouseX();
        mouseOffsetY = layerY - Input.getMouseY();
    }

    @Override
    public void updateAction() {
        Project.getCurrent().getImage().getActiveLayer().setPosition(Input.getMouseX() + mouseOffsetX, Input.getMouseY() + mouseOffsetY);
    }

    @Override
    public void finishAction() {
        // TODO Auto-generated method stub

    }

}