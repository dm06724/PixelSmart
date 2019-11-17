package pixelsmart.ui;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import pixelsmart.MainWindow;
import pixelsmart.image.Image;
import pixelsmart.image.ImageExporter;
import pixelsmart.image.Layer;

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
            Image img = ImagePanel.get().getImage();

            // If there is no image, return
            if (img == null) {
                return;
            }

            // Prompt user for layer name
            String name = JOptionPane.showInputDialog(MainWindow.getInstance(), "Enter a layer name:");

            // If they closed the window, return
            if (name == null) {
                return;
            }

            // If the name was empty, give it a default name
            if (name.isEmpty()) {
                name = "Layer " + (img.layerCount() + 1);
            }

            // Finally, add the layer
            img.addLayer(name);
        });

        /**
         * Load Layer From File
         */
        JMenuItem loadLayerButton = new JMenuItem("Load");
        loadLayerButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));

        loadLayerButton.addActionListener(e -> {
            Image img = ImagePanel.get().getImage();

            // If there is no image, return
            if (img == null) {
                return;
            }

            // Prompt user to pick a file
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(MainWindow.getInstance());

            // If we did not select a file, return
            if (result != JFileChooser.APPROVE_OPTION) {
                return;
            }

            // Load the buffered image from the chosen file
            File file = fileChooser.getSelectedFile();
            BufferedImage data = ImageExporter.load(file);

            // If the file was not an image, return
            if (data == null) {
                return;
            }

            // Finally, add the image to a new layer
            img.addLayer(file.getName(), data);
        });

        /**
         * Delete Layer
         */
        JMenuItem deleteLayer = new JMenuItem("Remove Layer");
        deleteLayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));

        deleteLayer.addActionListener(e -> {
            if (ImagePanel.get().getActiveLayer() == null) {
                return;
            }
            ImagePanel.get().getActiveLayer().delete();
        });

        /**
         * Select Previous Layer
         */
        JMenuItem prevLayerButton = new JMenuItem("Previous Layer");
        prevLayerButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.CTRL_DOWN_MASK));

        prevLayerButton.addActionListener(e -> {
            if (ImagePanel.get().getActiveLayer() == null) {
                return;
            }
            Layer layer = ImagePanel.get().getActiveLayer().getPreviousLayer(true);
            ImagePanel.get().setActiveLayer(layer);
        });

        /**
         * Select Next Layer
         */
        JMenuItem nextLayerButton = new JMenuItem("Next Layer");
        nextLayerButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.CTRL_DOWN_MASK));

        nextLayerButton.addActionListener(e -> {
            if (ImagePanel.get().getActiveLayer() == null) {
                return;
            }
            Layer layer = ImagePanel.get().getActiveLayer().getNextLayer(true);
            ImagePanel.get().setActiveLayer(layer);
        });

        /**
         * Move Layer Up
         */
        JMenuItem shiftLayerUpButton = new JMenuItem("Shift Layer Up");
        shiftLayerUpButton.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK));

        shiftLayerUpButton.addActionListener(e -> {
            if (ImagePanel.get().getActiveLayer() == null) {
                return;
            }
            ImagePanel.get().getActiveLayer().moveUp();
        });

        /**
         * Move Layer Down
         */
        JMenuItem shiftLayerDownButton = new JMenuItem("Shift Layer Down");
        shiftLayerDownButton.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK));

        shiftLayerDownButton.addActionListener(e -> {
            if (ImagePanel.get().getActiveLayer() == null) {
                return;
            }
            ImagePanel.get().getActiveLayer().moveDown();
        });

        /**
         * Toggle Layer Visibility
         */
        JMenuItem toggleLayerVisibilityButton = new JMenuItem("Toggle Hidden");
        toggleLayerVisibilityButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));

        toggleLayerVisibilityButton.addActionListener(e -> {
            if (ImagePanel.get().getImage() == null) {
                return;
            }
            ImagePanel.get().getActiveLayer().toggleVisible();
        });

        /**
         * Set Active Layer as Base Layer
         */
        JMenuItem setActiveLayerBase = new JMenuItem("Set Active Layer as Base Layer");

        setActiveLayerBase.addActionListener(e -> {
            if (ImagePanel.get().getActiveLayer() == null) {
                return;
            }
            ImagePanel.get().getActiveLayer().setAsBaseLayer();
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