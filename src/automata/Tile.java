
/**
 * TILE CLASS
 * Keeps track of tile position and state.
 * State can be one of Wall(0), Floor(1) or Explored(2)
 */

package automata;


public class Tile {
    private int gridX;
    private int gridY;
    private int floorNeighbors;

    public Tile(int x, int y) {
        this.gridX = x;
        this.gridY = y;
        this.floorNeighbors = 0;
    }

    public boolean isWall(int[][] grid) {
        if (grid[this.gridX][this.gridY] == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFloor(int[][] grid) {
        if (grid[this.gridX][this.gridY] == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExplored(int[][] grid) {
        if (grid[this.gridX][this.gridY] == 2) {
            return true;
        } else {
            return false;
        }
    }

    public void updateNeighbors(int value) {
        this.floorNeighbors = value;
    }

    public int getNeighbors() {
        return this.floorNeighbors;
    }

    public int getX() {
        return this.gridX;
    }

    public int getY() {
        return this.gridY;
    }

    public String toString() {
        return "Tile at [" + this.gridX + ", " + this.gridY + "]";
    }
}