package pixelsmart;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageExporter {
    public static void export(BufferedImage image, String format, File file) {
        if (image == null)
            return;

        try {
            ImageIO.write(image, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}