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
        JButton circle_select;
        JButton lasso_select;
        JButton move;

        public Toolbar() {
                ImageIcon pencilImage = new ImageIcon("/res/images/pencil x2.png");
                ImageIcon eraserImage = new ImageIcon("/res/images/eraser x2.png");
                ImageIcon fillImage = new ImageIcon("/res/images/Fill x2.png");
                ImageIcon typeImage = new ImageIcon("/res/images/Type x2.png");
                ImageIcon zoomImage = new ImageIcon("/res/images/Zoom x2.png");
                ImageIcon square_selectImage = new ImageIcon("/res/images/Select x2.png");
                ImageIcon circle_selectImage = new ImageIcon("/res/images/Select_Circle x2.png");
                ImageIcon lasso_selectImage = new ImageIcon("/res/images/lasso x2.png");
                ImageIcon moveImage = new ImageIcon("/res/images/move x2.png");

                pencil = new JButton(pencilImage);
                eraser = new JButton(eraserImage);
                fill = new JButton(fillImage);
                type = new JButton(typeImage);
                zoom = new JButton(zoomImage);
                square_select = new JButton(square_selectImage);
                circle_select = new JButton(circle_selectImage);
                lasso_select = new JButton(lasso_selectImage);
                move = new JButton(moveImage);

                this.add(pencil);
                this.add(eraser);
                this.add(fill);
                this.add(type);
                this.add(zoom);
                this.add(square_select);
                this.add(circle_select);
                this.add(lasso_select);
                this.add(move);
        }
}