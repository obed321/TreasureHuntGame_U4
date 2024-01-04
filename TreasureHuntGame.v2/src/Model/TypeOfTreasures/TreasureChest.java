package Model.TypeOfTreasures;

import Model.BoardItem;

public class TreasureChest extends BoardItem {
    public TreasureChest(String name, int size) {
        super(name, size);
    }

    @Override
    public int[][] getShape() {
        return new int[][]{
                {1,1},
                {1,1},
                {1,0},
        };
    }

    @Override
    public int getPoints() {
        return 2;
    }
}
