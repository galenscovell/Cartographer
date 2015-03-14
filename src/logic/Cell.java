
/**
 * Cell class
 */

package logic;

import java.util.Random;


public class Cell {
    private int posX;
    private int posY;
    private boolean active;

    public Cell(int x, int y) {
        this.posX = x;
        this.posY = y;

        Random random = new Random();
        int fate = random.nextInt(10);
        if (fate > 5) {
            this.active = true;
        } else {
            this.active = false;
        }
    }

    public boolean isActive() {
        return this.active;
    }

    public int getX() {
        return this.posX;
    }

    public int getY() {
        return this.posY;
    }

    public String toString() {
        return "Cell at [" + posX + ", " + posY + "]";
    }
}