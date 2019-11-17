package pixelsmart.ui;

import java.awt.event.KeyEvent;
import java.io.File;
import java.text.NumberFormat;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import pixelsmart.MainWindow;
import pixelsmart.Project;

public class FileMenu extends JMenu {
    private static final long serialVersionUID = -9140684398793551793L;

    public FileMenu() {
        super("File");

        // New Image
        JMenuItem newImage = new JMenuItem("New Project");
        newImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        newImage.addActionListener(e -> {
            if (Project.getCurrent() != null) {
                // Prompt to save current project
                int result = JOptionPane.showOptionDialog(MainWindow.getInstance(),
                        "Do you want to save your current project?", "Save Current Project?",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                switch (result) {
                default:
                case JOptionPane.CLOSED_OPTION:
                case JOptionPane.CANCEL_OPTION:
                    return;
                case JOptionPane.NO_OPTION:
                    break;
                case JOptionPane.YES_OPTION:
                    if (!Project.getCurrent().save(null))
                        return;
                    break;
                }
            }
            NumberFormat format = NumberFormat.getInstance();
            format.setParseIntegerOnly(true);
            JFormattedTextField widthInput = new JFormattedTextField(format);
            JFormattedTextField heightInput = new JFormattedTextField(format);
            widthInput.setText("64");
            heightInput.setText("64");

            final JComponent[] inputs = new JComponent[] { widthInput, heightInput };

            int result = JOptionPane.showConfirmDialog(null, inputs, "Create New Project", JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    int width = Integer.parseInt(widthInput.getText());
                    int height = Integer.parseInt(heightInput.getText());

                    Project.createNew(width, height);
                } catch (Exception ex) {
                    Project.createNew(64, 64);
                }
            } else {
                return;
            }
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
            if (Project.getCurrent() == null) {
                return;
            }
            Project.getCurrent().save(new File("test.ps"));
        });

        // Export
        JMenuItem export = new JMenuItem("Export");
        export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK | KeyEvent.ALT_DOWN_MASK));
        export.addActionListener(e -> {
            if (Project.getCurrent() == null) {
                return;
            }
            Project.getCurrent().getImage().export();
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