
/**
 * EXPLORER CLASS
 * Traverses maze, determining paths.
 * Explorer makes only NESW movements and is replaced once unable to move.
 */

package automata;

import logic.Point;
import logic.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Explorer {
    private int gridX;
    private int gridY;


    public Explorer(int x, int y) {
        this.gridX = x;
        this.gridY = y;
    }

    public boolean movement(World world) {
        List<Point> options = new ArrayList<Point>();
        int sumX, sumY;

        for (int x = -1; x <= 1; x += 2) {
            sumX = getX() + x;
            sumY = getY();

            if (world.isOutOfBounds(sumX, sumY)) {
                continue;
            }
            if (world.grid[sumX][sumY] == 1) {
                options.add(new Point(sumX, sumY));
            }
        }

        for (int y = -1; y <= 1; y += 2) {
            sumX = getX();
            sumY = getY() + y;

            if (world.isOutOfBounds(sumX, sumY)) {
                continue;
            }
            if (world.grid[sumX][sumY] == 1) {
                options.add(new Point(sumX, sumY));
            }
        }

        world.grid[gridX][gridY] = 2;
        if (options.size() > 0) {
            Random random = new Random();
            int choice = random.nextInt(options.size());
            Point chosenPoint = options.get(choice);
            moveToTile(chosenPoint.pointX(), chosenPoint.pointY(), world.grid);
            return true;
        } else {
            return false;
        }
    }

    public void moveToTile(int newX, int newY, int[][] grid) {
        gridX = newX;
        gridY = newY;
        grid[gridX][gridY] = 3;
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