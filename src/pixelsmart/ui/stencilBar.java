package pixelsmart.ui;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import pixelsmart.tools.*;

public class stencilBar extends JToolBar {

        JButton test;
        JButton test2;
        JButton test3;

        public stencilBar() {
                ImageIcon testImage = new ImageIcon("res/images/star x2.png");
                ImageIcon test2Image = new ImageIcon("res/images/pencil x2.png");
                ImageIcon test3Image = new ImageIcon("res/images/pencil x2.png");
                
                test = new JButton(testImage);
                test2 = new JButton(test2Image);
                test3 = new JButton(test3Image);

                test.addActionListener(e -> {
                        ToolManager.getInstance().setTool(new StencilTool());
                });
                test2.addActionListener(e -> {
                        ToolManager.getInstance().setTool(new EraserTool());
                });
                test3.addActionListener(e -> {
                        ToolManager.getInstance().setTool(new PaintBucketTool());
                });
               
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                this.add(test);
                this.add(test2);
                this.add(test3);
        }
}