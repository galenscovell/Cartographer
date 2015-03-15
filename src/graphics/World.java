
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
    private int columns;
    private int rows;
    private int cellSize;
    private int margin;

    private int[][] grid;
    private List<Cell> cells;
    

    public World(int width, int height, int cellSize, int margin) {
        this.columns = width / (cellSize + margin);
        this.rows = height / (cellSize + margin);
        this.cellSize = cellSize;
        this.margin = margin;

        this.grid = new int[this.columns][this.rows];
        this.cells = new ArrayList<Cell>();
    }

    public void build() {
        int screenX;
        int screenY;
        for (int column = 0; column < this.columns; column++) {
            for (int row = 0; row < this.rows; row++) {
                screenX = (column * (this.cellSize + this.margin)) + this.margin;
                screenY = (row * (this.cellSize + this.margin)) + this.margin;
                this.cells.add(new Cell(screenX, screenY, column, row));
                this.grid[column][row] = 0;
            }
        }
        this.grid[1][0] = 1;

        findAdjacent(cells.get(1));
    }

    public void findAdjacent(Cell cell) {
        System.out.println(cell);
        int sumX;
        int sumY;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                sumX = cell.getGridX() + x;
                sumY = cell.getGridY() + y;
                // If values equal self
                if (sumX == cell.getGridX() && sumY == cell.getGridY()) {
                    continue;
                } else if (isOutOfBounds(sumX, sumY)) {
                    continue;
                }
                System.out.println(sumX + ", " + sumY);
            }
        }
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
            g.fillRect(cell.getScreenX(), cell.getScreenY(), this.cellSize, this.cellSize);
        }
    }
}