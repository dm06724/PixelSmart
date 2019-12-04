package pixelsmart.ui.menubar;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import pixelsmart.commands.CommandList;
import pixelsmart.image.Image;
import pixelsmart.ui.ImagePanel;
import pixelsmart.ui.MainWindow;
import pixelsmart.util.ImageExporter;

public class FileMenu extends JMenu {
    private static final long serialVersionUID = -9140684398793551793L;

    public FileMenu() {
        super("File");

        // New Project
        JMenuItem newImage = new JMenuItem("New Project");
        newImage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        newImage.addActionListener(e -> {
            if (ImagePanel.get().getImage() != null) {
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
                    promptSaveProject();
                    break;
                }
            }
            JTextField widthInput = new JTextField();
            JTextField heightInput = new JTextField();
            
            JLabel widthLabel = new JLabel("Width");
            JLabel heightLabel = new JLabel("Height");
            
            widthInput.setToolTipText("Width");
            widthInput.setText("64");
            heightInput.setText("64");

            final JComponent[] inputs = new JComponent[] { widthLabel, widthInput, heightLabel, heightInput };

            int result = JOptionPane.showConfirmDialog(MainWindow.getInstance(), inputs, "Create New Project",
                    JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    int width = Integer.parseInt(widthInput.getText());
                    int height = Integer.parseInt(heightInput.getText());

                    CommandList.getInstance().clear();
                    ImagePanel.get().setImage(new Image(width, height));
                } catch (Exception ex) {
                    CommandList.getInstance().clear();
                    ImagePanel.get().setImage(new Image(64, 64));
                }
            } else {
                return;
            }
        });

        // Open Project (.ps)
        JMenuItem open = new JMenuItem("Open Project");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        open.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            int result = fileChooser.showOpenDialog(MainWindow.getInstance());
            if (result == JFileChooser.APPROVE_OPTION) {
                // File file = fileChooser.getSelectedFile();
                // TODO SWITCH TO IMAGE
                // ToolManager.load(file);
            	if (ImagePanel.get().getImage() != null) {
                    // Prompt to save current project
                    result = JOptionPane.showOptionDialog(MainWindow.getInstance(),
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
                        promptSaveProject();
                        break;
                    }
                }
            	
            	Image loadedImage = ImageExporter.loadPSF(fileChooser.getSelectedFile());
            	
            	if(loadedImage!=null)
            	{
            		ImagePanel.get().setImage(loadedImage);
            	}
            	else
            	{
            		JOptionPane.showMessageDialog(null, "ERROR LOADING .PSF File", "ERROR", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });

        // Save
        JMenuItem save = new JMenuItem("Save Project");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        save.addActionListener(e -> {
            promptSaveProject();
        });

        // Export
        JMenuItem export = new JMenuItem("Export Image");
        export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK | KeyEvent.ALT_DOWN_MASK));
        export.addActionListener(e -> {
            Image image = ImagePanel.get().getImage();
            if (image == null) {
                return;
            }
            ImageExporter.exportWithDialog(image.getAggregatedData(), "png");
        });

        // Close
        JMenuItem close = new JMenuItem("Close Project");
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.SHIFT_DOWN_MASK));
        close.addActionListener(e -> {
            ImagePanel.get().setImage(null);
        });

        // Exit
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> {
            if (ImagePanel.get().getImage() != null) {

            }
            System.exit(0);
        });

        this.add(newImage);
        this.add(open);
        this.add(save);
        this.add(close);
        this.add(new JSeparator());
        this.add(export);
        this.add(new JSeparator());
        this.add(exit);
    }

    private void promptSaveProject() {
        Image image = ImagePanel.get().getImage();
        if (image == null) {
            return;
        }

        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showSaveDialog(MainWindow.getInstance());

        if (result == JFileChooser.APPROVE_OPTION) {
            ImageExporter.savePSF(image, fileChooser.getSelectedFile());
        }
    }
}