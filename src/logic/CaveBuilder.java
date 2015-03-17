
/**
 * CAVEBUILDER CLASS
 * Constructs a new world grid and tileset with cave features.
 */

package logic;

import automata.Tile;

import java.util.ArrayList;
import java.util.Random;


public class CaveBuilder implements Builder {
    private int rows;
    private int columns;
    private int[][] grid;
    private ArrayList<Tile> tiles;

    public CaveBuilder(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        
        this.grid = new int[columns][rows];
        this.tiles = new ArrayList<Tile>();
    }

    public void build() {
        Random random = new Random();
        int chance;

        for (int y = 0; y < this.rows; y++) {
            for (int x = 0; x < this.columns; x++) {
                this.tiles.add(new Tile(x, y));
                
                chance = random.nextInt(100);
                if (chance < 40) {
                    this.grid[x][y] = 1;
                } else {
                    this.grid[x][y] = 0;
                }
            }
        }
    }

    public void smooth(Tile tile) {
        int floorNeighbors = tile.getNeighbors();
        
        if (floorNeighbors > 3) {
            this.grid[tile.getX()][tile.getY()] = 1;
        } else if (floorNeighbors < 3) {
            this.grid[tile.getX()][tile.getY()] = 0;
        }
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public ArrayList<Tile> getTiles() {
        return this.tiles;
    }
}