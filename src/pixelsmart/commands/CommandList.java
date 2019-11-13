package pixelsmart.commands;

import java.util.LinkedList;

public class CommandList {
	private static final int AMOUNT_OF_COMMANDS = 50;
	private static CommandList instance;

	private LinkedList<Command> commands = new LinkedList<Command>();

	private int commandIndex = 0;

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

	public void addCommand(Command c) {
		commands.add(c);
		c.execute();
		commandIndex = commands.size() - 1;

		while (commands.size() > AMOUNT_OF_COMMANDS) {
			commands.removeFirst();
		}
	}
}
