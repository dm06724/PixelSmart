package pixelsmart;

public class ShapeFactory {
	
	   public Shape getShape(String shapeType, int x, int y){
		   
		      if(shapeType == null){
		         return null;
		      }		

		      //modify after Tyler adds slider for size

		      if(shapeType.equalsIgnoreCase("splash1")){
		         return new SplashShape(10,5,x,y);
		         
		      } else if(shapeType.equalsIgnoreCase("splash2")){
		    	  return new SplashShape(5,10,x,y);
		         
		      } else if(shapeType.equalsIgnoreCase("star")){
		    	  return new StarShape(x,y);
		      } else if(shapeType.equalsIgnoreCase("circle1")){
		    	  return new CircleShape(x,y,15);
		      } else if(shapeType.equalsIgnoreCase("circle2")){
		    	  return new CircleShape(x,y,13);
		      } else if(shapeType.equalsIgnoreCase("circle3")){
		    	  return new CircleShape(x,y,11);
		      } else if(shapeType.equalsIgnoreCase("circle4")){
		    	  return new CircleShape(x,y,9);
		      } else if(shapeType.equalsIgnoreCase("circle5")){
		    	  return new CircleShape(x,y,7);
		      } else if(shapeType.equalsIgnoreCase("circle6")){
		    	  return new CircleShape(x,y,5);
		      }
		      
		      return null;
		   }
	
	

}
