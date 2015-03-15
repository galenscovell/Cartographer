
/**
 * WORLD CLASS
 * Responsible for rendering and updating world grid.
 */

package graphics;

import logic.Cell;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class World extends Canvas {
    private int columns;
    private int rows;
    private int cellSize;
    private int margin;

    private int[][] grid;
    private List<Cell> cells;
    

    public World(int width, int height, int cellSize, int margin) {
        this.cellSize = cellSize;
        this.margin = margin;

        this.columns = width / (cellSize + margin);
        this.rows = height / (cellSize + margin);

        this.grid = new int[this.columns][this.rows];
        this.cells = new ArrayList<Cell>();
    }

    public void build() {
        Random random = new Random();
        int chance;

        int screenX, screenY;
        for (int y = 0; y < this.rows; y++) {
            for (int x = 0; x < this.columns; x++) {
                screenX = (x * (this.cellSize + this.margin)) + this.margin;
                screenY = (y * (this.cellSize + this.margin)) + this.margin;
                this.cells.add(new Cell(screenX, screenY, x, y));

                chance = random.nextInt(100);
                if (chance > 80) {
                    this.grid[x][y] = 1;
                } else {
                    this.grid[x][y] = 0;
                }
            }
        }
    }

    public int checkAdjacent(Cell cell) {
        int sumX, sumY;
        int activeNeighbors = 0;
                
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                sumX = cell.getGridX() + x;
                sumY = cell.getGridY() + y;
                if (sumX == cell.getGridX() && sumY == cell.getGridY()) {
                    continue;
                }
                if (isOutOfBounds(sumX, sumY)) {
                    continue;
                }
                if (this.grid[sumX][sumY] == 1) {
                        activeNeighbors++;
                }
            }
        }
        return activeNeighbors;
    }

    public boolean isOutOfBounds(int x, int y) {
        if (x < 0 || y < 0){
            return true;
        } else if (x >= this.columns || y >= this.rows){
            return true;
        } else {
            return false;
        }
    }

    public void evolveCell(int growthValue, Cell cell) {
        if (growthValue < 2 || growthValue > 3) {
            grid[cell.getGridX()][cell.getGridY()] = 0;
        } else if (growthValue == 3) {
            grid[cell.getGridX()][cell.getGridY()] = 1;
        }
    }

    public void update() {
        for (Cell cell : this.cells) {
            cell.growthValue = checkAdjacent(cell);
        }
        for (Cell cell : this.cells) {
            evolveCell(cell.growthValue, cell);
        }
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