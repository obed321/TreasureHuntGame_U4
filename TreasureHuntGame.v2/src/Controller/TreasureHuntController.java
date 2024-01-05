package Controller;

import Model.*;
import Model.TypeOfTreasures.*;

import View.TreasureHuntGUI;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TreasureHuntController {
    private Player player1;
    private Player player2;

    private Player currentPlayer;
    private TreasureHuntGUI view;
    private GameGrid gameGrid;
    private LeaderBoardManager leaderBoardManager;

    //This is the instance for the traps


    //This is the instance for the treasures
    private Gem gem;
    private GoldBar goldBar;
    private Jewelry jewelry;
    private SpecialStone specialStone;
    private TreasureChest treasureChest;



    public TreasureHuntController() {
        // Initialize player1 and player2 first
        player1 = new Player("DefaultPlayer1", 0);
        player2 = new Player("DefaultPlayer2", 0);

        gameGrid = new GameGrid(16);
        view = new TreasureHuntGUI(this);
        currentPlayer = player1;  // Now currentPlayer is correctly initialized
        leaderBoardManager = new LeaderBoardManager();

        startGame();  // Move this line here to ensure player1 and player2 are initialized
        placeTreasuresAndTraps();
        switchPlayerTurn();
    }

    /**
     * This metod starts the game by asking the user for the player names and then starting the other startGame method
     * @author Obed Owusu
     */
    public void startGame() {
        // Prompt the user for player names
        String player1Name = JOptionPane.showInputDialog("Enter Player 1 name:");
        String player2Name = JOptionPane.showInputDialog("Enter Player 2 name:");

        // Start the game with obtained player names
        startGame(player1Name, player2Name);


    }

    /**
     * This metod sets player names in player1 and player2 instance variables
     * updateView metod is then called to update the gui to show the player names
     * leaderboard from text file is fetched and displated on the view.
     * @author Obed Owusu
     * @param player1Name Player 1 name from Joption Dialog from startGame method
     * @param player2Name Player 2 name from Joption Dialog from startGame method
     */
    public void startGame(String player1Name, String player2Name) {


        // Set the player names and board size in the game grid
        player1.setName(player1Name);
        player2.setName(player2Name);
        //placeTreasuresAndTraps();

        // Update the view based on the initial game state, including the buttons
        updateView(player1.getName(), player2.getName());

        String [] leaderboard = loadLeaderBoardFromFile("C:\\Users\\obed2\\IdeaProjects\\TreasureHuntGame_U4\\TreasureHuntGame.v2\\PlayersLeaderBoardList.txt");
        view.updateLeaderboard(leaderboard);
    }


    /**
     * This metod updates the view with the provided player names
     * The gameboard buttons are then set to enabled, so that you can click on them
     * @author Obed Owusu
     * @param player1Name Player 1 name from Joption Dialog from startGame method
     * @param player2Name Player 2 name from Joption Dialog from startGame method
     */
    public void updateView(String player1Name, String player2Name) {
        // Update the player names in the GUI using the provided method
        view.updatePlayerNames(player1Name, player2Name);

        // Update the view based on the initial game state, including the buttons
        view.setGridEnabled(true);
    }



    /**
     * This metod place the treasures and traps instance on the game grid
     * with selected names and size
     * @author Obed Owusu
     */
    public void placeTreasuresAndTraps() {

        gem = new Gem("Gem", 1);
        gameGrid.placeBoardItem(gem);


        jewelry = new Jewelry("jewelry", 2);
        gameGrid.placeBoardItem(jewelry);


        goldBar = new GoldBar("Gold bar", 3);
        gameGrid.placeBoardItem(goldBar);


        specialStone = new SpecialStone("Special stone", 4);
        gameGrid.placeBoardItem(specialStone);


        treasureChest = new TreasureChest("Treasure chest", 5);
        gameGrid.placeBoardItem(treasureChest);


        Trap trap1 = new Trap("Trap", 1);
        gameGrid.placeBoardItem(trap1);


        Trap trap2 = new Trap("Trap", 1);
        gameGrid.placeBoardItem(trap2);


        Trap trap3 = new Trap("Trap", 1);
        gameGrid.placeBoardItem(trap3);


    }


    /**
     * This method is resonsible for looping through the gamegrid and determining of all instances of treasures are found
     * but not traps.
     * @return true if all treasures are found, false otherwise
     * @author Obed Owusu
     */
    public boolean areAllTreasuresFound() {
        for (int row = 0; row < gameGrid.getSizeOfBoard(); row++) {
            for (int col = 0; col < gameGrid.getSizeOfBoard(); col++) {
                BoardItem boardItem = gameGrid.getGrid()[row][col];
                if (boardItem instanceof BoardItem && boardItem.getSize() > 0 && !(boardItem instanceof Trap)) {
                    return false; // At least one treasure is not found
                }
            }
        }
        return true; // All treasures are found
    }


    /**
     * This method is responsible for switching the current player's turn and display the currentplayer on view
     * There is also and if statment that is triggered if all treasures are found, then gameover method is activated.
     * @author Obed Owusu
     */
    public void switchPlayerTurn() {
        // Switch turns between player1 and player2
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        view.displayTurnIndicator(currentPlayer.getName());  // Update the view with the current player's turn

        if (areAllTreasuresFound()) {
            handleGameOver();
        }
    }


    /**
     * This method is activated when the game is over.
     * The player with the highest score is displayed with JOption Dialog and their score is saved in leaderboard text file
     * You are then showed play again or exit the game.
     * Depending on the user's choice, new game is screated or application is exited.
     * @author Obed Owusu
     */
    public void handleGameOver() {
        Player winner = (player1.getScore() > player2.getScore()) ? player1 : player2;
        String message = "Game Over! " + winner.getName() + " wins with a score of " + winner.getScore() + ".";
        leaderBoardManager.addPlayerToScoreBoard(winner.getName(), winner.getScore());
        saveLeaderBoardToFile("PlayersLeaderBoardList.txt", leaderBoardManager.getLeaderBoard());
        // Define buttons for the JOptionPane
        Object[] options = {"Play Again", "Exit"};

        int choice = JOptionPane.showOptionDialog(
                null,
                message,
                "Game Over",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );


        if (choice == 0) {

            newGame();
        } else if (choice == 1) {

            System.exit(0);
        }
    }


    /**
     * This method is responsible for starting a new game.
     * The player with the highest score is displayed with JOption Dialog and their score is saved in leaderboard
     * Both players' scores are reset to 0.
     * view is updated to show the new score of both players
     * grid is cleared and the buttons are reset to thier the original state.
     * The player are then ask for new names, these names are then set and diplayed in view.
     * The leaderboard from textfile is displayed in the view.
     * New treasure and trap instances are placed on the grid.
     * @author Obed Owusu
     */
    public void newGame() {
        // Save winner's info before resetting the game
        Player winner = (player1.getScore() > player2.getScore()) ? player1 : player2;
        leaderBoardManager.addPlayerToScoreBoard(winner.getName(), winner.getScore());
        saveLeaderBoardToFile("PlayersLeaderBoardList.txt", leaderBoardManager.getLeaderBoard());

        // Display a message with the winner's info
        JOptionPane.showMessageDialog(null, "Game Over! " + winner.getName() +
                " wins with a score of " + winner.getScore() + ".");


        player1.setScore(0);
        player2.setScore(0);


        view.updatePlayerScores(player1.getScore(), player2.getScore());


        gameGrid.clearBoardObject();


        view.resetGameBoardButtons();
        view.setGridEnabled(true);


        String player1Name = JOptionPane.showInputDialog("Enter Player 1 name:");
        String player2Name = JOptionPane.showInputDialog("Enter Player 2 name:");


        player1.setName(player1Name);
        player2.setName(player2Name);


        updateView(player1.getName(), player2.getName());


        currentPlayer = player1;
        view.displayTurnIndicator(currentPlayer.getName());


        String[] leaderboard = loadLeaderBoardFromFile("PlayersLeaderBoardList.txt");
        view.updateLeaderboard(leaderboard);


        gameGrid = new GameGrid(16);


        placeTreasuresAndTraps();



    }





    public void inspectBoard(int x, int y) {
        BoardItem boardItem = gameGrid.getGrid()[x][y];

        if (boardItem != null) {
            handleHit(boardItem, x, y);
            switchPlayerTurn();
        } else {
            handleMiss(x, y);
            switchPlayerTurn();
        }
    }

    //This metod handels shots that are hit
    private void handleHit(BoardItem boardItem, int x, int y) {
        view.shotHit(x, y);

        boardItem.setSize(boardItem.getSize() - 1);

        if (boardItem.getSize() == 0) {
            // Object completely found
            view.boardItemCompletlyFound(boardItem.getName());

            // Check if it's a treasure and update scores
            if (boardItem instanceof Trap) {
                // Check if the player has points to deduct
                if (currentPlayer.getScore() > 0) {
                    // Deduct 1 point for hitting a trap
                    currentPlayer.reduceScore();
                    view.showMessage(currentPlayer.getName() + " hit a trap! Points deducted: 1");
                    view.updatePlayerScores(player1.getScore(), player2.getScore());
                } else {
                    // Switch to the other player if the current player has 0 points
                    view.showMessage("Trap hit, current player" + currentPlayer.getName() + " has 0 points. Switching turn.");
                    switchPlayerTurn();
                    switchPlayerTurn();
                }
            } else {
                // It's a treasure
                //  currentPlayer.addFoundObject(boardItem);
                currentPlayer.increaseScore(boardItem.getPoints());
                view.updateFoundObjects(currentPlayer.getName(), boardItem);
                view.updatePlayerScores(player1.getScore(), player2.getScore());
            }
        } else {
            // Object not completely found
            view.showMessage(boardItem.getName() + " has been hit! " + boardItem.getSize() + " lives left.");
        }
    }





    private void handleMiss(int x, int y) {
        view.shotMissed(x, y);
    }



    public Object[][] getGrid() {
        return gameGrid.getGrid();

    }

    public int getBoardSize() {
        return gameGrid.getSizeOfBoard();
    }


    public void saveLeaderBoardToFile(String fileName, String[] leaderBoard) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            for (String entry : leaderBoard) {
                writer.println(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] loadLeaderBoardFromFile(String fileName) {
        List<String> leaderBoard = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                leaderBoard.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaderBoard.toArray(new String[0]);
    }




    public Player getCurrentPlayer() {
        return currentPlayer;
    }


}