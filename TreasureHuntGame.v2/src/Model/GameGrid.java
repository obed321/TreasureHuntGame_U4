package Model;

import java.util.*;

/**
 * This class acts as the gamegrid manager, and has the necessary metod, for placing boarditem on grid,
 * checking for valid and invalid postion
 * @author Obed Owusu
 */
public class GameGrid {
    private static final int maxAttempt = 5000; // You can adjust this number based on your needs
    private int sizeOfBoard;
    private BoardItem[][] boardItems;
    private List<String> occupiedPositions;


    /**
     * This is the constructor for the gamegrid class,
     * it takes in size of the grid from controller and creats grid.
     * @param boardSize The size that the player wants the gird to be is inputed here
     * @author Obed Owusu
     */
    public GameGrid(int boardSize) {
        this.sizeOfBoard = boardSize;
        boardItems = new BoardItem[boardSize][boardSize];
        this.occupiedPositions = new ArrayList<>();
    }

    /**
     * This metod add ships to the gird,
     * it checks so that the postion the boarditems is being placed in is valid and not null.
     * The boarditem is then placed and marked as occupied,
     * if the position is not valid, its marked as not valid postion
     * @param boardItem boarditem that you want to place
     * @param row position of row
     * @param col postition of column
     * @author Obed Owusu
     */
    public void addBoardItemToGrid(BoardItem boardItem, int row, int col) {
        if (isValidPosition(row, col) && boardItem != null && boardItems[row][col] == null) {
            boardItems[row][col] = boardItem;
            markPositionAsOccupied(row, col);
        } else {
            System.out.println("Cannot place " + boardItem.getName() + " at position (" + row + ", " + col + ")");
            if (!isValidPosition(row, col)) {
                System.out.println("Invalid position: Out of bounds.");
            } else {
                System.out.println("Position already occupied.");
            }
        }
    }

    /**
     * This metod checks so that the postition the boarditem is being placed is valid
     * and returns if it's true or not
     * @param row position of row
     * @param col position of column
     * @return true if boardposition is valid
     */
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < sizeOfBoard && col >= 0 && col < sizeOfBoard;
    }

    /**
     * This metod checks so that boarditems can be placed on the grid.
     * The metod take in the size of the board and item
     * Then uses it to measure wether the objects can be place on the board or not.
     * This metod also check so that objects dont overlap on existing objects
     * @param boardItem The item that is to be placed
     * @param row position of row
     * @param col position of column
     * @return true if overlap was not detected
     * @author Obed Owusu
     */
    public boolean canPlaceBoardItem(BoardItem boardItem, int row, int col) {
        int itemSize = boardItem.getSize();
        int gridSize = getSizeOfBoard();

        // Check if the item is within the board boundaries
        if (row < 0 || col < 0 || row >= gridSize || col >= gridSize) {
            System.out.println("Placement failed: Position (" + row + ", " + col + ") is outside the board boundaries.");
            return false;
        }

        // Check if the item extends beyond the board
        if ((row + itemSize) > gridSize) {
            System.out.println("Placement extends beyond the board boundaries at position (" + row + ", " + col + ").");
            return false;
        } else if ((col + itemSize) > gridSize) {
            System.out.println("Placement extends beyond the board boundaries at position (" + row + ", " + col + ").");
            return false;
        }

        // Check for overlap with existing boarditems
        for (int i = 0; i < itemSize; i++) {

                if (boardItems[row + i][col] != null) {
                    System.out.println("Placement failed: Overlap detected at position (" + (row + i) + ", " + col + ").");
                    return false; // Overlap detected
                }

                else if (boardItems[row][col + i] != null) {
                    System.out.println("Placement failed: Overlap detected at position (" + row + ", " + (col + i) + ").");
                    return false; // Overlap detected
                }

        }

        return true; // Overlap not detected
    }



    public void placeBoardItem(BoardItem boardItem) {
        Random rand = new Random();

        for (int attempt = 0; attempt < maxAttempt; attempt++) {
            int row = rand.nextInt(sizeOfBoard);
            int col = rand.nextInt(sizeOfBoard);

            // Check if the item can be placed at the current position
            if (canPlaceBoardItem(boardItem, row, col)) {
                placeBoardItemWithShape(boardItem, row, col);
                break;  // Exit the loop if the placement is successful
            }
        }
    }

    private void placeBoardItemWithShape(BoardItem boardItem, int row, int col) {
        int[][] shape = boardItem.getShape();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] == 1) {
                    addBoardItemToGrid(boardItem, row + i, col + j);
                }
            }
        }
    }





    public void clearBoardObject() {
        for (int row = 0; row < sizeOfBoard; row++) {
            for (int col = 0; col < sizeOfBoard; col++) {
                if (boardItems[row][col] instanceof BoardItem) {
                    boardItems[row][col] = null;
                    clearPosition(row, col);
                }
            }
        }
    }

    private void clearPosition(int row, int col) {
        occupiedPositions.remove(row + "," + col);
    }



    private void markPositionAsOccupied(int row, int col) {
        occupiedPositions.add(row + "," + col);
    }



    public int getSizeOfBoard() {
        return sizeOfBoard;
    }

    public BoardItem[][] getGrid() {
        return boardItems;
    }


}
