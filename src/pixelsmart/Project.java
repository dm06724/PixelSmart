package pixelsmart;

import javax.swing.JOptionPane;

public class Project {
    private static Project instance;
    private Image image;
    // TODO maybe add command list here?

    private Project(int imageWidth, int imageHeight) {
        this.image = new Image(imageWidth, imageHeight);
    }

    public static Project getCurrent() {
        return instance;
    }

    public static synchronized Project createNew(int imageWidth, int imageHeight) {
        if (getCurrent() != null) {
            // Prompt to save current project
            int result = JOptionPane.showOptionDialog(null, "Do you want to save your current project?",
                    "Save Current Project?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
                    null);
            switch (result) {
            default:
            case JOptionPane.CLOSED_OPTION:
            case JOptionPane.CANCEL_OPTION:
                return getCurrent();
            case JOptionPane.YES_OPTION:
                getCurrent().save();
                break;
            case JOptionPane.NO_OPTION:
                break;
            }
        }
        return instance = new Project(imageWidth, imageHeight);
    }

    public Image getImage() {
        return this.image;
    }

    public boolean save() {
        // TODO serialize all project data somehow
        // layers, brush size, brush colors, etc.

        // JFileChooser fileChooser = new JFileChooser();

        // int result = fileChooser.showSaveDialog(null);
        // if (result == JFileChooser.APPROVE_OPTION) {
        // File file = fileChooser.getSelectedFile();

        // return true;
        // } else {
        // return false;
        // }
        return false;
    }
}