package pixelsmart;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

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

    public static BufferedImage loadWithDialog(){
        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showOpenDialog(MainWindow.getInstance());
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return load(file);
        }

        return null;
    }

    public static BufferedImage load(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            return null;
        }
    }
}