package notDefault;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	public static myPanel myP;
	private static MainWindow frame;
	
	private static int mouseX = 0;
	private static int mouseY = 0;
	
	private static boolean shouldDraw = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		frame = new MainWindow();
		frame.setVisible(true);
		
		frame.appLoop();
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		myP = new myPanel();
		contentPane.add(myP, BorderLayout.CENTER);
		
		myP.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				mouseX = e.getX();
				mouseY = e.getY();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				mouseX = e.getX();
				mouseY = e.getY();
			}
			
		});
		myP.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					shouldDraw = true;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					shouldDraw = false;
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});
	}

	public void appLoop()
	{
		BasicBrush b = new BasicBrush();
		while(running())
		{
			
			//do things
			if(shouldDraw)
			{
				b.performAction();
			}
			
			//render things
			
			myP.repaint();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static int getMouseX()
	{
		return mouseX;
	}
	
	public static int getMouseY()
	{
		return mouseY;
	}
	
	private boolean running() {
		// TODO Auto-generated method stub
		return this.isDisplayable();
	}
}
