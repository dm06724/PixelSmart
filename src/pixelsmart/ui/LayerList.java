package pixelsmart.ui;

import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import pixelsmart.Project;
import pixelsmart.image.Layer;

public class LayerList extends JList<Layer> {
    private static final long serialVersionUID = -5953911805394394364L;

    public static LayerList instance;

    public LayerList() {
        TitledBorder border = new TitledBorder("Layers");
        border.setTitlePosition(TitledBorder.TOP);
        border.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(border);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        instance = this;
        updateList();

        this.addListSelectionListener(e -> {
            Layer selected = this.getSelectedValue();
            Layer current = Project.getCurrent().getImage().getActiveLayer();
            if (selected != current) {
                Project.getCurrent().getImage().setActiveLayer(selected);
            }
        });
    }

    public void updateList() {
        if (Project.getCurrent() != null) {
            Vector<Layer> layers = new Vector<>(Project.getCurrent().getImage().getLayers());
            this.setListData(layers);
        }
    }
}