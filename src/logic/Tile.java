
/**
 * TILE CLASS
 * Keeps track of tile position and state.
 * State can be one of Wall(0), Floor(1), Explored(2), or Corridor(3)
 */

package logic;

import java.util.ArrayList;
import java.util.List;


public class Tile {
    private int x;
    private int y;
    private int state;
    private int floorNeighbors;
    private List<Point> neighbors;

    public Tile(int x, int y, int state, int columns, int rows) {
        this.x = x;
        this.y = y;
        this.state = state;
        this.neighbors = findNeighbors(columns, rows);
    }

    public boolean isWall() {
        if (state == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFloor() {
        if (state == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExplored() {
        if (state == 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCorridor() {
        if (state == 3) {
            state = 1;
            return true;
        } else {
            return false;
        }
    }

    public boolean isOutOfBounds(int i, int j, int columns, int rows) {
        if (i < 0 || j < 0){
            return true;
        } else if (i >= columns || j >= rows){
            return true;
        } else {
            return false;
        }
    }

    public void setNeighbors(int value) {
        floorNeighbors = value;
    }

    public List<Point> getNeighbors() {
        return neighbors;
    }

    public int getFloorNeighbors() {
        return floorNeighbors;
    }

    public void setState(int value) {
        state = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private List<Point> findNeighbors(int columns, int rows) {
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
}