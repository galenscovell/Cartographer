
/**
 * POINT CLASS
 * Stores [x, y] coordinates from grid.
 * Used as List item for easily storing grid coordinates across objects.
 */

package logic;


public class Point {
    private int x;
    private int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int pointX() {
        return x;
    }

    public int pointY() {
        return y;
    }

    public String toString() {
        return x + ", " + y;
    }
}