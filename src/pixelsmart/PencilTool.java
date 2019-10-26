package pixelsmart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.Point;
import java.util.*;

public class PencilTool implements Tool, DrawingTool {

	// should be able to keep track of its size, color, and where it gets applied
	// must maintain the image before it was modified so it can undo.

	// TODO make these fields part of the project class somehow
	private Color color;
	private int size = 1;

	private int lastMouseX = -1, lastMouseY = -1;

	private Path2D.Double finalStrokeShape = new Path2D.Double();
	private ArrayList<Point> listOfPoints = new ArrayList<Point>();
	
	//what layer this pencil tool acts on. This way, undo and redo
	//affect the same layer.
	
	private Layer appliedLayer = null;
	
	public PencilTool(int size, Color color) {
		this.color = color;
		this.size = size;
		appliedLayer = Project.getCurrent().getImage().getActiveLayer();
	}

	public void recreateFinalShape()
	{
		//possibly use to draw and undo what the pencil has done.
		//To slow for real time though.
		
		//Could save all tools drawings and draw them in order, but performance could be
		//an issue. Better than saving the entire image though.
		
		//Currently, the finalStrokeShape does not perfectly match what was originally drawn
		//the final shape is smoother in certain spots.
		//While I prefer the smoother spots, should be fixed anyway.
		
		finalStrokeShape = new Path2D.Double();
		
		finalStrokeShape.moveTo(listOfPoints.get(0).getX(), listOfPoints.get(0).getY());
		for(int i=1; i<listOfPoints.size(); i++)
		{
			finalStrokeShape.lineTo(listOfPoints.get(i).getX(), listOfPoints.get(i).getY());
		}
	}
	
	public Shape getFinalShape()
	{
		return finalStrokeShape;
	}

	public void addToShape(int mx, int my)
	{
		if(lastMouseX == -1 && lastMouseY == -1)
		{
			listOfPoints.add(new Point(mx, my));
			listOfPoints.add(new Point(mx, my));
		}
		else
		{
			if(lastMouseX != mx && lastMouseY != my)
			{
				listOfPoints.add(new Point(mx, my));
			}
		}
		
	}
	
	@Override
	public void performAction() {
		// TODO Need a better way of accessing current layer / drawing to it. Maybe
		// implement facade somehow?
		Graphics2D g = appliedLayer.getData().createGraphics();

		g.setColor(color);
		BasicStroke t = new BasicStroke(size);
		g.setStroke(t);
		
		int mx = Input.getMouseX();
		int my = Input.getMouseY();
		addToShape(mx, my);
		
		if (lastMouseX < 0 || lastMouseY < 0) {
			lastMouseX = mx;
			lastMouseY = my;
		}

		//g.draw(fullStrokeShape);
		g.drawLine(lastMouseX, lastMouseY, mx, my);

		lastMouseX = mx;
		lastMouseY = my;
		
	}

	@Override
	public void undoAction() {
		// the new currentImage
		// BufferedImage k = MainWindow.panel.copyImage();
		// MainWindow.panel.setImage(oldImage);
		// oldImage = k;
		finalStrokeShape = null;
	}

	@Override
	public void redoAction() {
		// TODO Auto-generated method stub
		recreateFinalShape();
	}

	@Override
	public void finishAction() {
		// TODO Auto-generated method stub
		recreateFinalShape();
	}

	@Override
	public void reDraw() {
		// TODO Auto-generated method stub
		
		// should redraw what it had before.
		Graphics2D g = appliedLayer.getData().createGraphics();
		
		g.setColor(color);
		BasicStroke t = new BasicStroke(size);
		g.setStroke(t);
		
		if(finalStrokeShape!=null)
		{
			g.draw(finalStrokeShape);
		}
	}

}
