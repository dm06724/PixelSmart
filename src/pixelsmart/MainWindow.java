package pixelsmart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

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
		this.setBounds(100, 100, 600, 450);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);

		imagePanel = new ImagePanel();

		JToolBar brushToolbar = new JToolBar("Brushes");
		JToolBar attributeToolbar = new JToolBar("Tools");

		JButton colorWheelButton = new JButton();
		colorWheelButton.addActionListener(e -> {
			Color color = JColorChooser.showDialog(null, "Select Color", Color.BLACK);
			colorWheelButton.setBackground(color);
			Input.color = color;
		});

		JButton createProjectButton = new JButton("New Project");
		createProjectButton.addActionListener(e -> Project.createNew(600, 450));

		JButton addLayerButton = new JButton("Add Layer");
		addLayerButton.addActionListener(e -> {
			Image image = Project.getCurrent().getImage();
			image.addLayer(image.layerCount() + "");
		});

		JLabel layerLabel = new JLabel("Base");

		JButton nextLayerButton = new JButton("Next Layer");
		nextLayerButton.addActionListener(e -> {
			Image img = Project.getCurrent().getImage();
			int index = img.getActiveLayer().getIndex();

			img.setActiveLayer((index + 1) % img.layerCount());
			layerLabel.setText(img.getActiveLayer().getName());
		});

		JButton loadLayerButton = new JButton("Load Layer");
		loadLayerButton.addActionListener(e -> {
			Image img = Project.getCurrent().getImage();
			BufferedImage data = ImageExporter.loadWithDialog();
			img.addLayer(img.layerCount() + "", data);
		});

		JButton exportButton = new JButton("Export");
		exportButton.addActionListener(e -> {
			Project.getCurrent().getImage().export();
		});

		attributeToolbar.add(new JLabel("Color"));
		attributeToolbar.add(colorWheelButton);
		attributeToolbar.add(createProjectButton);
		attributeToolbar.add(addLayerButton);
		attributeToolbar.add(nextLayerButton);
		attributeToolbar.add(layerLabel);
		attributeToolbar.add(exportButton);
		attributeToolbar.add(loadLayerButton);

		contentPane.add(attributeToolbar, BorderLayout.NORTH);
		contentPane.add(brushToolbar, BorderLayout.WEST);
		contentPane.add(imagePanel, BorderLayout.CENTER);

		imagePanel.addMouseMotionListener(Input.getInstance());
		imagePanel.addMouseListener(Input.getInstance());
	}

	public static synchronized MainWindow getInstance() {
		if (currentWindow == null) {
			currentWindow = new MainWindow();
		}
		return currentWindow;
	}

	public void run() {
		while (running()) {
			// Update
			CommandList.getInstance().update();

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
