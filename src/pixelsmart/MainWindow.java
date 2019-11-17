package pixelsmart;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import pixelsmart.ui.EditMenu;
import pixelsmart.ui.FileMenu;
import pixelsmart.ui.ImagePanel;
import pixelsmart.ui.LayerList;
import pixelsmart.ui.LayerMenu;
import pixelsmart.ui.SliderWithText;
import pixelsmart.ui.ToolMenu;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -2835920738241308199L;
	private JPanel contentPane;
	private ImagePanel imagePanel;
	private static MainWindow currentWindow;

	/**
	 * Create the frame.
	 */
	private MainWindow() {
		this.setTitle("PixelSmart");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 800);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);

		imagePanel = new ImagePanel();

		JToolBar attributeToolbar = new JToolBar("Tools");

		JButton colorWheelButton = new JButton();
		colorWheelButton.addActionListener(e -> {
			if (Project.getCurrent() == null) {
				return;
			}
			Color color = JColorChooser.showDialog(null, "Select Color", Project.getCurrent().getPrimaryBrushColor());
			colorWheelButton.setBackground(color);
			Project.getCurrent().setPrimaryBrushColor(color);
		});

		attributeToolbar.add(new JLabel("Color"));
		attributeToolbar.add(colorWheelButton);
		attributeToolbar.add(new SliderWithText());

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new FileMenu());
		menuBar.add(new EditMenu());
		menuBar.add(new LayerMenu());
		menuBar.add(new ToolMenu());

		this.setJMenuBar(menuBar);

		LayerList layerList = new LayerList();

		contentPane.add(attributeToolbar, BorderLayout.SOUTH);
		contentPane.add(layerList, BorderLayout.EAST);
		contentPane.add(imagePanel, BorderLayout.CENTER);

		imagePanel.addMouseMotionListener(Input.getInstance());
		imagePanel.addMouseListener(Input.getInstance());
		imagePanel.addMouseWheelListener(Input.getInstance());
	}

	public static synchronized MainWindow getInstance() {
		if (currentWindow == null) {
			currentWindow = new MainWindow();
		}
		return currentWindow;
	}

	protected void run() {
		Input.getInstance().update();
		while (running()) {
			// Update
			Input.getInstance().update();

			if (Project.getCurrent() != null) {
				Project.getCurrent().update();
			}

			// Render
			imagePanel.repaint();

			// Sleep
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public ImagePanel getPanel() {
		return imagePanel;
	}

	private boolean running() {
		return this.isDisplayable();
	}

}
