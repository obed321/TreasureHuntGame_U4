package Model.TypeOfTreasures;

import Model.BoardItem;

public class Gem extends BoardItem {

    public Gem(String name, int size) {
        super(name, size);
    }

    @Override
    public int getPoints() {
        return 1;
    }

    public int [][] getShape() {
        return new int[][]{{1}};
    }
}
