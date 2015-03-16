
/**
 * MINER CLASS
 * Navigates across world grid, leaving walls.
 */

package automata;


public class Miner {
    private int gridX;
    private int gridY;

    public Miner(int x, int y) {
        this.gridX = x;
        this.gridY = y;
    }

    public int getX() {
        return this.gridX;
    }

    public int getY() {
        return this.gridY;
    }

    public String toString() {
        return "Miner at [" + this.gridX + ", " + this.gridY + "]";
    }
}