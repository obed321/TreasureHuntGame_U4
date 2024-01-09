package Model.TypeOfTreasures;

import Model.BoardItem;
/**
 * This class is one of the boarditems.
 * This class is used to represent how a boarditem should look like
 * @author Obed Owusu
 */
public class TreasureChest extends BoardItem {
    /**
     * Constructor for the class
     * @param name of the board item
     * @param size size of the board item
     */
    public TreasureChest(String name, int size) {
        super(name, size);
    }
    /**
     * This metod is used to represent how the shape of the item look like
     * @return shape of the item
     */
    @Override
    public int[][] getShape() {
        return new int[][]{
                {1,1},
                {1,1},
                {1,0},
        };
    }
    /**
     * Every boarditem that extends this class can use this metod to set how much point you
     * get for finding the boarditem
     * @return point for finding the boarditem
     * @author Obed Owusu
     */
    @Override
    public int getPoints() {
        return 2;
    }
}
