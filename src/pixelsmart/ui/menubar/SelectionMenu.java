package pixelsmart.ui.menubar;

import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import pixelsmart.commands.CommandList;
import pixelsmart.commands.SetClipShapeCommand;
import pixelsmart.ui.ImagePanel;

public class SelectionMenu extends JMenu {
    private static final long serialVersionUID = 8949390567052355433L;

    public SelectionMenu() {
        super("Selection");

        JMenuItem deselect = new JMenuItem("Deselect");
        deselect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
        deselect.addActionListener(e -> {
            CommandList.getInstance().addCommand(new SetClipShapeCommand(null));
        });

        // JMenuItem scaleUp = new JMenuItem("Scale Up");
        // // scaleUp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
        // // KeyEvent.CTRL_DOWN_MASK));
        // scaleUp.addActionListener(e -> {
        //     Shape clip = ImagePanel.get().getClip(ImagePanel.RELATIVE_TO_IMAGE);
        //     if (clip == null) {
        //         return;
        //     }
        //     AffineTransform scale = AffineTransform.getScaleInstance(1.1, 1.1);
        //     CommandList.getInstance().addCommand(new SetClipShapeCommand(scale.createTransformedShape(clip)));
        // });

        // JMenuItem scaleDown = new JMenuItem("Scale Down");
        // // scaleDown.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
        // // KeyEvent.CTRL_DOWN_MASK));
        // scaleDown.addActionListener(e -> {
        //     Shape clip = ImagePanel.get().getClip(ImagePanel.RELATIVE_TO_IMAGE);
        //     if (clip == null) {
        //         return;
        //     }
        //     AffineTransform scale = AffineTransform.getScaleInstance(0.9, 0.9);
        //     CommandList.getInstance().addCommand(new SetClipShapeCommand(scale.createTransformedShape(clip)));
        // });

        this.add(deselect);
        this.add(new JSeparator());
        //this.add(scaleUp);
        //this.add(scaleDown);
    }
}