package pixelsmart.ui;

import java.util.Collections;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import java.awt.Dimension;
import pixelsmart.Project;
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
            Image image = Project.getCurrent().getImage();
            Layer selected = this.getSelectedValue();
            Layer current = image.getActiveLayer();

            if (selected == null || selected == current) {
                return;
            }

            selected.setAsActive();
        });

        Image.onImageChanged.addListener((image) -> updateList(image));
    }

    public void updateList(Image image) {
        if (Project.getCurrent() != null) {
            Vector<Layer> layers = new Vector<>(image.getLayers());
            Collections.reverse(layers);
            this.setListData(layers);
            this.setSelectedValue(image.getActiveLayer(), true);
        }
    }
}