package pixelsmart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;

public class StencilTool implements Tool {
	
	private Path2D.Double finalStrokeShape;
	private Graphics2D g;
	Color eraserOriginalColor =null;
	@Override
	public void startAction() {

		finalStrokeShape = new Path2D.Double();
		Layer layer = Project.getCurrent().getImage().getActiveLayer();
		BufferedImage newData = layer.copyData();
		 g = newData.createGraphics();

		 
	}

	@Override
	public void updateAction() {
			Layer layer = Project.getCurrent().getImage().getActiveLayer();
			BufferedImage newData = layer.copyData();
			Graphics2D g = newData.createGraphics();

			g.setColor(Project.getCurrent().getPrimaryBrushColor());
			BasicStroke stroke = new BasicStroke(Project.getCurrent().getBrushSize());
			g.setStroke(stroke);
			
			int mx = Input.getMouseX();
			int my = Input.getMouseY();
			
			
			ShapeFactory shapeFactory = new ShapeFactory();
			GeneralPath  path = shapeFactory.getShape(Project.getCurrent().getBrushMode(), mx, my).getPath();
			finalStrokeShape.append(path, false); // needs pathIteartor 
			

		}
	@Override
	public void finishAction() {
		Layer layer = Image.getCurrent().getActiveLayer();
		BufferedImage newData = layer.copyData();
		Graphics2D g = newData.createGraphics();

		g.setColor(Project.getCurrent().getPrimaryBrushColor());

		g.fill(finalStrokeShape);
		
		g.draw(finalStrokeShape);

		UpdateLayerDataCommand c = new UpdateLayerDataCommand(layer, newData);
		CommandList.getInstance().addCommand(c);

		g.dispose();

	}
}
