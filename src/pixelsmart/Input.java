package pixelsmart;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class Input extends MouseInputAdapter {

    private int mouseX, mouseY;
    private static Input instance;

    private Input() {

    }

    public static synchronized Input getInstance() {
        if (instance == null) {
            instance = new Input();
        }

        return instance;
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // commands.addCommand(new PencilTool(10, Color.black));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // commands.addCommand(new StopTool());
        }
    }

    public static int getMouseX() {
        return getInstance().mouseX;
    }

    public static int getMouseY() {
        return getInstance().mouseY;
    }
}