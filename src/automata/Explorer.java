
/**
 * EXPLORER CLASS
 * Traverses maze, determining paths.
 */

package automata;


public class Explorer {
    private int gridX;
    private int gridY;


    public Explorer(int x, int y) {
        this.gridX = x;
        this.gridY = y;
    }

    public void move(int newX, int newY, int[][] grid) {
        grid[newX][newY] = 1;
        this.gridX = newX;
        this.gridY = newY;
    }

    public int getX() {
        return this.gridX;
    }

    public int getY() {
        return this.gridY;
    }

    public String toString() {
        return "Explorer at [" + this.gridX + ", " + this.gridY + "]";
    }
}