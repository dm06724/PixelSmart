package pixelsmart;

import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

public class FileMenu extends JMenu {
    private static final long serialVersionUID = -9140684398793551793L;

    public FileMenu() {
        super("File");

        // New Image
        JMenuItem newImage = new JMenuItem("New Project");
        newImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        newImage.addActionListener(e -> {
            // TODO add width height menu
            Project.createNew(500, 500);
        });

        // Open Project
        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        open.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            int result = fileChooser.showSaveDialog(MainWindow.getInstance());
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                Project.load(file);
            }
        });

        // Save
        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        save.addActionListener(e -> {
            if (Image.getCurrent() == null) {
                return;
            }
            Project.getCurrent().save(new File("test.ps"));
        });

        // Export
        JMenuItem export = new JMenuItem("Export");
        export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK | KeyEvent.ALT_DOWN_MASK));
        export.addActionListener(e -> {
            if (Image.getCurrent() == null) {
                return;
            }
            Image.getCurrent().export();
        });

        // Close
        JMenuItem close = new JMenuItem("Close Image");
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.SHIFT_DOWN_MASK));
        close.addActionListener(e -> {

        });

        // Exit
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> {
            if (Project.getCurrent() != null) {
                Project.getCurrent().save(null);
            }
            System.exit(0);
        });

        this.add(newImage);
        this.add(open);
        this.add(save);
        this.add(close);
        this.add(export);
        this.add(new JSeparator());
        this.add(exit);
    }
}