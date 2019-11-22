import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;

public class Toolbar extends JFrame {

    /*
    public static void main(String[] args) {
        Toolbar t = new Toolbar();
        t.setVisible(true);
    }
    */

    JToolBar toolbar;
    JButton pencil;
    JButton eraser;
    JButton fill;
    JButton type;
    JButton zoom;
    JButton square_select;
    JButton circle_select;

    public Toolbar() {
        setSize(400, 400);
        setTitle("Toolbar test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon pencilImage = new ImageIcon("C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\pencil x2.png");
        ImageIcon eraserImage = new ImageIcon("C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\eraser x2.png");
        ImageIcon fillImage = new ImageIcon("C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\Fill x2.png");
        ImageIcon typeImage = new ImageIcon("C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\Type x2.png");
        ImageIcon zoomImage = new ImageIcon("C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\Zoom x2.png");
        ImageIcon square_selectImage = new ImageIcon("C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\Select x2.png");
        ImageIcon circle_selectImage = new ImageIcon("C:\\Users\\Tyler\\Desktop\\PixelSmart\\src\\pixelsmart\\ui\\images\\Select_Circle x2.png");

        toolbar = new JToolBar();
        pencil = new JButton(pencilImage);
        eraser = new JButton(eraserImage);
        fill = new JButton(fillImage);
        type = new JButton(typeImage);
        zoom = new JButton(zoomImage);
        square_select = new JButton(square_selectImage);
        circle_select = new JButton(circle_selectImage);


        toolbar.add(pencil);
        toolbar.add(eraser);
        toolbar.add(fill);
        toolbar.add(type);
        toolbar.add(zoom);
        toolbar.add(square_select);
        toolbar.add(circle_select);
        // toolbar.setFloatable(false); // REMOVE COMMENT IF YOU DON'T WANT TOOLBAR TO BE MOVEABLE
        add(toolbar, BorderLayout.NORTH);
    }
}