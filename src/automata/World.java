
/**
 * WORLD CLASS
 * Renders and updates world grid.
 */

package automata;

import automata.Tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class World {
    private int columns;
    private int rows;
    private int tileSize;
    private int margin;

    private int[][] grid;
    private List<Tile> tiles;
    

    public World(int width, int height, int tileSize, int margin) {
        this.tileSize = tileSize;
        this.margin = margin;

        this.columns = width / (tileSize + margin);
        this.rows = height / (tileSize + margin);

        this.grid = new int[this.columns][this.rows];
        this.tiles = new ArrayList<Tile>();
    }

    public void build() {
        Random random = new Random();
        int chance;

        int screenX, screenY;
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

    public int checkAdjacent(Tile tile) {
        int sumX, sumY;
        int floorNeighbors = 0;
                
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                sumX = tile.getX() + x;
                sumY = tile.getY() + y;
                if (sumX == tile.getX() && sumY == tile.getY()) {
                    continue;
                }
                if (isOutOfBounds(sumX, sumY)) {
                    continue;
                }
                if (this.grid[sumX][sumY] == 1) {
                        floorNeighbors++;
                }
            }
        }
        return floorNeighbors;
    }

    
    public boolean isOutOfBounds(int x, int y) {
        if (x < 0 || y < 0){
            return true;
        } else if (x >= this.columns || y >= this.rows){
            return true;
        } else {
            return false;
        }
    }

    public void smooth(int floorNeighbors, Tile tile) {
        if (tile.isFloor(this.grid)) {
            if (floorNeighbors == 0 || floorNeighbors > 4) {
                grid[tile.getX()][tile.getY()] = 0; // tile becomes wall
            }
        } else if (tile.isWall(this.grid)) {
            if (floorNeighbors == 3) {
                grid[tile.getX()][tile.getY()] = 1; // tile becomes floor
            }
        }
    }

    public void update() {
        for (Tile tile : this.tiles) {
            tile.floorNeighbors = checkAdjacent(tile);
        }
        for (Tile tile : this.tiles) {
            smooth(tile.floorNeighbors, tile);
        }
    }

    public void render(Graphics g) {
        int screenX, screenY;

        for (Tile tile : this.tiles) {
            if (tile.isFloor(this.grid)) {
                g.setColor(new Color(0x34495e));
            } else {
                g.setColor(new Color(0x27ae60));
            }
            screenX = (tile.getX() * (this.tileSize + this.margin)) + this.margin;
            screenY = (tile.getY() * (this.tileSize + this.margin)) + this.margin;
            g.fillRect(screenX, screenY, this.tileSize, this.tileSize);
        }
    }
}