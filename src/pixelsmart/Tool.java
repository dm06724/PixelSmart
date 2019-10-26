package pixelsmart;

//Command Pattern
public interface Tool {
	public void performAction();

	public void undoAction();
	public void redoAction();
	public void finishAction();
	
}
