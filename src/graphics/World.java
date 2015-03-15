
/**
 * WORLD CLASS
 * Responsible for rendering world grid.
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
    private int rows;
    private int columns;
    private int cellSize;
    private int margin;

    private int[][] grid;
    private List<Cell> cells;
    

    public World(int width, int height, int cellSize, int margin) {
        this.rows = height / (cellSize + margin);
        this.columns = width / (cellSize + margin);
        this.cellSize = cellSize;
        this.margin = margin;

        this.grid = new int[this.columns][this.rows];
        this.cells = new ArrayList<Cell>();
    }

    public void build() {
        int absoluteX;
        int absoluteY;
        for (int x = 0; x < this.columns; x++) {
            for (int y = 0; y < this.rows; y++) {
                absoluteX = (x * (this.cellSize + this.margin)) + this.margin;
                absoluteY = (y * (this.cellSize + this.margin)) + this.margin;
                this.cells.add(new Cell(absoluteX, absoluteY, x, y));
                this.grid[x][y] = 0;
            }
        }
        this.grid[25][10] = 1;
        this.grid[25][9] = 1;
        this.grid[24][10] = 1;
    }

    public boolean isOutOfBounds(int x, int y) {
        if (x < 0 || y < 0){
            return true;
        } else if (x > this.columns || y > this.rows){
            return true;
        } else {
            return false;
        }
    }

    public void update() {

    }

    public void render(Graphics g) {
        for (Cell cell : this.cells) {
            if (cell.isActive(this.grid)) {
                g.setColor(new Color(0x27ae60));
            } else {
                g.setColor(new Color(0x34495e));
            }
            g.fillRect(cell.getX(), cell.getY(), this.cellSize, this.cellSize);
        }
    }
}