package pixelsmart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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

	private CommandList commands = new CommandList();

	/**
	 * Create the frame.
	 */
	private MainWindow() {
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
		colorWheelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				Color color = JColorChooser.showDialog(imagePanel, "Select Color", Color.BLACK);
				colorWheelButton.setBackground(color);
			}
		});

		attributeToolbar.add(new JLabel("Color"));
		attributeToolbar.add(colorWheelButton);

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

	public void appLoop() {

		while (running()) {
			// Update
			commands.update();

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
