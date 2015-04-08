
/**
 * DUNGEONBUILDER CLASS
 * Constructs a new world grid and tileset with dungeon features. 
 * (rooms connected with corridors)
 */

package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DungeonBuilder implements Builder {
    private int rows, columns;
    private Tile[][] grid;

    public DungeonBuilder(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.grid = new Tile[columns][rows];
    }

    public void build() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                // All tiles begin as walls
                grid[x][y] = new Tile(x, y, 0, columns, rows);
            }
        }

        // Find center of Tile grid to create first room
        int midColumn = (columns - 1) / 2;
        int midRow = (rows - 1) / 2;
        createRoom(midColumn, midRow);
    }

    public void createRoom(int centerX, int centerY) {
        Random generator = new Random();
        // Possible room sizes from (5x5) to (11x11) tiles
        int roomSize = generator.nextInt(3) + 2;
        List<Point> perimeterPoints = new ArrayList<Point>();
        int sumX, sumY;

        grid[centerX][centerY].setState(1);
        for (int x = -roomSize; x <= roomSize; x++) {
            for (int y = -roomSize; y <= roomSize; y++) {
                sumX = centerX + x;
                sumY = centerY + y;

                if (isOutOfBounds(sumX, sumY)) {
                    continue;
                }
                if (x == -roomSize || x == roomSize || y == -roomSize || y == roomSize) {
                    if ((x == -roomSize && y == -roomSize) || (x == -roomSize && y == roomSize) || (x == roomSize && y == -roomSize) || (x == roomSize && y == roomSize)) {
                        grid[sumX][sumY].setState(1);
                        continue;
                    } 
                    perimeterPoints.add(new Point(sumX, sumY));
                }
                grid[sumX][sumY].setState(1);
            }
        }
        
        int chosenPoint = generator.nextInt(perimeterPoints.size() - 1);
        Point corridorPoint = perimeterPoints.get(chosenPoint);
        findCorridorDirection(corridorPoint.x, corridorPoint.y);
    }

    public void findCorridorDirection(int startX, int startY) {
        int sumX, sumY;
        String direction = "";

        for (int x = -1; x <= 1; x += 2) {
            sumX = startX + x;
            sumY = startY;

            if (isOutOfBounds(sumX, sumY)) {
                continue;
            }
            if (grid[sumX][sumY].isWall()) {
                if (x == -1) {
                    direction = "left";
                } else if (x == 1) {
                    direction = "right";
                }
            }
        }

        for (int y = -1; y <= 1; y += 2) {
            sumX = startX;
            sumY = startY + y;

            if (isOutOfBounds(sumX, sumY)) {
                continue;
            }
            if (grid[sumX][sumY].isWall()) {
                if (y == -1) {
                    direction = "up";
                } else if (y == 1) {
                    direction = "down";
                }
            }
        }
        extendCorridor(direction, startX, startY);
    }

    public void extendCorridor(String direction, int startX, int startY) {
        Random generator = new Random();
        // Possible corridor length from 4 to 10 tiles
        int corridorSize = generator.nextInt(6) + 4;
        int currentX = startX;
        int currentY = startY;
        
        for (int i = 0; i < corridorSize; i++) {
            if (direction.equals("up") && !isOutOfBounds(currentX, currentY - 1)) {
                currentY -= 1;
            } else if (direction.equals("right") && !isOutOfBounds(currentX + 1, currentY)) {
                currentX += 1;
            } else if (direction.equals("down") && !isOutOfBounds(currentX, currentY + 1)) {
                currentY += 1;
            } else if (direction.equals("left") && !isOutOfBounds(currentX - 1, currentY)) {
                currentX -= 1;
            } 
            grid[currentX][currentY].setState(1);
        }
        grid[currentX][currentY].setState(3);
    }

    public boolean isOutOfBounds(int x, int y) {
        if (x < 0 || y < 0){
            return true;
        } else if (x >= columns || y >= rows){
            return true;
        } else {
            return false;
        }
    }

    public void smooth(Tile tile) {
        if (tile.isCorridor() && tile.getFloorNeighbors() < 5) {
            createRoom(tile.x, tile.y);
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