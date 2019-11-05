package pixelsmart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;

public class StencilTool implements Tool {

	private Path2D.Double finalStrokeShape;
	private Graphics2D g;
	Color eraserOriginalColor = null;

	@Override
	public void startAction() {
		finalStrokeShape = new Path2D.Double();
		Layer layer = Project.getCurrent().getImage().getActiveLayer();
		BufferedImage newData = layer.copyData();
		g = newData.createGraphics();
		String brush = Project.getCurrent().getBrushShape();
		if (brush.contentEquals("eraser")) {
			eraserOriginalColor = Project.getCurrent().getPrimaryBrushColor();
			Project.getCurrent().setPrimaryBrushColor(Color.WHITE);
		}
	}

	@Override
	public void updateAction() {
		String brush = Project.getCurrent().getBrushShape();
		Layer layer = Project.getCurrent().getImage().getActiveLayer();
		BufferedImage newData = layer.copyData();
		Graphics2D g = newData.createGraphics();

		g.setColor(Project.getCurrent().getPrimaryBrushColor());
		BasicStroke stroke = new BasicStroke(Project.getCurrent().getBrushSize());
		g.setStroke(stroke);

		int mx = Input.getMouseX();
		int my = Input.getMouseY();

		Graphics2D g2d = (Graphics2D) g;
		GeneralPath path = null;

		// figure out which brush is selected
		if (brush.contentEquals("star")) {
			// create shape
			Star temp = new Star(mx, my);
			path = temp.getPath();
			path.transform(AffineTransform.getTranslateInstance(-7, -7));
		} else if (brush.contentEquals("splash1")) {
			Splash temp = new Splash(15, 3, mx, my);
			path = temp.getPath();
			path.transform(AffineTransform.getTranslateInstance(-13, -13));
		} else if (brush.contentEquals("splash2")) {
			Splash temp = new Splash(10, 6, mx, my);
			path = temp.getPath();
			path.transform(AffineTransform.getTranslateInstance(-13, -13));
		} else if (brush.contentEquals("eraser")) {
			// the eraser is a white circle that will overlay over existing shapes
			Circle temp = new Circle(mx, my, 30);
			path = temp.getPath();
			path.transform(AffineTransform.getTranslateInstance(-13, -13));
		} else {
			System.out.println("Error Unknown brush name, using default");
			Star temp = new Star(mx, my);
			path = temp.getPath();
			path.transform(AffineTransform.getTranslateInstance(-7, -7));
		}
		g2d.fill(path);
		g.draw(finalStrokeShape);
		UpdateLayerDataCommand c = new UpdateLayerDataCommand(layer, newData);
		CommandList.getInstance().addCommand(c);
		g.dispose();
	}

	@Override
	public void finishAction() {
		String brush = Project.getCurrent().getBrushShape();
		// star-- if you just click it will draw one star at position
		if (brush.contentEquals("star")) {
			int mx = Input.getMouseX();
			int my = Input.getMouseY();
			Star temp = new Star(mx, my);
			GeneralPath path = temp.getPath();
			path.transform(AffineTransform.getTranslateInstance(-7, -7));
			finalStrokeShape.append(path, false);
			g.draw(finalStrokeShape);
		} else if (brush.contentEquals("eraser")) {
			// sets color back to what it was if you use eraser
			Project.getCurrent().setPrimaryBrushColor(eraserOriginalColor);
		}
		Layer layer = Project.getCurrent().getImage().getActiveLayer();
		BufferedImage newData = layer.copyData();
		Graphics2D g = newData.createGraphics();
		g.setColor(Project.getCurrent().getPrimaryBrushColor());
		BasicStroke stroke = new BasicStroke(Project.getCurrent().getBrushSize());
		g.setStroke(stroke);
		UpdateLayerDataCommand c = new UpdateLayerDataCommand(layer, newData);
		CommandList.getInstance().addCommand(c);
		g.dispose();

	}
}
