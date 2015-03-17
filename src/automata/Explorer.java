
/**
 * EXPLORER CLASS
 * Traverses maze, determining paths.
 */

package automata;

import logic.Point;

import java.util.ArrayList;
import java.util.Random;


public class Explorer {
    private int gridX;
    private int gridY;


    public Explorer(int x, int y) {
        this.gridX = x;
        this.gridY = y;
    }

    public boolean movement(World world) {
        ArrayList<Point> options = new ArrayList<Point>();
        int sumX, sumY;

        for (int x = -1; x <= 1; x += 2) {
            sumX = this.getX() + x;
            sumY = this.getY();

            if (world.isOutOfBounds(sumX, sumY)) {
                continue;
            }
            if (world.grid[sumX][sumY] == 1) {
                options.add(new Point(sumX, sumY));
            }
        }

        for (int y = -1; y <= 1; y += 2) {
            sumX = this.getX();
            sumY = this.getY() + y;

            if (world.isOutOfBounds(sumX, sumY)) {
                continue;
            }
            if (world.grid[sumX][sumY] == 1) {
                options.add(new Point(sumX, sumY));
            }
        }

        world.grid[this.gridX][this.gridY] = 2;
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
        this.gridX = newX;
        this.gridY = newY;
        grid[this.gridX][this.gridY] = 3;
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