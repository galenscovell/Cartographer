
/**
 * TILE CLASS
 * Keeps track of tile position and state.
 * State can be one of Wall(0), Floor(1), Explored(2), or Corridor(3)
 */

package logic;

import java.util.ArrayList;
import java.util.List;


public class Tile {
    public int x, y;
    private int state;
    private int floorNeighbors;
    private List<Point> neighboringTiles;

    public Tile(int x, int y, int state, int columns, int rows) {
        this.x = x;
        this.y = y;
        this.state = state;
        this.neighboringTiles = findNeighbors(columns, rows);
    }

    public boolean isWall() {
        return state == 0;
    }

    public boolean isFloor() {
        return state == 1;
    }

    public boolean isExplored() {
        return state == 2;
    }

    public boolean isCorridor() {
        return state == 3;
    }

    public void setFloorNeighbors(int value) {
        floorNeighbors = value;
    }

    public int getFloorNeighbors() {
        return floorNeighbors;
    }

    public void setState(int value) {
        state = value;
    }

    public List<Point> getNeighbors() {
        return neighboringTiles;
    }

    private List<Point> findNeighbors(int columns, int rows) {
        // Compute neighboring tiles only once at object construction
        List<Point> points = new ArrayList<Point>();
        int sumX, sumY;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                sumX = x + i;
                sumY = y + j;

                if ((sumX == x && sumY == y || (isOutOfBounds(sumX, sumY, columns, rows)))) {
                    continue;
                }
                points.add(new Point(sumX, sumY));
            }
        }
        return points;
    }

    private boolean isOutOfBounds(int i, int j, int columns, int rows) {
        if (i < 0 || j < 0){
            return true;
        } else if (i >= columns || j >= rows){
            return true;
        } else {
            return false;
        }
    }
}
