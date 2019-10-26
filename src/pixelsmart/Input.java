package pixelsmart;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class Input extends MouseInputAdapter {

    private int mouseX, mouseY;
    private static Input instance;

    public static Color color = Color.BLACK;

    private Input() {

    }

    public static synchronized Input getInstance() {
        if (instance == null) {
            instance = new Input();
        }

        return instance;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            CommandList.getInstance().addCommand(new PencilTool(10, color));
        }
        
        if(e.getButton() == MouseEvent.BUTTON2) {
        	CommandList.getInstance().undo();
        }
        
        if(e.getButton() == MouseEvent.BUTTON3) {
        	CommandList.getInstance().redo();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            CommandList.getInstance().addCommand(new StopTool());
        }
    }

    public static int getMouseX() {
        return getInstance().mouseX;
    }

    public static int getMouseY() {
        return getInstance().mouseY;
    }
}