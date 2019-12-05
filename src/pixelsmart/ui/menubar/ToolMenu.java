package pixelsmart.ui.menubar;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import pixelsmart.tools.BoxSelectTool;
import pixelsmart.tools.ColorPickerTool;
import pixelsmart.tools.EraserTool;
import pixelsmart.tools.LassoTool;
import pixelsmart.tools.LineTool;
import pixelsmart.tools.MoveTool;
import pixelsmart.tools.PaintBucketTool;
import pixelsmart.tools.PencilTool;
import pixelsmart.tools.StencilTool;
import pixelsmart.tools.ToolManager;
import pixelsmart.tools.ZoomTool;

public class ToolMenu extends JMenu{
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
         * Box Select Tool
         */
        JMenuItem boxSelectTool = new JMenuItem("Box Select");
        boxSelectTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.SHIFT_DOWN_MASK));
        boxSelectTool.addActionListener(e -> {
            ToolManager.getInstance().setTool(new BoxSelectTool());
        });

        /**
         * Box Select Tool
         */
        JMenuItem lassoSelectTool = new JMenuItem("Lasso Select");
        lassoSelectTool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.SHIFT_DOWN_MASK));
        lassoSelectTool.addActionListener(e -> {
            ToolManager.getInstance().setTool(new LassoTool());
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
        this.add(boxSelectTool);
        this.add(lassoSelectTool);
        this.add(stencilTool);
        this.add(zoomTool);
    }
}