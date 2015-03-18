
/**
 * WORLD CLASS
 * Renders and updates world grid.
 * World is composed of a 2D array grid and a list of matching Tile instances.
 */

package automata;

import logic.Builder;
import logic.CaveBuilder;
import logic.MazeBuilder;

import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;


public class World {
    private int columns;
    private int rows;

    private int tileSize;
    private int margin;

    private Builder builder;
    public int[][] grid;
    private ArrayList<Tile> tiles;

    private Explorer explorer;
    

    public World(int width, int height, int tileSize, int margin) {
        this.tileSize = tileSize;
        this.margin = margin;

        this.columns = width / (tileSize + margin);
        this.rows = height / (tileSize + margin);

        this.builder = new MazeBuilder(this.columns, this.rows);
        this.builder.build();

        this.grid = this.builder.getGrid();
        this.tiles = this.builder.getTiles();
    }

    public Explorer placeExplorer() {
        for (Tile tile : this.tiles) {
            if (tile.isFloor(this.grid)) {
                this.grid[tile.getX()][tile.getY()] = 2;
                Explorer explorer = new Explorer(tile.getX(), tile.getY());
                return explorer;
            } else {
                continue;
            }
        }
        return null;
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

    public boolean isFloorSpace() {
        for (Tile tile : this.tiles) {
            if (tile.isFloor(this.grid)) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        for (Tile tile : this.tiles) {
            tile.updateNeighbors(checkAdjacent(tile));
        }
        for (Tile tile : this.tiles) {
            this.builder.smooth(tile);
        }
    }

    public void render(Graphics gfx) {
        int screenX, screenY;
        Color floor    = new Color(0x34495e);
        Color wall     = new Color(0x2c3e50);
        Color explored = new Color(0x2980b9);
        Color active   = new Color(0xecf0f1);

        for (Tile tile : this.tiles) {
            if (tile.isFloor(this.grid)) {
                gfx.setColor(floor);
            } else if (tile.isWall(this.grid)) {
                gfx.setColor(wall);
            } else if (tile.isExplored(this.grid)) {
                gfx.setColor(explored);
            } else {
                gfx.setColor(active);
            }
            screenX = (tile.getX() * (this.tileSize + this.margin)) + 1;
            screenY = (tile.getY() * (this.tileSize + this.margin)) + 1;
            gfx.fillRect(screenX, screenY, this.tileSize, this.tileSize);
        }
    }
}