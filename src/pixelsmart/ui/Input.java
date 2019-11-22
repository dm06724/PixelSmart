package pixelsmart.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.event.MouseInputAdapter;

public class Input extends MouseInputAdapter {
    public static final int LEFT_MOUSE = 0;
    public static final int RIGHT_MOUSE = 1;

    private static Input instance;

    private int mouseX, mouseY;
    private final boolean[] mouseIsPressed = { false, false };
    private final boolean[] mouseWasPressed = { false, false };
    private boolean[] mousePress = { false, false };

    private Input() {

    }

    public static synchronized Input getInstance() {
        if (instance == null) {
            instance = new Input();
        }

        return instance;
    }

    protected void update() {
        mouseWasPressed[LEFT_MOUSE] = mouseIsPressed[LEFT_MOUSE];
        mouseWasPressed[RIGHT_MOUSE] = mouseIsPressed[RIGHT_MOUSE];

        mouseIsPressed[LEFT_MOUSE] = mousePress[LEFT_MOUSE];
        mouseIsPressed[RIGHT_MOUSE] = mousePress[RIGHT_MOUSE];
    }

    public static boolean getMouseButtonDown(int button) {
        return getInstance().mouseIsPressed[button] && !getInstance().mouseWasPressed[button];
    }

    public static boolean getMouseButtonUp(int button) {
        return !getInstance().mouseIsPressed[button] && getInstance().mouseWasPressed[button];
    }

    public static boolean getMouseButton(int button) {
        return getInstance().mouseIsPressed[button] && getInstance().mouseWasPressed[button];
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
            mousePress[LEFT_MOUSE] = true;
        }

        if (e.getButton() == MouseEvent.BUTTON2) {
            mousePress[RIGHT_MOUSE] = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mousePress[LEFT_MOUSE] = false;
        }

        if (e.getButton() == MouseEvent.BUTTON1) {
            mousePress[RIGHT_MOUSE] = false;
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.isAltDown()) {
            double scroll = e.getWheelRotation();
            MainWindow.getInstance().getPanel().zoomOut(scroll);
        }
    }

    public static int getMouseX() {
        return getInstance().mouseX;
    }

    public static int getMouseY() {
        return getInstance().mouseY;
    }
}