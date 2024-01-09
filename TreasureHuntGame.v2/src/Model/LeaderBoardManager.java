package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class is a manager for the leaderboard and handles how to add player to the list of leaderboard
 *
 */
public class LeaderBoardManager {

        private List<Player> players = new ArrayList<>();

        //This metod att player score to the arrayList
        public void addPlayerToScoreBoard(String name, int score) {
            if (!name.equals("")) {
                players.add(new Player(name,score));
                sortLeaderBoard();
            }

        }

        //This metod sorts the leaderboard and shows the higest score on top
        private void sortLeaderBoard() {
            players.sort(Comparator.comparingInt(Player::getScore).reversed());
        }



        // This method returns the leaderboard list
        //This metod returns the leaderboard list
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
