package pixelsmart;

import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

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
            Layer current = Image.getCurrent().getActiveLayer();
            if (selected != current) {
                Image.getCurrent().setActiveLayer(selected);
            }
        });
    }

    public void updateList() {
        if (Image.getCurrent() != null) {
            Vector<Layer> layers = new Vector<>(Image.getCurrent().getLayers());
            this.setListData(layers);
        }
    }
}