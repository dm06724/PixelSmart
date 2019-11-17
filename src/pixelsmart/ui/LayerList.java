package pixelsmart.ui;

import java.awt.Dimension;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import pixelsmart.image.Image;
import pixelsmart.image.Layer;

public class LayerList extends JList<Layer> {
    private static final long serialVersionUID = -5953911805394394364L;

    public LayerList() {
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

        Image.onImageChanged.addListener((image) -> updateList());
    }

    public void updateList() {
        if (ImagePanel.get().getImage() != null) {
            ImagePanel panel = ImagePanel.get();
            Vector<Layer> layers = new Vector<>(panel.getImage().getLayers());
            Collections.reverse(layers);
            this.setListData(layers);
            this.setSelectedValue(panel.getActiveLayer(), true);
        }
    }
}