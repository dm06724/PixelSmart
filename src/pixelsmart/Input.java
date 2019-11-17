package pixelsmart;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.event.MouseInputAdapter;

public class Input extends MouseInputAdapter {

    private int mouseX, mouseY;
    private boolean mouse1WasPressed, mouse1IsPressed, mouse2WasPressed, mouse2IsPressed;
    private boolean mouse1Press, mouse2Press;
    private static Input instance;

    private Input() {

    }

    public static synchronized Input getInstance() {
        if (instance == null) {
            instance = new Input();
        }

        return instance;
    }

    protected void update() {
        mouse1WasPressed = mouse1IsPressed;
        mouse2WasPressed = mouse2IsPressed;

        mouse1IsPressed = mouse1Press;
        mouse2IsPressed = mouse2Press;
    }

    public static boolean getMouseButtonDown(int button) {
        return getInstance().mouse1IsPressed && !getInstance().mouse1WasPressed;
    }

    public static boolean getMouseButtonUp(int button) {
        return !getInstance().mouse1IsPressed && getInstance().mouse1WasPressed;
    }

    public static boolean getMouseButton(int button) {
        return getInstance().mouse1IsPressed && getInstance().mouse1WasPressed;
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
            mouse1Press = true;
        }

        if (e.getButton() == MouseEvent.BUTTON2) {
            mouse2Press = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouse1Press = false;
        }

        if (e.getButton() == MouseEvent.BUTTON1) {
            mouse2Press = false;
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