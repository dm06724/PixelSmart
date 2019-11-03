package pixelsmart;

public class LineTool implements Tool {

    private int startMX, startMY;

    @Override
    public void startAction() {
        startMX = Input.getMouseX();
        startMY = Input.getMouseY();
    }

    @Override
    public void updateAction() {

    }

    @Override
    public void finishAction() {
        int mx = Input.getMouseX();
        int my = Input.getMouseY();

        // Draw line from (startMX, startMY) to (mx, my)
    }

}