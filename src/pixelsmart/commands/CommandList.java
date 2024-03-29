package pixelsmart.commands;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import pixelsmart.image.Layer;
import pixelsmart.image.Image;
import pixelsmart.ui.ImagePanel;
import pixelsmart.util.MathUtil;
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
    	if(ImagePanel.get().getClip(ImagePanel.RELATIVE_TO_LAYER) == null) {
    		return;
    	}
    	
    	Shape selectionClip = ImagePanel.get().getClip(ImagePanel.RELATIVE_TO_LAYER);
    	Rectangle rectClip = selectionClip.getBounds();
    	Layer activeLayer = ImagePanel.get().getActiveLayer();
    	
    	rectClip.x = MathUtil.clamp(rectClip.x, 0, ImagePanel.get().getActiveLayer().getWidth());
    	rectClip.y = MathUtil.clamp(rectClip.y, 0, ImagePanel.get().getActiveLayer().getHeight());
    	
    	if(rectClip.x + rectClip.width > ImagePanel.get().getActiveLayer().getWidth())
    		rectClip.width = ImagePanel.get().getActiveLayer().getWidth()-rectClip.x;
    	if(rectClip.y + rectClip.height > ImagePanel.get().getActiveLayer().getHeight())
    		rectClip.height = ImagePanel.get().getActiveLayer().getHeight()-rectClip.y;
    	
    	copyImage = activeLayer.copyData().getSubimage(rectClip.x, rectClip.y, rectClip.width, rectClip.height);
    	
    	//System.out.format("Copied Image! X: %d, Y: %d, X2: %d, Y2: %d\n", rectClip.x, rectClip.y, rectClip.width, rectClip.height);
    }
    
    public void paste() {
    	//System.out.println("Pasted!");
    	if(copyImage == null) {
    		return;
    	}
    	
    	Image img = ImagePanel.get().getImage();
    	String name = "";
    	
    	if(img.containsLayer(x -> x.getName().equals("New Layer"))) {
    		name = "New Layer " + img.layerCount();
    	}else {
    		name = "New Layer";
    	}
    	img.addLayer(name);
    	ImagePanel.get().setActiveLayer(name);
    	Layer activeLayer = ImagePanel.get().getActiveLayer();
    	BufferedImage copyData = activeLayer.copyData();
    	Graphics2D g = copyData.createGraphics();
    	g.drawImage(copyImage, 0, 0, null);
    	//Shape shapeClip = ImagePanel.get().getClip(ImagePanel.RELATIVE_TO_IMAGE);
    	//Rectangle rectClip = shapeClip.getBounds();
    	Rectangle newClip = new Rectangle(0, 0, copyImage.getWidth(), copyImage.getHeight());

        SetClipShapeCommand command = new SetClipShapeCommand(newClip);
    	UpdateLayerDataCommand dc = new UpdateLayerDataCommand(activeLayer, copyData);
    	CommandList.getInstance().addCommand(command);
		CommandList.getInstance().addCommand(dc);
		
		g.dispose();
    }
    
    public void cut() {
    	if(ImagePanel.get().getClip(ImagePanel.RELATIVE_TO_LAYER) == null) {
    		return;
    	}
    	
    	Shape selectionClip = ImagePanel.get().getClip(ImagePanel.RELATIVE_TO_LAYER);
    	Rectangle rectClip = selectionClip.getBounds();
    	Layer activeLayer = ImagePanel.get().getActiveLayer();
    	BufferedImage copyData = activeLayer.copyData();
    	Graphics2D g = copyData.createGraphics();
    	g.setBackground(new Color(255,255,255,0));

    	rectClip.x = MathUtil.clamp(rectClip.x, 0, ImagePanel.get().getActiveLayer().getWidth());
    	rectClip.y = MathUtil.clamp(rectClip.y, 0, ImagePanel.get().getActiveLayer().getHeight());
    	
    	if(rectClip.x + rectClip.width > ImagePanel.get().getActiveLayer().getWidth())
    		rectClip.width = ImagePanel.get().getActiveLayer().getWidth()-rectClip.x;
    	if(rectClip.y + rectClip.height > ImagePanel.get().getActiveLayer().getHeight())
    		rectClip.height = ImagePanel.get().getActiveLayer().getHeight()-rectClip.y;
    	
    	g.clearRect(rectClip.x, rectClip.y, rectClip.width, rectClip.height);
    	copyImage = activeLayer.copyData().getSubimage(rectClip.x, rectClip.y, rectClip.width, rectClip.height);
    	
    	UpdateLayerDataCommand dc = new UpdateLayerDataCommand(activeLayer, copyData);
		CommandList.getInstance().addCommand(dc);
		CommandList.getInstance().addCommand(new SetClipShapeCommand(null));
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
