package Model;

import java.util.HashSet;
import java.util.Set;

public class Player
{
    private String name;
    private int score;



    public Player(String name, int score) {
        this.score = score;
        this.name = name;

    }



    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // Add method to increase the player's score
    public void increaseScore(int points) {
        score += points;
    }

    // Add method to reduce the player's score
    public void reduceScore() {
      score--;
    }

    @Override
    public String toString() {
        return String.format("%s %d", name, score);
    }

    public void setScore(int score) {
        this.score = score;
    }


}
