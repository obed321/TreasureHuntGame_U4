package Model;

public class Trap extends BoardItem {

    private String name;
    private int size;
    public Trap(String name, int size) {
        super(name, size);

    }

    @Override
    public int[][] getShape() {
        return new int[][]{{0,1}};
    }

    @Override
    public int getPoints() {
        return -1;
    }



}
