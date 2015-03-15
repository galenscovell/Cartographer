
/**
 * CELL CLASS
 * Responsible for keeping track of cell position and state.
 */

package logic;

import java.util.Random;


public class Cell {
    private int screenX;
    private int screenY;
    private int gridX;
    private int gridY;

    public Cell(int screenX, int screenY, int gridX, int gridY) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.gridX = gridX;
        this.gridY = gridY;
    }

    public boolean isActive(int[][] grid) {
        if (grid[this.gridX][this.gridY] == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int getX() {
        return this.screenX;
    }

    public int getY() {
        return this.screenY;
    }

    public String toString() {
        return "Cell at [" + screenX + ", " + screenY + "] (" + gridX + ", " + gridY + "]";
    }
}