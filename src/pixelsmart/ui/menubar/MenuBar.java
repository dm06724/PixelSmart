package pixelsmart.ui.menubar;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;

    public MenuBar() {
        this.add(new FileMenu());
        this.add(new EditMenu());
        this.add(new SelectionMenu());
        this.add(new LayerMenu());
        this.add(new ToolMenu());
    }
}