package pixelsmart;

import java.util.*;

public class CommandList {

	// Should be a singlton.
	// Should be able to keep track of all of the commands up to 20ish
	// and call there perform and undo actions
	// should be able to redo from any point provided there are commands afterwards
	// After adding a command after a series of undos, the commands after the new
	// one should be
	// deleted.

	// Whoever works on this, feel free to change whatever you need. This is just a
	// template.
	private static final int AMOUNT_OF_COMMANDS = 50;
	private static CommandList instance;

	private LinkedList<Tool> listOfCommands = new LinkedList<Tool>();
	
	private int commandIndex = 0;

	private CommandList() {
	}

	public static synchronized CommandList getInstance() {
		if (instance == null)
			instance = new CommandList();
		return instance;
	}

	public void redo() {
		
		if(commandIndex > 0)
		{
			System.out.println("REDO");
			//because of the stop tool, we have to do this in twos instead of ones
			listOfCommands.get( listOfCommands.size()-commandIndex).redoAction();
			commandIndex-=2;
			updateAllLayers();
		}
	}

	public void undo() {
		
		if(commandIndex < listOfCommands.size())
		{
			System.out.println("UNDO");
			//because of the stop tool, we have to do this in twos instead of ones
			listOfCommands.get( listOfCommands.size()-commandIndex-2).undoAction();
			commandIndex+=2;
			updateAllLayers();
		}
	}

	public void addCommand(Tool c) {
		if(listOfCommands.size()>0)
			listOfCommands.getLast().finishAction();
		
		listOfCommands.add(c);
		

		while (listOfCommands.size() > AMOUNT_OF_COMMANDS) {
			//remove both the stop tool and the general tool
			//stop tool always comes after any general tool
			
			Tool t = listOfCommands.removeFirst(); //general tool
			listOfCommands.removeFirst(); //stop tool
			
			updateOldLayerData(t);
		}
	}

	public void update() {
		// continue to call the perform action of the current tool
		
		if (listOfCommands.size() > 0)
			listOfCommands.getLast().performAction();

	}
	
	public void updateOldLayerData(Tool t)
	{
		//only called when we add new commands that extend the max number of commands
		//it permanently modifies the old data in one or more of the layers.
		
		//find a better way to do this without try/catch if you can
		
		try
		{
			DrawingTool dt = (DrawingTool)t;
			
			//this part is bad for performance.
			//currently do not have access to the tools applied layer. fix later
			for(Layer l : Project.getCurrent().getImage())
			{
				l.copyToCurrentData();
			}
			
			dt.reDraw();
			
			for(Layer l : Project.getCurrent().getImage())
			{
				l.copyToOldData();
			}
			
			updateAllLayers();
		}
		catch(Exception e)
		{
			//couldn't cast
		}
	}
	
	public void updateAllLayers()
	{
		//The Idea, draw final changes to each layer.
		//Do not include the things in the current list of commands
		
		//first, draw the previous image of each layer onto its current image
		
		for(Layer l : Project.getCurrent().getImage())
		{
			System.out.println("Updating");
			l.copyToCurrentData();
		}
		
		//next, redraw all of the tools shapes into their corresponding layer
		//each drawing tool remembers its layer and its shape
		redrawLayers();
	}
	
	public void redrawLayers()
	{
		for(Tool t: listOfCommands)
		{
			try
			{
				DrawingTool dt = (DrawingTool)t;
				dt.reDraw();
			}
			catch(Exception e)
			{
				//couldn't cast
			}
		}
	}
}
