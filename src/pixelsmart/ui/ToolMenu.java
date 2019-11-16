package pixelsmart.ui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import pixelsmart.Project;
import pixelsmart.tools.ColorPickerTool;
import pixelsmart.tools.EraserTool;
import pixelsmart.tools.PaintBucketTool;
import pixelsmart.tools.PencilTool;
import pixelsmart.tools.SelectTool;

public class ToolMenu extends JMenu /* Will change to JToolBar - Tyler */{
    private static final long serialVersionUID = 3180545437387906156L;

    public ToolMenu() {
        super("Tools");

        JMenuItem pencilTool = new JMenuItem("Pencil");
        // pencilTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
        // KeyEvent.CTRL_DOWN_MASK));
        pencilTool.addActionListener(e -> {
            Project.getCurrent().setTool(new PencilTool());
        });

        JMenuItem colorPickerTool = new JMenuItem("Color Picker");
        // colorpicker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
        // KeyEvent.CTRL_DOWN_MASK));
        colorPickerTool.addActionListener(e -> {
            Project.getCurrent().setTool(new ColorPickerTool());
        });

        JMenuItem selectTool = new JMenuItem("Select");
        // pencilTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
        // KeyEvent.CTRL_DOWN_MASK));
        selectTool.addActionListener(e -> {
            Project.getCurrent().setTool(new SelectTool());
        });

        JMenuItem eraserTool = new JMenuItem("Eraser");
        // colorpicker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
        // KeyEvent.CTRL_DOWN_MASK));
        eraserTool.addActionListener(e -> {
            Project.getCurrent().setTool(new EraserTool());
        });

        JMenuItem paintBucketTool = new JMenuItem("Paint Bucket");
        // colorpicker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
        // KeyEvent.CTRL_DOWN_MASK));
        paintBucketTool.addActionListener(e -> {
            Project.getCurrent().setTool(new PaintBucketTool());
        });

        this.add(pencilTool);
        this.add(colorPickerTool);
        this.add(eraserTool);
        this.add(selectTool);
        this.add(eraserTool);
        this.add(paintBucketTool);
    }
}