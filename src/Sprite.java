
/**
 * Sprite class, displays only sprite image (no state information).
 */

import java.awt.Graphics;
import java.awt.Image;


public class Sprite {
    private Image image;

    public Sprite(Image image) {
        this.image = image;
    }

    public int getWidth() {
        // Image.getWidth(ImageObserver)
        return this.image.getWidth(null);
    }

    public int getHeight() {
        // Image.getHeight(ImageObserver)
        return this.image.getHeight(null);
    }

    public void draw(Graphics g, int x, int y) {
        // Graphics.drawImage(img, pos-x, pos-y, ImageObserver)
        g.drawImage(this.image, x, y, null);
    }
}