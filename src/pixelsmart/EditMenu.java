package pixelsmart;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class EditMenu extends JMenu {
    private static final long serialVersionUID = 3180545437387906156L;

    public EditMenu() {
        super("Edit");

        JMenuItem undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        undo.addActionListener(e -> {
            CommandList.getInstance().undo();
        });

        JMenuItem redo = new JMenuItem("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
        redo.addActionListener(e -> {
            CommandList.getInstance().redo();
        });

        this.add(undo);
        this.add(redo);
    }
}