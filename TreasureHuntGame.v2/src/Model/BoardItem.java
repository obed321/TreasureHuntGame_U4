package Model;

public abstract class BoardItem {

    private int size;
    private String name;

    public BoardItem(String name, int size) {
        this.size = size;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public abstract int [][] getShape();

    public abstract int getPoints();



    public String toString() {
        String info = String.format("Current size of %s is %d", name, size);
        return info;
    }
}
