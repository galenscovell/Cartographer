
/**
 * WORLD CLASS
 * World is composed of a 2D array grid and a list of matching Tile instances.
 */

package logic;

import java.awt.Color;
import java.awt.Graphics2D;

import java.util.List;


public class World {
    private int columns;
    private int rows;
    private int tileSize;
    private int margin;

    private Builder builder;
    private List<Tile> tiles;
    private Explorer explorer;
    

    public World(int width, int height, int tileSize, int margin, String worldType) {
        this.tileSize = tileSize;
        this.margin = margin;
        this.columns = width / (tileSize + margin);
        this.rows = height / (tileSize + margin);

        if (worldType.equals("maze")) {
            this.builder = new MazeBuilder(columns, rows);
        } else if (worldType.equals("cave")) {
            this.builder = new CaveBuilder(columns, rows);
        } else if (worldType.equals("dungeon")) {
            this.builder = new DungeonBuilder(columns, rows);
        }

        builder.build();
        this.tiles = this.builder.getTiles();
    }

    public Point findFloorSpace() {
        Point floorPoint;
        for (Tile tile : tiles) {
            if (tile.isFloor()) {
                floorPoint = new Point(tile.getX(), tile.getY());
                return floorPoint;
            }
        }
        return null;
    }

    public Explorer placeExplorer(Point point) {
        Explorer explorer = new Explorer(point.getX(), point.getY(), columns, rows, getGrid());
        return explorer;
    }

    public void checkAdjacent() {
        Tile[][] grid = builder.getGrid();

        for (Tile tile : tiles) {
            int floorNeighbors = 0;
            List<Point> neighborPoints = tile.getNeighbors();
            for (Point point : neighborPoints) {
                if (grid[point.getX()][point.getY()].isFloor()) {
                    floorNeighbors++;
                }
            }
            tile.setFloorNeighbors(floorNeighbors);
        }
    }

    public void update() {
        checkAdjacent();

        for (Tile tile : tiles) {
            builder.smooth(tile);
        }
    }

    public void render(Graphics2D gfx) {
        int screenX, screenY;
        Color floor    = new Color(0x34495e);
        Color wall     = new Color(0x2c3e50);
        Color explored = new Color(0x27ae60);
        Color active   = new Color(0xecf0f1);

        for (Tile tile : tiles) {
            if (tile.isFloor()) {
                gfx.setColor(floor);
            } else if (tile.isWall()) {
                gfx.setColor(wall);
            } else if (tile.isExplored()) {
                gfx.setColor(explored);
            } else {
                gfx.setColor(active);
            }
            screenX = (tile.getX() * (tileSize + margin)) + 1;
            screenY = (tile.getY() * (tileSize + margin)) + 1;
            gfx.fillRect(screenX, screenY, tileSize, tileSize);
        }
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Tile[][] getGrid() {
        return builder.getGrid();
    }
}