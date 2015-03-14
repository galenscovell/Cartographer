
/**
 * World class renders world to screen.
 */

package graphics;

import logic.Cell;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.List;


public class World extends Canvas {
    private int width;
    private int height;
    private int cellSize;
    private int margin;

    private List<Cell> grid;
    

    public World(int width, int height, int cellSize, int margin) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.margin = margin;

        this.grid = new ArrayList<Cell>();
    }

    public void build() {
        int incrementer = cellSize + margin;
        for (int x = margin; x < width - margin; x += incrementer) {
            for (int y = margin; y < height - margin; y += incrementer) {
                grid.add(new Cell(x, y));
            }
        }
    }

    public void render(Graphics g) {
        for (Cell cell : grid) {
            if (cell.isActive()) {
                g.setColor(new Color(0x27ae60));
            } else {
                g.setColor(new Color(0x34495e));
            }
            g.fillRect(cell.getX(), cell.getY(), cellSize, cellSize);
        }
    }
}