package Model;

import java.util.*;

public class GameGrid {
    private static final int MAX_PLACEMENT_ATTEMPTS = 5000; // You can adjust this number based on your needs
    private int sizeOfBoard;
    private BoardItem[][] boardItems;
    private List<String> occupiedPositions;



    public GameGrid(int boardSize) {
        this.sizeOfBoard = boardSize;
        boardItems = new BoardItem[boardSize][boardSize];
        this.occupiedPositions = new ArrayList<>();
    }

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

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < sizeOfBoard && col >= 0 && col < sizeOfBoard;
    }

    public boolean canPlaceBoardItem(BoardItem boardItem, int row, int col, boolean isVertical) {
        int itemSize = boardItem.getSize();
        int gridSize = getSizeOfBoard();

        // Check if the item is within the board boundaries
        if (row < 0 || col < 0 || row >= gridSize || col >= gridSize) {
            System.out.println("Placement failed: Position (" + row + ", " + col + ") is outside the board boundaries.");
            return false;
        }

        // Check if the item extends beyond the board boundaries
        if (isVertical && (row + itemSize) > gridSize) {
            System.out.println("Placement failed: Vertical placement extends beyond the board boundaries at position (" + row + ", " + col + ").");
            return false;
        } else if (!isVertical && (col + itemSize) > gridSize) {
            System.out.println("Placement failed: Horizontal placement extends beyond the board boundaries at position (" + row + ", " + col + ").");
            return false;
        }

        // Check for overlap with existing ships
        for (int i = 0; i < itemSize; i++) {
            if (isVertical) {
                if (boardItems[row + i][col] != null) {
                    System.out.println("Placement failed: Overlap detected at position (" + (row + i) + ", " + col + ").");
                    return false; // Overlap detected
                }
            } else {
                if (boardItems[row][col + i] != null) {
                    System.out.println("Placement failed: Overlap detected at position (" + row + ", " + (col + i) + ").");
                    return false; // Overlap detected
                }
            }
        }
        return true;
    }



    public void placeBoardItem(BoardItem boardItem) {
        Random rand = new Random();

        for (int attempt = 0; attempt < MAX_PLACEMENT_ATTEMPTS; attempt++) {
            int row = rand.nextInt(sizeOfBoard);
            int col = rand.nextInt(sizeOfBoard);

            // Check if the item can be placed at the current position
            if (canPlaceBoardItem(boardItem, row, col, false)) {
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

    private boolean isOccupied(int row, int column) {
        return occupiedPositions.contains(row + "," + column);
    }


    public int getSizeOfBoard() {
        return sizeOfBoard;
    }

    public BoardItem[][] getGrid() {
        return boardItems;
    }


}
