package pixelsmart;

import java.awt.image.BufferedImage;

public class Layer {
    private BufferedImage image;
    private String name;
    private int x, y;

    public Layer(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}