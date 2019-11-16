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
import pixelsmart.image.Layer;

public class LayerMenu extends JMenu {
    private static final long serialVersionUID = -5953911805394394364L;

    public LayerMenu() {
        super("Layer");

        // New Layer
        JMenuItem addNewLayer = new JMenuItem("New Layer");
        addNewLayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));

        addNewLayer.addActionListener(e -> {
            Image img = Project.getCurrent().getImage();
            if (img == null) {
                return;
            }

            String name = JOptionPane.showInputDialog(MainWindow.getInstance(), "Enter a layer name:");

            if (name == null || name.isEmpty()) {
                return;
            }

            img.addLayer(name);
        });

        // Load Layer
        JMenuItem loadLayerButton = new JMenuItem("Load");
        loadLayerButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));

        loadLayerButton.addActionListener(e -> {
            Image img = Project.getCurrent().getImage();
            BufferedImage data = ImageExporter.loadWithDialog();
            img.addLayer(img.layerCount() + "", data);
        });

        // Delete Active Layer
        JMenuItem deleteLayer = new JMenuItem("Remove Layer");
        deleteLayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));

        deleteLayer.addActionListener(e -> {
            Project.getCurrent().getImage().getActiveLayer().delete();
        });

        // Set active layer as base layer
        JMenuItem setActiveLayerBase = new JMenuItem("Set Active Layer as Base Layer");

        setActiveLayerBase.addActionListener(e -> {
            if (Project.getCurrent() == null){
                return;
            }
            
            Image img = Project.getCurrent().getImage();
            Layer activeLayer = img.getActiveLayer();

            img.setBaseLayer(activeLayer);
        });

        this.add(addNewLayer);
        this.add(deleteLayer);
        this.add(loadLayerButton);
        this.add(new JSeparator());
        this.add(setActiveLayerBase);
    }
}