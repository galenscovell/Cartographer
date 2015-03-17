
/**
 * WORLD CLASS
 * Renders and updates world grid.
 */

package automata;

import automata.Tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;


public class World {
    public static int columns;
    public static int rows;

    private int tileSize;
    private int margin;

    public static int[][] grid;
    private ArrayList<Tile> tiles;
    private Explorer explorer;
    

    public World(int width, int height, int tileSize, int margin) {
        this.tileSize = tileSize;
        this.margin = margin;

        World.columns = width / (tileSize + margin);
        World.rows = height / (tileSize + margin);

        World.grid = new int[World.columns][World.rows];
        this.tiles = new ArrayList<Tile>();
    }

    public void build() {
        Random random = new Random();
        int chance;

        int screenX, screenY;
        for (int y = 0; y < World.rows; y++) {
            for (int x = 0; x < World.columns; x++) {
                this.tiles.add(new Tile(x, y));

                chance = random.nextInt(100);
                if (chance < 40) {
                    World.grid[x][y] = 1;
                } else {
                    World.grid[x][y] = 0;
                }
            }
        }
    }

    public Explorer placeExplorer() {
        for (Tile tile : this.tiles) {
            if (tile.isFloor(World.grid)) {
                World.grid[tile.getX()][tile.getY()] = 2;
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
                if (World.grid[sumX][sumY] == 1) {
                    floorNeighbors++;
                }
            }
        }
        return floorNeighbors;
    }

    
    public static boolean isOutOfBounds(int x, int y) {
        if (x < 0 || y < 0){
            return true;
        } else if (x >= World.columns || y >= World.rows){
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmptyFloorSpace() {
        for (Tile tile : this.tiles) {
            if (tile.isFloor(World.grid)) {
                return true;
            }
        }
        return false;
    }

    public void smooth(int floorNeighbors, Tile tile) {
        if (tile.isFloor(World.grid)) {
            if (floorNeighbors == 0 || floorNeighbors > 4) {
                grid[tile.getX()][tile.getY()] = 0;
            }
        } else if (tile.isWall(World.grid)) {
            if (floorNeighbors == 3) {
                grid[tile.getX()][tile.getY()] = 1;
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
        Color floor = new Color(0x34495e);
        Color wall = new Color(0x2c3e50);
        Color explored = new Color(0x3498db);

        for (Tile tile : this.tiles) {
            if (tile.isFloor(World.grid)) {
                g.setColor(floor);
            } else if (tile.isWall(World.grid)) {
                g.setColor(wall);
            } else if (tile.isExplored(World.grid)) {
                g.setColor(explored);
            }
            screenX = (tile.getX() * (this.tileSize + this.margin)) + this.margin;
            screenY = (tile.getY() * (this.tileSize + this.margin)) + this.margin;
            g.fillRect(screenX, screenY, this.tileSize, this.tileSize);
        }
    }
}