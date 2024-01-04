package Model.TypeOfTreasures;

import Model.BoardItem;

public class GoldBar extends BoardItem {
    public GoldBar(String name, int size) {
        super(name, size);
    }

    @Override
    public int[][] getShape() {

        return new int[][]{
                {1, 0},
                {1, 1}

        };
    }

    @Override
    public int getPoints() {
        return 1;
    }
}
