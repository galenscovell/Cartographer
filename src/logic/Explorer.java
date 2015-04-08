
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
    public int x, y;
    private int columns, rows;
    private Tile[][] grid;


    public Explorer(int x, int y, int columns, int rows, Tile[][] grid) {
        this.x = x;
        this.y = y;
        this.columns = columns;
        this.rows = rows;
        this.grid = grid;
    }

    public boolean movement(World world) {
        List<Point> options = new ArrayList<Point>();
        int sumX, sumY;

        for (int dx = -1; dx <= 1; dx += 2) {
            sumX = x + dx;
            sumY = y;

            if (isOutOfBounds(sumX, sumY)) {
                continue;
            }
            if (grid[sumX][sumY].isFloor()) {
                options.add(new Point(sumX, sumY));
            }
        }

        for (int dy = -1; dy <= 1; dy += 2) {
            sumX = x;
            sumY = y + dy;

            if (isOutOfBounds(sumX, sumY)) {
                continue;
            }
            if (grid[sumX][sumY].isFloor()) {
                options.add(new Point(sumX, sumY));
            }
        }

        grid[x][y].setState(2);
        if (options.size() > 0) {
            Random random = new Random();
            int choice = random.nextInt(options.size());
            Point chosenPoint = options.get(choice);
            moveToTile(chosenPoint.x, chosenPoint.y);
            return true;
        } else {
            return false;
        }
    }

    public void moveToTile(int newX, int newY) {
        x = newX;
        y = newY;
        grid[x][y].setState(3);
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

    public String toString() {
        return "Explorer at [" + x + ", " + y + "]";
    }
}