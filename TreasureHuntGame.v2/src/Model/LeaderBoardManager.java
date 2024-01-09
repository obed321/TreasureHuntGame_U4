package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class is a manager for the leaderboard and handles how to add player to the list of leaderboard,
 * and how to retrieve the leaderboard and sort it.
 * @author Obed Owusu
 */
public class LeaderBoardManager {

        private List<Player> players = new ArrayList<>();

    /**
     * This metod adds instance of player to the list of players,
     * by taking in name and score
     * @param name player enter name and it's put into this variable
     * @param score player enter score and it's put into this variable
     * @author Obed Owusu
     */
    public void addPlayerToScoreBoard(String name, int score) {
            if (!name.equals("")) {
                players.add(new Player(name,score));
                sortLeaderBoard();
            }

        }

    /**
     * This metod sorts the list of players with the help of sort and comparator
     * The player score are retrived from the list and is sorted in descending order
     * @author Obed Owusu
     */
    private void sortLeaderBoard() {
            players.sort(Comparator.comparingInt(Player::getScore).reversed());
        }


    /**
     * This metod creates new arraylist of type player and put the list of player into it.
     * New string list is then created with the same size as playerlist.
     * Then playerlist is looped and player in specifik index is put into player varible.
     * The player varible is the onverted to string and put into leaderBoard
     * @return list of players in string
     * @author Obed Owusu
     */
    public String[] getLeaderBoard() {
            List<Player> playerList = new ArrayList<>(players);
            String[] leaderBoard = new String[playerList.size()];

            int index = 0;
            for (Player player : playerList) {
                leaderBoard[index++] = player.toString();
            }

            return leaderBoard;
        }

}
