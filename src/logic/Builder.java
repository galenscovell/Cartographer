
/**
 * BUILDER INTERFACE
 * All builders utilize build(), smooth(), getGrid() and getTiles()
 */

package logic;

import java.util.List;


public interface Builder {
    public void build();
    public void smooth(Tile tile);
    public List<Tile> getTiles();
    public Tile[][] getGrid();
}