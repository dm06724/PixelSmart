package pixelsmart;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.event.MouseInputAdapter;

public class Input extends MouseInputAdapter {

    private int mouseX, mouseY;
    private static Input instance;

    private static Color color = Color.BLACK;
    private static JButton colorWheelButton;

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
        	if(Project.getCurrent()!=null)
        	{
	        	CommandList.getInstance().addCommand(new PencilTool(10, color));
	        	//CommandList.getInstance().addCommand(new ColorPickerTool());
        	}
        	else
        	{
        		System.out.println("You must create a new project first");
        	}
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
    
    public static void setColor(Color c)
    {
    	color = c;
    	if(colorWheelButton!=null)
    		colorWheelButton.setBackground(c);
    }
    
    public static Color getColor()
    {
    	return color;
    }
    
    public static void setColorButton(JButton b)
    {
    	colorWheelButton = b;
    }
}