package pixelsmart.ui;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import pixelsmart.MainWindow;
import pixelsmart.Project;
import pixelsmart.image.Image;
import pixelsmart.image.ImageExporter;

public class LayerMenu extends JMenu {
    private static final long serialVersionUID = -5953911805394394364L;

    public LayerMenu() {
        super("Layer");

        /**
         * Add New Layer
         */
        JMenuItem addNewLayer = new JMenuItem("New Layer");
        addNewLayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK));

        addNewLayer.addActionListener(e -> {
            Image img = Project.getCurrent().getImage();
            if (img == null) {
                return;
            }

            String name = JOptionPane.showInputDialog(MainWindow.getInstance(), "Enter a layer name:");

            if (name == null) {
                return;
            }

            if (name.isEmpty()) {
                name = "Layer " + (img.layerCount() + 1);
            }

            img.addLayer(name);
        });

        /**
         * Load Layer From File
         */
        JMenuItem loadLayerButton = new JMenuItem("Load");
        loadLayerButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));

        loadLayerButton.addActionListener(e -> {
            Image img = Project.getCurrent().getImage();
            BufferedImage data = ImageExporter.loadWithDialog();

            if (data == null) {
                return;
            }

            img.addLayer(img.layerCount() + "", data);
        });

        /**
         * Delete Layer
         */
        JMenuItem deleteLayer = new JMenuItem("Remove Layer");
        deleteLayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));

        deleteLayer.addActionListener(e -> {
            if (Project.getCurrent() == null) {
                return;
            }
            Image image = Project.getCurrent().getImage();
            image.getActiveLayer().delete();
        });

        /**
         * Select Previous Layer
         */
        JMenuItem prevLayerButton = new JMenuItem("Previous Layer");
        prevLayerButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.CTRL_DOWN_MASK));

        prevLayerButton.addActionListener(e -> {
            if (Project.getCurrent() == null) {
                return;
            }
            Image image = Project.getCurrent().getImage();
            image.getActiveLayer().getPreviousLayer(true).setAsActive();
        });

        /**
         * Select Next Layer
         */
        JMenuItem nextLayerButton = new JMenuItem("Next Layer");
        nextLayerButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.CTRL_DOWN_MASK));

        nextLayerButton.addActionListener(e -> {
            if (Project.getCurrent() == null) {
                return;
            }
            Image image = Project.getCurrent().getImage();
            image.getActiveLayer().getNextLayer(true).setAsActive();
        });

        /**
         * Move Layer Up
         */
        JMenuItem shiftLayerUpButton = new JMenuItem("Shift Layer Up");
        shiftLayerUpButton.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK));

        shiftLayerUpButton.addActionListener(e -> {
            if (Project.getCurrent() == null) {
                return;
            }
            Image image = Project.getCurrent().getImage();
            image.getActiveLayer().moveUp();
        });

        /**
         * Move Layer Down
         */
        JMenuItem shiftLayerDownButton = new JMenuItem("Shift Layer Down");
        shiftLayerDownButton.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK));

        shiftLayerDownButton.addActionListener(e -> {
            if (Project.getCurrent() == null) {
                return;
            }
            Image image = Project.getCurrent().getImage();
            image.getActiveLayer().moveDown();
        });

        /**
         * Toggle Layer Visibility
         */
        JMenuItem toggleLayerVisibilityButton = new JMenuItem("Toggle Hidden");
        toggleLayerVisibilityButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));

        toggleLayerVisibilityButton.addActionListener(e -> {
            if (Project.getCurrent() == null) {
                return;
            }
            Image image = Project.getCurrent().getImage();
            image.getActiveLayer().toggleVisible();
        });

        /**
         * Set Active Layer as Base Layer
         */
        JMenuItem setActiveLayerBase = new JMenuItem("Set Active Layer as Base Layer");

        setActiveLayerBase.addActionListener(e -> {
            if (Project.getCurrent() == null) {
                return;
            }
            Image image = Project.getCurrent().getImage();
            image.getActiveLayer().setAsActive();
        });

        this.add(addNewLayer);
        this.add(deleteLayer);
        this.add(loadLayerButton);
        this.add(new JSeparator());
        this.add(prevLayerButton);
        this.add(nextLayerButton);
        this.add(new JSeparator());
        this.add(shiftLayerDownButton);
        this.add(shiftLayerUpButton);
        this.add(new JSeparator());
        this.add(toggleLayerVisibilityButton);
        this.add(new JSeparator());
        this.add(setActiveLayerBase);
    }
}