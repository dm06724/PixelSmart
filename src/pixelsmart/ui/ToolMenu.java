package pixelsmart.ui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import pixelsmart.tools.ColorPickerTool;
import pixelsmart.tools.EraserTool;
import pixelsmart.tools.LineTool;
import pixelsmart.tools.MoveTool;
import pixelsmart.tools.PaintBucketTool;
import pixelsmart.tools.PencilTool;
import pixelsmart.tools.SelectTool;
import pixelsmart.tools.StencilTool;
import pixelsmart.tools.ToolManager;
import pixelsmart.tools.ZoomTool;

public class ToolMenu extends JMenu /* Will change to JToolBar - Tyler */ {
    private static final long serialVersionUID = 3180545437387906156L;

    public ToolMenu() {
        super("Tools");

        /**
         * Pencil Tool
         */
        JMenuItem pencilTool = new JMenuItem("Pencil");
        pencilTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.SHIFT_DOWN_MASK));
        pencilTool.addActionListener(e -> {
            ToolManager.getInstance().setTool(new PencilTool());
        });

        /**
         * Color Picker Tool
         */
        JMenuItem colorPickerTool = new JMenuItem("Color Picker");
        colorPickerTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.SHIFT_DOWN_MASK));
        colorPickerTool.addActionListener(e -> {
            ToolManager.getInstance().setTool(new ColorPickerTool());
        });

        /**
         * Eraser Tool
         */
        JMenuItem eraserTool = new JMenuItem("Eraser");
        eraserTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.SHIFT_DOWN_MASK));
        eraserTool.addActionListener(e -> {
            ToolManager.getInstance().setTool(new EraserTool());
        });

        /**
         * Paint Bucket Tool
         */
        JMenuItem paintBucketTool = new JMenuItem("Paint Bucket");
        paintBucketTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.SHIFT_DOWN_MASK));
        paintBucketTool.addActionListener(e -> {
            ToolManager.getInstance().setTool(new PaintBucketTool());
        });

        /**
         * Move Tool
         */
        JMenuItem moveTool = new JMenuItem("Move");
        moveTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.SHIFT_DOWN_MASK));
        moveTool.addActionListener(e -> {
            ToolManager.getInstance().setTool(new MoveTool());
        });

        /**
         * Line Tool
         */
        JMenuItem lineTool = new JMenuItem("Line");
        lineTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.SHIFT_DOWN_MASK));
        lineTool.addActionListener(e -> {
            ToolManager.getInstance().setTool(new LineTool());
        });

        /**
         * Select Tool
         */
        JMenuItem selectTool = new JMenuItem("Select");
        selectTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.SHIFT_DOWN_MASK));
        selectTool.addActionListener(e -> {
            ToolManager.getInstance().setTool(new SelectTool());
        });

        /**
         * Stencil Tool
         */
        JMenuItem stencilTool = new JMenuItem("Stencil");
        stencilTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.SHIFT_DOWN_MASK));
        stencilTool.addActionListener(e -> {
            ToolManager.getInstance().setTool(new StencilTool());
        });

        /**
         * Zoom Tool
         */
        JMenuItem zoomTool = new JMenuItem("Zoom");
        zoomTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.SHIFT_DOWN_MASK));
        zoomTool.addActionListener(e -> {
            ToolManager.getInstance().setTool(new ZoomTool());
        });

        this.add(colorPickerTool);
        this.add(eraserTool);
        this.add(lineTool);
        this.add(moveTool);
        this.add(paintBucketTool);
        this.add(pencilTool);
        this.add(selectTool);
        this.add(stencilTool);
        this.add(zoomTool);
    }
}