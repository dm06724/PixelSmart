package pixelsmart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
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

		
		//////////////////////////////////////////////////////////////////
		
		JToolBar brushToolbar = new JToolBar("Brushes");


		// adding a new Jpanel for the title
		JPanel brushTitle =  new JPanel();
		// setting layout to gridlayout this will center the title
		brushTitle.setLayout( new GridBagLayout() );
		JLabel brushLabel =new JLabel("Brushes");
		brushTitle.add(brushLabel, new GridBagConstraints());
		// forcing layout to be particular size
		brushTitle.setMinimumSize(new Dimension(70, 20));
		brushTitle.setMaximumSize(new Dimension(70, 20));
		brushTitle.setPreferredSize(new Dimension(70, 20));
		// adding it to toolbar
		brushToolbar.add(brushTitle);

		// adding a new Jpanel for the brushes
		JPanel brushes =  new JPanel();
		// removing border, to improves visuals
		brushToolbar.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
		// setting the grid
		brushes.setLayout(new GridLayout(6, 3));
		// forcing layout to be particular size
		brushes.setMaximumSize(new Dimension(55, 160));
		brushes.setMinimumSize(new Dimension(55, 160));
		brushes.setPreferredSize(new Dimension(55, 160));

		// here we are creating the buttons
		JButton btn1 = new JButton();
		// removing border, to improves visuals
		btn1.setMargin(new Insets(0,0,0,0));
		// adding shape to the button
		btn1.add(new Circle(3,3,15));
		
		JButton btn2 = new JButton();
		btn2.setMargin(new Insets(0,0,0,0));
		btn2.add(new Circle(4,4,13));
		
		JButton btn3 = new JButton();
		btn3.setMargin(new Insets(0,0,0,0));
		btn3.add(new Circle(5,5,11));

		JButton btn4 = new JButton();
		btn4.setMargin(new Insets(0,0,0,0));
		btn4.add(new Circle(6,6,9));
		
		JButton btn5 = new JButton();
		btn5.setMargin(new Insets(0,0,0,0));
		btn5.add(new Circle(7,7,7));
		
		JButton btn6 = new JButton();
		btn6.setMargin(new Insets(0,0,0,0));
		btn6.add(new Circle(8,8,5));
		
		JButton btn7 = new JButton();
		btn7.setMargin(new Insets(0,0,0,0));
		btn7.add(new Splash(15,3,0,0));
		
		JButton btn8 = new JButton();
		btn8.setMargin(new Insets(0,0,0,0));
		btn8.add(new Splash(10,6,0,0));
		
		JButton btn9 = new JButton();
		btn9.setMargin(new Insets(0,0,0,0));
		btn9.add(new Star(2,2));

		// adding all the buttons to the brushes layout
		brushes.add(btn1);
		brushes.add(btn2);
		brushes.add(btn3);
		brushes.add(btn4);
		brushes.add(btn5);
		brushes.add(btn6);
		brushes.add(btn7);
		brushes.add(btn8);
		brushes.add(btn9);
		// adding brushes layout to the toolbar
		brushToolbar.add(brushes);

		// creating a new button with text eraser
		JButton eraserBtn = new JButton("Eraser");

		// creating new panel
		JPanel eraserPanel =  new JPanel();
		eraserPanel.setLayout( new GridBagLayout() );
		eraserPanel.add(eraserBtn, new GridBagConstraints());
		// forcing the layout to be a size
		eraserPanel.setMinimumSize(new Dimension(70, 50));
		eraserPanel.setMaximumSize(new Dimension(70, 50));
		eraserPanel.setPreferredSize(new Dimension(70, 50));
		// adding layout to toolbar
		brushToolbar.add(eraserPanel);

		// adding Listener for each button
		btn1.addActionListener(e -> {
			// setting the tool incase it set to Stencil tool
			Project.getCurrent().setTool(new PencilTool());
			Project.getCurrent().setBrushSize(15);								
		});
		btn2.addActionListener(e -> {
			Project.getCurrent().setTool(new PencilTool());
			Project.getCurrent().setBrushSize(13);
		});
		btn3.addActionListener(e -> {
			Project.getCurrent().setTool(new PencilTool());
			Project.getCurrent().setBrushSize(11);
		});
		btn4.addActionListener(e -> {
			Project.getCurrent().setTool(new PencilTool());
			Project.getCurrent().setBrushSize(9);
		});
		btn5.addActionListener(e -> {
			Project.getCurrent().setTool(new PencilTool());
			Project.getCurrent().setBrushSize(7);
		});
		btn6.addActionListener(e -> {
			Project.getCurrent().setTool(new PencilTool());
			Project.getCurrent().setBrushSize(5);
		});
		btn7.addActionListener(e -> {
			Project.getCurrent().setTool(new StencilTool());
			// to decide what shape to produce
			Project.getCurrent().setBrushShape("splash1");
		});
		btn8.addActionListener(e -> {
			Project.getCurrent().setTool(new StencilTool());
			Project.getCurrent().setBrushShape("splash2");
		});
		
		btn9.addActionListener(e -> {
			Project.getCurrent().setTool(new StencilTool());
			Project.getCurrent().setBrushShape("star");
		});
		eraserBtn.addActionListener(e -> {
			Project.getCurrent().setTool(new StencilTool());
			Project.getCurrent().setBrushShape("eraser");
		});

		JToolBar attributeToolbar = new JToolBar("Tools");

		JButton colorWheelButton = new JButton();
		
		colorWheelButton.addActionListener(e -> {
			if (Project.getCurrent() == null) {
				return;
			}
			Color color = JColorChooser.showDialog(null, "Select Color", Project.getCurrent().getPrimaryBrushColor());
			colorWheelButton.setBackground(color);
			Project.getCurrent().setPrimaryBrushColor(color);
			System.out.println("wow");
			btn1.updateUI();
			btn2.updateUI();
			btn3.updateUI();
			btn4.updateUI();
			btn5.updateUI();
			btn6.updateUI();
			btn7.updateUI();
			btn8.updateUI();
			btn9.updateUI();
			
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

		JButton undoButton = new JButton("Undo");
		undoButton.addActionListener(e -> {
			CommandList.getInstance().undo();
		});

		JButton redoButton = new JButton("Redo");
		redoButton.addActionListener(e -> {
			CommandList.getInstance().redo();
		});

		attributeToolbar.add(new JLabel("Color"));
		attributeToolbar.add(colorWheelButton);
		attributeToolbar.add(createProjectButton);
		attributeToolbar.add(addLayerButton);
		attributeToolbar.add(loadLayerButton);
		attributeToolbar.add(nextLayerButton);
		attributeToolbar.add(layerLabel);
		attributeToolbar.add(exportButton);
		attributeToolbar.add(undoButton);
		attributeToolbar.add(redoButton);
		

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

	protected void run() {
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
