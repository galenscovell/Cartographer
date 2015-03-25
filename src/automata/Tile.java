
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
        if (grid[gridX][gridY] == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFloor(int[][] grid) {
        if (grid[gridX][gridY] == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExplored(int[][] grid) {
        if (grid[gridX][gridY] == 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCorridor(int[][] grid) {
        if (grid[gridX][gridY] == 3) {
            return true;
        } else {
            return false;
        }
    }

    public void updateNeighbors(int value) {
        floorNeighbors = value;
    }

    public int getNeighbors() {
        return floorNeighbors;
    }

    public int getX() {
        return gridX;
    }

    public int getY() {
        return gridY;
    }

    public String toString() {
        return "Tile at [" + gridX + ", " + gridY + "]";
    }
}