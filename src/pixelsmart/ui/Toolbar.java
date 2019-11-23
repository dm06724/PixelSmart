package pixelsmart.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar {
    /**
     *
     */
    private static final long serialVersionUID = -833808924744341165L;
    JToolBar toolbar;
    JButton pencil;
    JButton eraser;
    JButton fill;
    JButton type;
    JButton zoom;
    JButton square_select;
    JButton circle_select; // TODO lasso, not just circle

    public Toolbar() {

        // TODO put images in /res/images
        ImageIcon pencilImage = new ImageIcon(
                "C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\pencil x2.png");
        ImageIcon eraserImage = new ImageIcon(
                "C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\eraser x2.png");
        ImageIcon fillImage = new ImageIcon(
                "C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\Fill x2.png");
        ImageIcon typeImage = new ImageIcon(
                "C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\Type x2.png");
        ImageIcon zoomImage = new ImageIcon(
                "C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\Zoom x2.png");
        ImageIcon square_selectImage = new ImageIcon(
                "C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\Select x2.png");
        ImageIcon circle_selectImage = new ImageIcon(
                "C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\Select_Circle x2.png");

        pencil = new JButton(pencilImage);
        eraser = new JButton(eraserImage);
        fill = new JButton(fillImage);
        type = new JButton(typeImage);
        zoom = new JButton(zoomImage);
        square_select = new JButton(square_selectImage);
        circle_select = new JButton(circle_selectImage);

        this.add(pencil);
        this.add(eraser);
        this.add(fill);
        this.add(type);
        this.add(zoom);
        this.add(square_select);
        this.add(circle_select);
    }
}