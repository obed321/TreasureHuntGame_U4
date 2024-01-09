package Model;

/**
 * This class is an important class that acts as a template for how board-item should look like
 * @author Obed Owusu
 */
public abstract class BoardItem {

    private int size;
    private String name;

    /**
     * This is the constructor for the class
     * @param name of the boarditem is put here
     * @param size of the boarditem is put here
     * @author Obed Owusu
     */
    public BoardItem(String name, int size) {
        this.size = size;
        this.name = name;
    }

    /**
     * This metod return the name of the boarditem
     * @return name of the boarditem
     * @author Obed Owusu
     */
    public String getName(){
        return name;
    }

    /**
     * This metod enable you to set name of the boarditem
     * @param name
     * @author Obed Owusu
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This metod return size of the boarditem
     * @return size of the boarditem
     * @author Obed Owusu
     */
    public int getSize() {
        return size;
    }

    /**
     * This metod enable you to set size of the boarditem
     * @param size
     * @author Obed Owusu
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * This is an abstract metod that every boarditem that extends this class can use to set their own shape
     * @return the shape of the boarditem
     * @author Obed Owusu
     */
    public abstract int [][] getShape();

    /**
     * Every boarditem that extends this class can use this metod to set how much point you
     * get for finding the boarditem
     * @return point for finding the boarditem
     * @author Obed Owusu
     */
    public abstract int getPoints();


    /**
     * This metod is used to format name and size
     * @return string format of name and size
     * @author Obed Owusu
     */
    public String toString() {
        String info = String.format("Current size of %s is %d", name, size);
        return info;
    }
}
