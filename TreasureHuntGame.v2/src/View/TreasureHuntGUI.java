package View;

import Controller.TreasureHuntController;
import Model.BoardItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreasureHuntGUI extends JFrame {



    private GameCell[][] buttons;
    private JButton newGameBtn;
    private JLabel player1NameLabel;
    private JLabel player2NameLabel;
    private JLabel player1ScoreLabel;
    private JLabel player2ScoreLabel;
    private JLabel turnIndicatorLabel;
    private JList<String> leaderboardList; // DefaultListModel to manage the JList

    private TreasureHuntController controller;

    public TreasureHuntGUI(TreasureHuntController controller) {
        this.controller = controller;
        initComponents();
        setupUI();
        addListeners();
    }

    private void initComponents() {
        int gridSize = controller.getBoardSize();
        buttons = new GameCell[gridSize][gridSize];
        newGameBtn = new JButton("New Game");



        player1NameLabel = new JLabel("Player 1: ");
        player2NameLabel = new JLabel("Player 2: ");
        player1ScoreLabel = new JLabel("Score: 0");
        player2ScoreLabel = new JLabel("Score: 0");
        turnIndicatorLabel = new JLabel("Turn: ");
        leaderboardList = new JList<>(); // Initialize the DefaultListModel
        Font font = new Font("Courier New", Font.PLAIN, 13);
        leaderboardList.setFont(font);
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // Create a panel for player names and scores
        JPanel playerInfoPanel = new JPanel(new GridLayout(4, 1));
        playerInfoPanel.add(player1NameLabel);
        playerInfoPanel.add(player1ScoreLabel);
        playerInfoPanel.add(player2NameLabel);
        playerInfoPanel.add(player2ScoreLabel);
        playerInfoPanel.add(turnIndicatorLabel);
        playerInfoPanel.add(newGameBtn);
        // Create a panel for the grid of buttons
        JPanel gridPanel = new JPanel(new GridLayout(buttons.length, buttons[0].length));
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j] = new GameCell();
                buttons[i][j].addActionListener(new ButtonActionListeners());
                gridPanel.add(buttons[i][j]);
            }
        }

        // Create a panel for the leaderboard
        JPanel leaderboardPanel = new JPanel(new BorderLayout());
        leaderboardPanel.add(new JScrollPane(leaderboardList), BorderLayout.CENTER);

        JScrollPane leaderboardScrollPane = new JScrollPane(leaderboardList);
        leaderboardScrollPane.setPreferredSize(new Dimension(200, getHeight()));  // Adjust the width as needed

        leaderboardPanel.add(leaderboardScrollPane, BorderLayout.CENTER);


        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(playerInfoPanel, BorderLayout.WEST);
        infoPanel.add(gridPanel, BorderLayout.CENTER);

        // Create a panel to hold player names, scores, and the grid
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(leaderboardPanel, BorderLayout.EAST); // Move leaderboardPanel to the right

        add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setGridEnabled(false);
        newGameBtn.setSize(new Dimension(100, 100));
        //newGameBtn.setEnabled(true);

    }




    public void setGridEnabled(boolean enabled) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j].setEnabled(enabled);
            }
        }
    }

    private void addListeners() {
        ActionListener listener = new ButtonActionListeners();
        newGameBtn.addActionListener(listener);
    }

    public void updatePlayerNames(String player1Name, String player2Name) {
        player1NameLabel.setText("Player 1: " + player1Name);
        player2NameLabel.setText("Player 2: " + player2Name);
    }

    public void updatePlayerScores(int player1Score, int player2Score) {
        player1ScoreLabel.setText("Score: " + player1Score);
        player2ScoreLabel.setText("Score: " + player2Score);
    }

    public void updateLeaderboard(String[] stringList) {
        leaderboardList.setListData(stringList); // Add entry to the leaderboard
    }

    public void displayTurnIndicator(String name) {

        turnIndicatorLabel.setText("Turn: " + name);
    }





    public void updateFoundObjects(String playerName, BoardItem foundObject) {
        String message = playerName + " found: " + foundObject.getName() +
                ". Current size: " + foundObject.getSize();

        JOptionPane.showMessageDialog(null, message, "Found Object", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showMessage(String s) {

        JOptionPane.showMessageDialog(null, "You have hit " + s );
    }

    public void boardItemCompletlyFound(String name) {

        JOptionPane.showMessageDialog(null,"You found "+ name);

    }


    class ButtonActionListeners implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();

            if(clickedButton == newGameBtn) {
                controller.newGame();
            }



            // Get the row and column of the clicked button
            int gridSize = buttons.length;
            int row = -1, col = -1;
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[0].length; j++) {
                    if (e.getSource() == buttons[i][j]) {
                        buttons[i][j].setEnabled(false);
                        controller.inspectBoard(i, j);
                    }
                }
            }

            // Call the controller method to handle the button click
            // controller.handleButtonClick(row, col);

            // Update the turn indicator after each button click
            displayTurnIndicator(controller.getCurrentPlayer().getName());

            // Disable only the clicked button after handling the button click
            clickedButton.setEnabled(false);

            newGameBtn.setEnabled(true);
        }
    }





    private void handleButtonClick(JButton clickedButton) {
    }





    public void resetGameBoardButtons() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                buttons[i][j].setEnabled(true);
                buttons[i][j].resetColor();
                setGridEnabled(true);

            }
        }
    }


    public void setPlayerNames(String player1Name, String player2Name) {
        player1NameLabel.setText("Player 1: " + player1Name);
        player2NameLabel.setText("Player 2: " + player2Name);
    }



    public void shotHit(int x, int y) {
        buttons[x][y].setHit(true);
    }

    public void shotMissed(int x, int y) {
        buttons[x][y].setHit(false);
    }


    public JButton getNewGameBtn() {
        return newGameBtn;
    }




}