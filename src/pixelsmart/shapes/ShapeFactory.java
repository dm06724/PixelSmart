package pixelsmart.shapes;

public class ShapeFactory {

	public Shape getShape(String shapeType, int x, int y) {

		if (shapeType == null) {
			return null;
		}

		// modify after Tyler adds slider for size

		if (shapeType.equalsIgnoreCase("splash1")) {
			return new Splash(10, 5);

		} else if (shapeType.equalsIgnoreCase("splash2")) {
			return new Splash(5, 10);

		} else if (shapeType.equalsIgnoreCase("star")) {
			return new Star();
		} else if (shapeType.equalsIgnoreCase("circle1")) {
			return new Circle(15);
		} else if (shapeType.equalsIgnoreCase("circle2")) {
			return new Circle(13);
		} else if (shapeType.equalsIgnoreCase("circle3")) {
			return new Circle(11);
		} else if (shapeType.equalsIgnoreCase("circle4")) {
			return new Circle(9);
		} else if (shapeType.equalsIgnoreCase("circle5")) {
			return new Circle(7);
		} else if (shapeType.equalsIgnoreCase("circle6")) {
			return new Circle(5);
		}

		return null;
	}

}
