package pixelsmart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
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

		
		//////////////////////////////////////////////////////////////////
		
		JToolBar brushToolbar = new JToolBar("Brushes");
		brushToolbar.setOrientation(JToolBar.VERTICAL);

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
		brushes.setLayout(new GridLayout(5, 2));
		// forcing layout to be particular size
		brushes.setMaximumSize(new Dimension(63, 150));
		brushes.setMinimumSize(new Dimension(63, 150));
		brushes.setPreferredSize(new Dimension(63, 150));
		
		
		ShapeFactory shapeFactory = new ShapeFactory();
		// here we are creating the buttons
		JButton btn1 = new JButton();
		// removing border, to improves visuals
		btn1.setMargin(new Insets(0,0,0,0));
		// adding shape to the button
		//Project.getCurrent().setBrushSize(15);
		btn1.add((Component) shapeFactory.getShape("circle1", 5, 5));
		
		JButton btn2 = new JButton();
		btn2.setMargin(new Insets(0,0,0,0));
		//Project.getCurrent().setBrushSize(13);
		btn2.add((Component) shapeFactory.getShape("circle2", 6, 6));
		
		JButton btn3 = new JButton();
		btn3.setMargin(new Insets(0,0,0,0));
		//Project.getCurrent().setBrushSize(11);
		btn3.add((Component) shapeFactory.getShape("circle3", 7,7));

		JButton btn4 = new JButton();
		btn4.setMargin(new Insets(0,0,0,0));
		//Project.getCurrent().setBrushSize(9);
		btn4.add((Component) shapeFactory.getShape("circle4", 8, 8));
		
		JButton btn5 = new JButton();
		btn5.setMargin(new Insets(0,0,0,0));
		//Project.getCurrent().setBrushSize(7);
		btn5.add((Component) shapeFactory.getShape("circle5", 9, 9));
		
		JButton btn6 = new JButton();
		btn6.setMargin(new Insets(0,0,0,0));
		//Project.getCurrent().setBrushSize(5);
		btn6.add((Component) shapeFactory.getShape("circle6", 10, 10));
		
		JButton btn7 = new JButton();
		btn7.setMargin(new Insets(0,0,0,0));
		
		btn7.add((Component) shapeFactory.getShape("splash1", 15, 15));
		
		
		JButton btn8 = new JButton();
		btn8.setMargin(new Insets(0,0,0,0));
	
		btn8.add((Component) shapeFactory.getShape("splash2", 13, 13));
		
		JButton btn9 = new JButton();
		btn9.setMargin(new Insets(0,0,0,0));
		
		btn9.add((Component) shapeFactory.getShape("star", 16, 17));
	
		


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
		eraserPanel.setMinimumSize(new Dimension(70, 25));
		eraserPanel.setMaximumSize(new Dimension(70, 25));
		eraserPanel.setPreferredSize(new Dimension(70, 25));
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
			Tool test = new PencilTool();
			Project.getCurrent().setTool(test);
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
			Project.getCurrent().setBrushMode("splash1");
		
		});
		btn8.addActionListener(e -> {
			Project.getCurrent().setTool(new StencilTool());
			Project.getCurrent().setBrushMode("splash2");
	
		});
		
		btn9.addActionListener(e -> {
			Project.getCurrent().setTool(new StencilTool());
			Project.getCurrent().setBrushMode("star");
		});
		eraserBtn.addActionListener(e -> {
			Project.getCurrent().setTool(new EraserTool());
			
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



		attributeToolbar.add(new JLabel("Color"));
		attributeToolbar.add(colorWheelButton);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new FileMenu());
		menuBar.add(new EditMenu());
		menuBar.add(new LayerMenu());
		
		

		LayerList layerList = new LayerList();

		contentPane.add(attributeToolbar, BorderLayout.SOUTH);
		contentPane.add(layerList, BorderLayout.EAST);
		contentPane.add(brushToolbar, BorderLayout.WEST);
		contentPane.add(imagePanel, BorderLayout.CENTER);
		contentPane.add(menuBar, BorderLayout.NORTH);
		
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
		Input.getInstance().update();
		while (running()) {
			
			// Update
			Input.getInstance().update();
			
			
			if (Project.getCurrent() != null) {
				Project.getCurrent().update();
				//System.out.println("Update");
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
