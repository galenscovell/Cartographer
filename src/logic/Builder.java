
/**
 * BUILDER INTERFACE
 * All builders utilize build(), smooth(), getGrid() and getTiles()
 */

package logic;

import automata.Tile;

import java.util.ArrayList;


public interface Builder {
    public void build();
    public void smooth(Tile tile);
    public int[][] getGrid();
    public ArrayList<Tile> getTiles();
}