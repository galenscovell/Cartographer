
/**
 * MAZEBUILDER CLASS
 * Constructs a new world grid and tileset with maze features. 
 * (winding paths one tile thick)
 */

package logic;

import automata.Tile;

import java.util.ArrayList;
import java.util.Random;


public class MazeBuilder implements Builder {
    private int rows;
    private int columns;
    private int[][] grid;
    private ArrayList<Tile> tiles;

    public MazeBuilder(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        
        this.grid = new int[columns][rows];
        this.tiles = new ArrayList<Tile>();
    }

    public void build() {
        Random random = new Random();
        int chance;

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                tiles.add(new Tile(x, y));
                
                chance = random.nextInt(100);
                if (chance < 40) {
                    grid[x][y] = 1;
                } else {
                    grid[x][y] = 0;
                }
            }
        }
    }

    public void smooth(Tile tile) {
        int floorNeighbors = tile.getNeighbors();
        
        if (floorNeighbors == 0 || floorNeighbors > 4) {
            grid[tile.getX()][tile.getY()] = 0;
        } else if (floorNeighbors == 3) {
            grid[tile.getX()][tile.getY()] = 1;
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}