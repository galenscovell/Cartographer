
/**
 * EXPLORER CLASS
 * Traverses maze, determining paths.
 * Explorer makes only NESW movements and is replaced once unable to move.
 */

package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Explorer {
    private int gridX;
    private int gridY;
    private int columns;
    private int rows;
    private Tile[][] grid;


    public Explorer(int x, int y, int columns, int rows, Tile[][] grid) {
        this.gridX = x;
        this.gridY = y;
        this.columns = columns;
        this.rows = rows;
        this.grid = grid;
    }

    public boolean movement(World world) {
        List<Point> options = new ArrayList<Point>();
        int sumX, sumY;

        for (int x = -1; x <= 1; x += 2) {
            sumX = getX() + x;
            sumY = getY();

            if (isOutOfBounds(sumX, sumY)) {
                continue;
            }
            if (grid[sumX][sumY].isFloor()) {
                options.add(new Point(sumX, sumY));
            }
        }

        for (int y = -1; y <= 1; y += 2) {
            sumX = getX();
            sumY = getY() + y;

            if (isOutOfBounds(sumX, sumY)) {
                continue;
            }
            if (grid[sumX][sumY].isFloor()) {
                options.add(new Point(sumX, sumY));
            }
        }

        grid[gridX][gridY].setState(2);
        if (options.size() > 0) {
            Random random = new Random();
            int choice = random.nextInt(options.size());
            Point chosenPoint = options.get(choice);
            moveToTile(chosenPoint.getX(), chosenPoint.getY());
            return true;
        } else {
            return false;
        }
    }

    public void moveToTile(int newX, int newY) {
        gridX = newX;
        gridY = newY;
        grid[gridX][gridY].setState(3);
    }

    public boolean isOutOfBounds(int x, int y) {
        if (x < 0 || y < 0){
            return true;
        } else if (x >= columns || y >= rows){
            return true;
        } else {
            return false;
        }
    }

    public int getX() {
        return gridX;
    }

    public int getY() {
        return gridY;
    }

    public String toString() {
        return "Explorer at [" + gridX + ", " + gridY + "]";
    }
}