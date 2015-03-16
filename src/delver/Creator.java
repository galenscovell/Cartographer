
/**
 * CREATOR CLASS
 * Another method for maze construction.
 * Creator object navigates across world grid leaving walls.
 */

package delver;


public class Creator {
    private int screenX;
    private int screenY;
    private int gridX;
    private int gridY;

    public Creator(int screenX, int screenY, int gridX, int gridY) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.gridX = gridX;
        this.gridY = gridY;
    }

    public int getScreenX() {
        return this.screenX;
    }

    public int getScreenY() {
        return this.screenY;
    }

    public int getGridX() {
        return this.gridX;
    }

    public int getGridY() {
        return this.gridY;
    }
}