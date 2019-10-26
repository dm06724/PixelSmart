package pixelsmart;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorPickerTool implements Tool {

	private Color oldColor = new Color(0);
	private Color newColor = new Color(0);
	
	public ColorPickerTool()
	{
		oldColor = Input.color;
		System.out.println("OLD: "+oldColor + ", "+oldColor.getAlpha());
	}
	
	@Override
	public void performAction() {
		// TODO Auto-generated method stub
		
		BufferedImage b = Project.getCurrent().getImage().getActiveLayer().getData();
		int intColor = b.getRGB(Input.getMouseX(), Input.getMouseY());
		
		int alphaComponent = (intColor>>24) & 0xFF;
		int redComponent = (intColor>>16) & 0xFF;
		int greenComponent = (intColor>>8) & 0xFF;
		int blueComponent = (intColor>>0) & 0xFF;
		newColor = new Color(redComponent, greenComponent, blueComponent, alphaComponent);
		
		Input.color = newColor;
	}

	@Override
	public void undoAction() {
		// TODO Auto-generated method stub
		Input.color = oldColor;
	}

	@Override
	public void redoAction() {
		// TODO Auto-generated method stub
		Input.color = newColor;
	}

	@Override
	public void finishAction() {
		// TODO Auto-generated method stub
		// Not needed
		System.out.println("NEW: "+newColor + ", "+newColor.getAlpha());
	}

}
