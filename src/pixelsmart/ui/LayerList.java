package pixelsmart.ui;

import java.awt.Dimension;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import pixelsmart.events.EventListener;
import pixelsmart.image.Image;
import pixelsmart.image.Layer;

public class LayerList extends JList<Layer> {
    private static final long serialVersionUID = -5953911805394394364L;

    private final EventListener<Image> imageChangedListener;
    private final EventListener<Layer> activeLayerChangedListener;

    public LayerList(ImagePanel panel) {
        TitledBorder border = new TitledBorder("Layers");
        border.setTitlePosition(TitledBorder.TOP);
        border.setTitleJustification(TitledBorder.CENTER);

        this.setBorder(border);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Dimension d = this.getPreferredSize();
        d.width = 100;
        this.setPreferredSize(d);

        this.addListSelectionListener(e -> {
            Layer selected = this.getSelectedValue();
            Layer current = ImagePanel.get().getActiveLayer();

            if (selected == null || selected == current) {
                return;
            }

            ImagePanel.get().setActiveLayer(selected);
        });

        imageChangedListener = image -> updateListeners(image);
        activeLayerChangedListener = layer -> updateSelection();

        panel.addImageChangedListener(imageChangedListener);
        panel.addActiveLayerChangedListener(activeLayerChangedListener);
    }

    public void updateList(Image image) {
        if (image == null) {
            this.setListData(new Layer[] {});
            return;
        }

        Vector<Layer> layers = new Vector<>(image.getLayers());
        Collections.reverse(layers);

        this.setListData(layers);
        this.updateSelection();
    }

    private void updateListeners(Image image) {
        image.addLayersModifiedListener(i -> updateList(i));
        updateList(image);
    }

    public void updateSelection() {
        this.setSelectedValue(ImagePanel.get().getActiveLayer(), true);
    }
}