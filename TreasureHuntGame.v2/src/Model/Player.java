package Model;

import java.util.HashSet;
import java.util.Set;

/**
 * This class acts as a tamplate on how a player instance should look like.
 * @author Obed Owusu
 */
public class Player
{
    private String name;
    private int score;


    /**
     * Constructor for the class.
     * @param name The player name is put into this varible
     * @param score The player score is put into this varible
     */
    public Player(String name, int score) {
        this.score = score;
        this.name = name;

    }


    /**
     * This metod returns the score of the player
     * @return score of player
     * @author Obed Owusu
     */
    public int getScore() {
        return score;
    }

    /**
     * This metod return the name of the player
     * @return name of the player
     * @author Obed Owusu
     */
    public String getName() {
        return name;
    }

    /**
     * This metod make it possible to setname of the player
     * @param name The player puts thier name here
     * @author Obed Owusu
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * This metod increases the score of the player
     * @param points The point that you want to add to the score
     * @author Obed Owusu
     */
    public void increaseScore(int points) {
        score += points;
    }

    /**
     * This metod reduces the score of the player
     * @author Obed Owusu
     */
    public void reduceScore() {
      score--;
    }

    /**
     * This is a toString for how the name and score should be displayed
     * @return string format of name and score
     * @author Obed Owusu
     */
    @Override
    public String toString() {
        return String.format("%s %d", name, score);
    }

    /**
     * This enables you to set score of the player
     * @param score
     * @author Obed Owusu
     */
    public void setScore(int score) {
        this.score = score;
    }


}
