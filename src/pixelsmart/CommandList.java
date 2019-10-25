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
	public static final int AMOUNT_OF_COMMANDS = 20;
	private int index = 0;

	private LinkedList<Tool> listOfCommands = new LinkedList<Tool>();

	public CommandList() {

	}

	public void redo() {

	}

	public void undo() {

	}

	public void addCommand(Tool c) {
		listOfCommands.add(c);

		while (listOfCommands.size() > AMOUNT_OF_COMMANDS){
			listOfCommands.removeFirst();
		}
	}

	public void update() {
		// continue to call the perform action of the current tool
		if (listOfCommands.size() > 0)
			listOfCommands.getLast().performAction();
	}
}
