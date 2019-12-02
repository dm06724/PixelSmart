package pixelsmart.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import pixelsmart.image.Image;
import pixelsmart.image.Layer;
import pixelsmart.ui.ImagePanel;
import pixelsmart.ui.MainWindow;

public class ImageExporter {

	public static boolean exportWithDialog(BufferedImage image, String format) {
		JFileChooser fileChooser = new JFileChooser();

		int result = fileChooser.showSaveDialog(MainWindow.getInstance());
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			return export(image, format, file);
		}

		return false;
	}

	public static boolean export(BufferedImage image, String format, File file) {
		if (image == null)
			return false;
		try {
			return ImageIO.write(image, format, file);
		} catch (IOException e) {
			return false;
		}
	}

	public static BufferedImage load(File file) {
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Image loadPSF(File file)
	{
		Image img = null;
		try {
			FileReader fr = new FileReader(file);
			
			img = loadHeader(fr);
			if(img!=null)
			{
				loadLayers(img, fr);
			}
			fr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public static void savePSF(Image image, File file) {
		// everything will be in a try catch cause I don't want to
		// write a try catch for each write.
		try {
			FileWriter fw = new FileWriter(file);

			// save header
			String HeaderString = getHeaderString();
			String LayersString = getLayersString();

			fw.write(HeaderString);
			fw.write(LayersString);

			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Image loadHeader(FileReader fr)
	{
		try
		{
			//load header
			//check if valid file
			String testThing = "";
			testThing += (char)fr.read();
			testThing += (char)fr.read();
			testThing += (char)fr.read();
			
			if(!testThing.equalsIgnoreCase("psf"))
			{
				return null;
			}
	
			String widthVal = "";
			widthVal += (char)fr.read();
			widthVal += (char)fr.read();
			widthVal += (char)fr.read();
			widthVal += (char)fr.read();
			
			String heightVal = "";
			heightVal += (char)fr.read();
			heightVal += (char)fr.read();
			heightVal += (char)fr.read();
			heightVal += (char)fr.read();
			
			Image img = new Image(MathUtil.byteStringToInt(widthVal), MathUtil.byteStringToInt(heightVal));
			
			return img;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private static String getHeaderString() {
		// Format for header
		// psf - Identifier
		// width - 4 bytes
		// height - 4 bytes
		// layers - 4 bytes

		String k = "psf";
		Image img = ImagePanel.get().getImage();
		k += MathUtil.intToByteString(img.getWidth());
		k += MathUtil.intToByteString(img.getHeight());
		k += MathUtil.intToByteString(img.getLayers().size());

		return k;
	}

	private static void loadLayers(Image img, FileReader fr)
	{
		try
		{
			//note, even though the amount of layers is considered apart of the header,
			//it is here for convenience
			
			String layers = "";
			layers += (char)fr.read();
			layers += (char)fr.read();
			layers += (char)fr.read();
			layers += (char)fr.read();
			
			int addAmount = MathUtil.byteStringToInt(layers);
			for(int i=0; i<addAmount; i++)
			{
				//for now, skip the identifier for layers in a file
				fr.read();
				fr.read();
				String xString = "";
				xString += (char)fr.read();
				xString += (char)fr.read();
				xString += (char)fr.read();
				xString += (char)fr.read();
				int x = MathUtil.byteStringToInt(xString);
				
				String yString = "";
				yString += (char)fr.read();
				yString += (char)fr.read();
				yString += (char)fr.read();
				yString += (char)fr.read();
				int y = MathUtil.byteStringToInt(yString);
				
				String widString = "";
				widString += (char)fr.read();
				widString += (char)fr.read();
				widString += (char)fr.read();
				widString += (char)fr.read();
				int width = MathUtil.byteStringToInt(widString);
				
				String heiString = "";
				heiString += (char)fr.read();
				heiString += (char)fr.read();
				heiString += (char)fr.read();
				heiString += (char)fr.read();
				int height = MathUtil.byteStringToInt(heiString);
				
				String nameSizeString = "";
				nameSizeString += (char)fr.read();
				nameSizeString += (char)fr.read();
				nameSizeString += (char)fr.read();
				nameSizeString += (char)fr.read();
				int nameSize = MathUtil.byteStringToInt(nameSizeString);
				
				String layerName = "";
				for(int j=0; j<nameSize; j++)
				{
					layerName += (char)fr.read();
				}
				
				boolean isVis = ((char)fr.read() == '1');
				boolean isBase = ((char)fr.read() == '1');
				
				img.addLayer(layerName);
				Layer p = img.getLayerByIndex(i);
				p.setVisible(isVis);
				if(isBase)
					p.setAsBaseLayer();
				
				p.setX(x);
				p.setY(y);
				p.setWidth(width);
				p.setHeight(height);
				
				//skip the id for pixel values
				fr.read();
				fr.read();
				fr.read();
				for(int tempY = 0; tempY < height; tempY++)
				{
					for(int tempX = 0; tempX < width; tempX++)
					{
						String colorString = "";
						
						colorString += (char)fr.read();
						colorString += (char)fr.read();
						colorString += (char)fr.read();
						colorString += (char)fr.read();
						
						int colorVal = MathUtil.byteStringToInt(colorString);
						Color c = new Color(colorVal, true);
						p.setPixelColor(tempX, tempY, c);
					}
				}
					
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private static String getLayersString() {
		String k = "";
		Image img = ImagePanel.get().getImage();
		ArrayList<Layer> layers = img.getLayers();

		for (int i = 0; i < layers.size(); i++) {
			k += "la";
			k += MathUtil.intToByteString(layers.get(i).getX());
			k += MathUtil.intToByteString(layers.get(i).getY());
			k += MathUtil.intToByteString(layers.get(i).getWidth());
			k += MathUtil.intToByteString(layers.get(i).getHeight());
			k += MathUtil.intToByteString(layers.get(i).getName().length());
			k += layers.get(i).getName();
			k += layers.get(i).isVisible()==true ? (byte)1 : (byte)0;
			k += layers.get(i).isBaseLayer()==true ? (byte)1 : (byte)0;

			k += "pix";
			for (int y = 0; y < layers.get(i).getHeight(); y++) {
				for (int x = 0; x < layers.get(i).getWidth(); x++) {
					k += MathUtil.intToByteString(layers.get(i).getData().getRGB(x, y));
				}
			}
		}

		return k;
	}
}