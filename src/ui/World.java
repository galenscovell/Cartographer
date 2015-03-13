
/**
 * World class renders world to screen.
 */

package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class World extends Canvas {
    private int width;
    private int height;
    private int cellSize;
    private int margin;
    
    public World(int width, int height, int cellSize, int margin) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.margin = margin;
    }

    public void render(Graphics g) {
        int incrementer = cellSize + margin;
        for (int x = margin; x < width - margin; x += incrementer) {
            for (int y = margin; y < height - margin; y += incrementer) {
                g.setColor(new Color(47, 47, 49));
                g.fillRect(x, y, cellSize, cellSize);
            }
        }
    }
}