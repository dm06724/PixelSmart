package pixelsmart.ui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import pixelsmart.Project;
import pixelsmart.tools.ColorPickerTool;
import pixelsmart.tools.EraserTool;
import pixelsmart.tools.LineTool;
import pixelsmart.tools.MoveTool;
import pixelsmart.tools.PaintBucketTool;
import pixelsmart.tools.PencilTool;
import pixelsmart.tools.SelectTool;
import pixelsmart.tools.StencilTool;
import pixelsmart.tools.ZoomTool;

public class ToolMenu extends JMenu /* Will change to JToolBar - Tyler */ {
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

        JMenuItem moveTool = new JMenuItem("Move");
        // colorpicker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
        // KeyEvent.CTRL_DOWN_MASK));
        moveTool.addActionListener(e -> {
            Project.getCurrent().setTool(new MoveTool());
        });

        JMenuItem zoomTool = new JMenuItem("Zoom");
        // colorpicker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
        // KeyEvent.CTRL_DOWN_MASK));
        zoomTool.addActionListener(e -> {
            Project.getCurrent().setTool(new ZoomTool());
        });

        JMenuItem lineTool = new JMenuItem("Line");
        // colorpicker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
        // KeyEvent.CTRL_DOWN_MASK));
        lineTool.addActionListener(e -> {
            Project.getCurrent().setTool(new LineTool());
        });

        JMenuItem stencilTool = new JMenuItem("Stencil");
        // colorpicker.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
        // KeyEvent.CTRL_DOWN_MASK));
        stencilTool.addActionListener(e -> {
            Project.getCurrent().setTool(new StencilTool());
        });

        this.add(colorPickerTool);
        this.add(eraserTool);
        this.add(lineTool);
        this.add(moveTool);
        this.add(paintBucketTool);
        this.add(pencilTool);
        this.add(selectTool);
        this.add(stencilTool);
        this.add(eraserTool);
    }
}