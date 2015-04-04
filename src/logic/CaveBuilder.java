
/**
 * CAVEBUILDER CLASS
 * Constructs a new world grid and tileset with cave features. 
 * (large, open areas)
 */

package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CaveBuilder implements Builder {
    private int rows;
    private int columns;
    private Tile[][] grid;

    public CaveBuilder(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.grid = new Tile[columns][rows];
    }

    public void build() {
        Random random = new Random();
        int chance, state;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                chance = random.nextInt(100);
                if (chance < 40) {
                    state = 1;
                } else {
                    state = 0;
                }
                grid[x][y] = new Tile(x, y, state, columns, rows);
            }
        }
    }

    public void smooth(Tile tile) {
        if (tile.getFloorNeighbors() > 3) {
            tile.setState(1);
        } else if (tile.getFloorNeighbors() < 3) {
            tile.setState(0);
        }
    }

    public List<Tile> getTiles() {
        List<Tile> tiles = new ArrayList<Tile>();

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                tiles.add(grid[x][y]);
            }
        }
        return tiles;
    }

    public Tile[][] getGrid() {
        return grid;
    }
}