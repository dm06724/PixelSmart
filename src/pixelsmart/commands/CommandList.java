package pixelsmart.commands;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import pixelsmart.image.Layer;
import pixelsmart.ui.ImagePanel;
import pixelsmart.tools.*;

public class CommandList {
    private static final int AMOUNT_OF_COMMANDS = 50;
    private static CommandList instance;

    private LinkedList<Command> commands = new LinkedList<Command>();

    private int commandIndex = 0;
    
    private BufferedImage copyImage;

    private CommandList() {
    }

    public static synchronized CommandList getInstance() {
        if (instance == null)
            instance = new CommandList();
        return instance;
    }

    public void undo() {
        if (commandIndex >= 0 && commandIndex < commands.size()) {
            commands.get(commandIndex--).undo();
        }
    }

    public void redo() {
        if (commandIndex >= -1 && commandIndex < commands.size() - 1) {
            commands.get(++commandIndex).execute();
        }
    }
    
    public void copy() {
    	//System.out.println("Copied!");
    	if(ImagePanel.get().getClip(ImagePanel.RELATIVE_TO_IMAGE) == null) {
    		return;
    	}
    	
    	Shape selectionClip = ImagePanel.get().getClip(ImagePanel.RELATIVE_TO_IMAGE);
    	Rectangle rectClip = selectionClip.getBounds();
    	Layer activeLayer = ImagePanel.get().getActiveLayer();
    	
    	copyImage = activeLayer.copyData().getSubimage(rectClip.x, rectClip.y, rectClip.width, rectClip.height);
    	
    	//System.out.format("Copied Image! X: %d, Y: %d, X2: %d, Y2: %d\n", rectClip.x, rectClip.y, rectClip.width, rectClip.height);
    }
    
    public void paste() {
    	//System.out.println("Pasted!");
    	if(copyImage == null) {
    		return;
    	}
    	
    	Layer activeLayer = ImagePanel.get().getActiveLayer();
    	BufferedImage copyData = activeLayer.copyData();
    	Graphics2D g = copyData.createGraphics();
    	g.drawImage(copyImage, 0, 0, null);
    	
    	UpdateLayerDataCommand dc = new UpdateLayerDataCommand(activeLayer, copyData);
		CommandList.getInstance().addCommand(dc);
		
		g.dispose();
    }

    public void addCommand(Command c) {
        while (commands.size() > commandIndex + 1) {
            commands.removeLast();
        }

        commands.add(c);
        c.execute();
        commandIndex = commands.size() - 1;

        while (commands.size() > AMOUNT_OF_COMMANDS) {
            commands.removeFirst();
        }
    }

    public void clear() {
        commands.clear();
    }
}
