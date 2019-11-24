package pixelsmart.util;

import java.awt.image.BufferedImage;
import java.io.File;
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

	public static void saveImage(Image image, File file) {
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
			k += layers.get(i).isVisible();
			k += layers.get(i).isBaseLayer();

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