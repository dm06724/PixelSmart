package pixelsmart;

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
    	
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainWindow window = MainWindow.getInstance();
        window.setVisible(true);
        window.run();
        
    }
}