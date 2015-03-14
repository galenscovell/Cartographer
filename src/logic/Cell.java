
/**
 * Cell class
 */

package logic;

import java.util.ArrayList;
import java.util.Random;


public class Cell {
    private int posX;
    private int posY;
    private boolean active;
    private ArrayList<Cell> adjacent;

    public Cell(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.adjacent = new ArrayList<Cell>();
        
        Random random = new Random();
        int fate = random.nextInt(100);
        if (fate > 95) {
            this.active = true;
        } else {
            this.active = false;
        }
    }

    public ArrayList<Cell> findAdjacent(int incrementer) {
        ArrayList<Cell> adjacent = new ArrayList<Cell>();
        for (Cell cell : grid) {
            int x = cell.getX();
            int y = cell.getY();
            
        }
    }

    public int checkAdjacent() {
        int activeTotal = 0;
        for (Cell cell : this.adjacent) {
            if (cell.isActive()) {
                activeTotal++;
            }
        }
        return activeTotal;
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