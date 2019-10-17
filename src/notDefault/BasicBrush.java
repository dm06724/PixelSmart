package notDefault;

import java.awt.Color;
import java.awt.Graphics2D;

public class BasicBrush implements Tool {

	@Override
	public void performAction() {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D)MainWindow.myP.img.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillOval(MainWindow.getMouseX()-2, MainWindow.getMouseY()-2, 4, 4);
	}

	@Override
	public void undoAction() {
		// TODO Auto-generated method stub
		
	}

}
