package Model.TypeOfTreasures;

import Model.BoardItem;

public class SpecialStone extends BoardItem {
    public SpecialStone(String name, int size) {
        super(name, size);
    }

    @Override
    public int[][] getShape() {
        return new int[][]{
                {1,1},
                {1,1}
        };
    }

    @Override
    public int getPoints() {
        return 1;
    }
}
