package pixelsmart;

public class Main {
    public static void main(String[] args) {
        MainWindow window = MainWindow.getInstance();
        window.setVisible(true);
        window.appLoop();
    }
}