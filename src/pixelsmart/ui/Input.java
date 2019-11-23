package pixelsmart.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.event.MouseInputAdapter;

public class Input extends MouseInputAdapter {
    public static final int LEFT_MOUSE = 0;
    public static final int RIGHT_MOUSE = 1;

    private static Input instance;

    private int mouseX, mouseY;
    private int scoll;
    private final boolean[] mouseIsPressed = { false, false };
    private final boolean[] mouseWasPressed = { false, false };
    private boolean[] mousePress = { false, false };

    private Input() {

    }

    protected static synchronized Input getInstance() {
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

    public static boolean getAnyMouseButtonDown() {
        return getMouseButtonDown(Input.LEFT_MOUSE) || getMouseButtonDown(Input.RIGHT_MOUSE);
    }

    public static boolean getAnyMouseButtonUp() {
        return getMouseButtonUp(Input.LEFT_MOUSE) || getMouseButtonUp(Input.RIGHT_MOUSE);
    }

    public static boolean getAnyMouseButton() {
        return getMouseButton(Input.LEFT_MOUSE) || getMouseButton(Input.RIGHT_MOUSE);
    }

    public static int getScroll(){
        return getInstance().scoll;
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

        if (e.getButton() == MouseEvent.BUTTON3) {
            mousePress[RIGHT_MOUSE] = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mousePress[LEFT_MOUSE] = false;
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            mousePress[RIGHT_MOUSE] = false;
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int mx = ImagePanel.get().getMouseX(ImagePanel.RELATIVE_TO_IMAGE);
        int my = ImagePanel.get().getMouseY(ImagePanel.RELATIVE_TO_IMAGE);
        if (e.isAltDown()) {
            double scroll = e.getWheelRotation();
            MainWindow.getInstance().getPanel().zoomOutAround(scroll, mx, my);
        }
    }

    public static int getMouseX() {
        return getInstance().mouseX;
    }

    public static int getMouseY() {
        return getInstance().mouseY;
    }
}