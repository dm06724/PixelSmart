package pixelsmart.ui.menubar;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import pixelsmart.commands.CommandList;

public class EditMenu extends JMenu {
    private static final long serialVersionUID = 3180545437387906156L;

    public EditMenu() {
        super("Edit");
        
        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
        copy.addActionListener(e -> {
            CommandList.getInstance().copy();
        });
        
        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        paste.addActionListener(e -> {
            CommandList.getInstance().paste();
        });
        
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

        this.add(copy);
        this.add(paste);
        this.add(undo);
        this.add(redo);
    }
}