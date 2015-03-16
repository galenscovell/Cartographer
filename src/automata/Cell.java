
/**
 * CELL CLASS
 * Responsible for keeping track of cell position and state.
 */

package automata;


public class Cell {
    private int screenX;
    private int screenY;
    private int gridX;
    private int gridY;
    public int growthValue;

    public Cell(int screenX, int screenY, int gridX, int gridY) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.gridX = gridX;
        this.gridY = gridY;
        this.growthValue = 0;
    }

    public boolean isActive(int[][] grid) {
        if (grid[this.gridX][this.gridY] == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int getScreenX() {
        return this.screenX;
    }

    public int getScreenY() {
        return this.screenY;
    }

    public int getGridX() {
        return this.gridX;
    }

    public int getGridY() {
        return this.gridY;
    }

    public String toString() {
        return "Cell at [" + gridX + ", " + gridY + "]";
    }
}