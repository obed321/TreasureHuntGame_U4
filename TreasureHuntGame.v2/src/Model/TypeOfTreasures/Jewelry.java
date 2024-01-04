package Model.TypeOfTreasures;

import Model.BoardItem;

public class Jewelry extends BoardItem {
    public Jewelry(String name, int size) {
        super(name, size);
    }

    @Override
    public int[][] getShape() {
        return new int[][]{
                {1,0},
                {1,0}

        };
    }

    @Override
    public int getPoints() {
        return 1;
    }
}
