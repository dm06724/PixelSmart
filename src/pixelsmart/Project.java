package pixelsmart;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Project {
    private static Project current;
    private Image image;

    public static Project getCurrent() {
        return current;
    }

    public Image getImage() {
        return this.image;
    }

    public void saveAs(String path) {
        // TODO Save entire project including layers, current brush size, brush color, etc.
    }
}