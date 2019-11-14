package pixelsmart;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import pixelsmart.image.Image;
import pixelsmart.tools.PencilTool;
import pixelsmart.tools.Tool;

public class Project {
    private static Project instance;
    private Color primaryBrushColor = Color.BLACK;
    private Color secondaryBrushColor = Color.WHITE;
    private Image image;
    private int brushSize = 20;
    private Tool tool;
    // private GeneralPath brushPath;
    private String brushMode;

    private Project(int imageWidth, int imageHeight) {
        this.image = new Image(imageWidth, imageHeight);
        this.tool = new PencilTool();
    }

    public static Project getCurrent() {
        return instance;
    }

    public static synchronized Project createNew(int imageWidth, int imageHeight) {
        System.out.println("createNew");
        if (getCurrent() != null) {
            // Prompt to save current project
            int result = JOptionPane.showOptionDialog(MainWindow.getInstance(),
                    "Do you want to save your current project?", "Save Current Project?",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            switch (result) {
            default:
            case JOptionPane.CLOSED_OPTION:
            case JOptionPane.CANCEL_OPTION:
                return getCurrent();
            case JOptionPane.YES_OPTION:
                if (!getCurrent().save(null))
                    return getCurrent();
                break;
            case JOptionPane.NO_OPTION:
                break;
            }
        }
        return instance = new Project(imageWidth, imageHeight);
    }

    protected void update() {

        if (tool != null) {
            if (Input.getMouseButtonDown(0)) {
                // System.out.println("button down");
                tool.startAction(image);
            } else if (Input.getMouseButton(0)) {
                // System.out.println("button held");
                tool.updateAction(image);
            } else if (Input.getMouseButtonUp(0)) {
                // System.out.println("button up");
                tool.finishAction(image);
            }
        } else {
            System.out.println("tool =null");
        }
    }

    public Image getImage() {
        return this.image;
    }

    public Color getPrimaryBrushColor() {
        return this.primaryBrushColor;
    }

    public Color getSecondaryBrushColor() {
        return this.secondaryBrushColor;
    }

    public int getBrushSize() {
        return this.brushSize;
    }

    public void setPrimaryBrushColor(Color color) {
        this.primaryBrushColor = color;
    }

    public void setSecondaryBrushColor(Color color) {
        this.secondaryBrushColor = color;
    }

    public void setBrushSize(int size) {
        this.brushSize = size;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public Tool getTool() {
        return this.tool;
    }

    public void setBrushMode(String x) {
        this.brushMode = x;
    }

    public String getBrushMode() {
        return brushMode;
    }
    
    private void addToArrayList(int size, int value, ArrayList<Byte> list)
    {
    	if(size<=4)
    	{
	    	for(int i=0; i<size; i++)
	    	{
		    	list.add((byte)((value >> 8*i) & 0xFF));
	    	}
    	}
    	
    }
    
    private ArrayList<Byte> getFileHeader()
    {
    	//must store overall Width, Height, Amount of Layers, Current Brush, Current Color,
    	//Active Layer
    	ArrayList<Byte> head = new ArrayList<Byte>();
    	head.add((byte)'p');
    	head.add((byte)'s');
    	
    	addToArrayList(4, image.getWidth(), head);
    	addToArrayList(4, image.getHeight(), head);
    	addToArrayList(2, image.getLayers().size(), head);
    	
    	return head;
    }
    
    private void writeFileHeader(FileOutputStream f)
    {
    	try {
			ArrayList<Byte> values = getFileHeader();
			byte[] convertedValues = new byte[values.size()];
			for(int i=0; i<values.size(); i++)
			{
				convertedValues[i] = values.get(i);
			}
			
			f.write(convertedValues);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public boolean save(File file) {
        // TODO serialize all project data somehow
        // layers, brush size, brush colors, etc.
    	FileOutputStream fileWriter = null;
    	
    	try {
			fileWriter = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(fileWriter!=null)
    	{
    		writeFileHeader(fileWriter);
    		
    		try {
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    	
        // JFileChooser fileChooser = new JFileChooser();

        // int result = fileChooser.showSaveDialog(null);
        // if (result == JFileChooser.APPROVE_OPTION) {
        // File file = fileChooser.getSelectedFile();

        // return true;
        // } else {
        // return false;
        // }
        //return false;
    }

    public static synchronized Project load(File file) {
        // TODO load project
        return null;
    }
}