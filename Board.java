package CSCI1933P2;
public class Board {
    // Instance variables (add more if you need)
    private Piece[][] board;


    /**
     * Default Constructor
     */
    public Board() {
        // initialize the board to chessboard dimensions.
        board = new Piece[8][8];
    }

    // Accessor Methods

    /**
     * Gets the piece at a particular row and column of the board.
     *
     * @param row The row of the piece to be accessed.
     * @param col The column of the piece to be accessed.
     * @return The piece at the specified row and column of the board.
     */
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    /**
     * Sets the piece at a particular row and column of the board.
     *
     * @param row   The row to place the piece at.
     * @param col   The column to place the piece at.
     * @param piece The piece to place at the specified row and column.
     */
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Movement helper functions

    /**
     * Verifies that the source and destination of a move are valid by performing the following checks:
     * 1. ALL rows and columns provided must be >= 0.
     * 2. ALL rows and columns provided must be < 8.
     * 3. The start position of the move must contain a piece.
     * 4. The piece at the starting position must be the correct color.
     * 5. The destination must be empty OR must contain a piece of the opposite color.
     *
     * @param startRow The starting row of the move.
     * @param startCol The starting column of the move.
     * @param endRow   The ending row of the move.
     * @param endCol   The ending column of the move.
     * @param isBlack  The expected color of the starting piece.
     * @return True if the above conditions are met, false otherwise.
     */
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        // check all rows and col between 0 and 8
        if (!(startRow >= 0 && startRow < 8 && startCol >= 0 && startCol < 8)) {
            return false;
        }
        if (!(endRow >= 0 && endRow < 8 && endCol >= 0 && endCol < 8)) {
            return false;
        }

        Piece sPiece = board[startRow][startCol]; // check if there is a piece
        if (sPiece == null) {
            return false;
        }
        if (sPiece.getIsBlack() != isBlack) {
            return false;
        }
        Piece ePiece = board[endRow][endCol];
        if (ePiece != null) {
            if (ePiece.getIsBlack() == isBlack) {
                return false;  // if same color, return false
            }
        }

        return true;
    }

    /**
     * Verifies that the source and destination of a move are adjacent squares (within 1 square of each other)
     * Example, Piece P is adjacent to the spots marked X:
     * OOOOO
     * OXXXO
     * OXPXO
     * OXXXO
     * OOOOO
     *
     * @param startRow The starting row of the move.
     * @param startCol The starting column of the move.
     * @param endRow   The ending row of the move.
     * @param endCol   The ending column of the move.
     * @return True if the source and destination squares are adjacent, false otherwise.
     */
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
            if ((startRow + 1 == endRow) || (startRow == endRow) || (startRow - 1 == endRow)) { // first checks if end destination is within the adjacent rows
                if ((startCol + 1 == endCol) || (startCol == endCol) || (startCol - 1 == endCol)) { // finally check if end dest whithin adjacent column
                    return true;
                }
            }

        return false;
    }

    /**
     * Verifies that a source and destination are in the same row and that there are no pieces on squares
     * between the source and the destination.
     *
     * @param startRow The starting row of the move.
     * @param startCol The starting column of the move.
     * @param endRow   The ending row of the move.
     * @param endCol   The ending column of the move.
     * @return True if source and destination are in same row with no pieces between them, false otherwise.
     */
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow){
            return false;
        }
         // first checks same row
            if (endCol - startCol > 0){ // Checks if piece moves from left to right
            for (int i = startCol+1; i < endCol; i++) { // then checks for pieces in between
                if ((board[startRow][i] != null)) { // if one of them isnt null in row (There is in fact a piece), return false
                    return false;
                }
                }
            return true;
            }
            if (endCol - startCol < 0){ // checks if piece moves from right to left
                for (int i = startCol - 1; i > endCol; i-- ) {
                    if (board[startRow][i] != null) {
                        return false;
                    }
                }
                return true;
            }

        return true;
    }

    /**
     * Verifies that a source and destination are in the same column and that there are no pieces on squares
     * between the source and the destination.
     *
     * @param startRow The starting row of the move.
     * @param startCol The starting column of the move.
     * @param endRow   The ending row of the move.
     * @param endCol   The ending column of the move.
     * @return True if source and destination are in same column with no pieces between them, false otherwise.
     */
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol) {
            return false;
        }
        if (endRow > startRow) { // when moving downwards
            // first checks same row
            for (int i = startRow + 1; i < endRow; i++) { // then checks for pieces in between
                if (board[i][startCol] != null) { // if one of them isn't null in column (there is in fact a piece), return false
                    return false;
                }
            }
            return true;
        }
        if (endRow < startRow) { // when moving upwards
            for (int i = startRow - 1; i > endRow; i--) { // then checks for pieces in between
                if (board[i][startCol] != null) { // if one of them isn't null in column (there is in fact a piece), return false
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    /**
     * Verifies that a source and destination are on the same diagonal and that there are no pieces on squares
     * between the source and the destination.
     *
     * @param startRow The starting row of the move.
     * @param startCol The starting column of the move.
     * @param endRow   The ending row of the move.
     * @param endCol   The ending column of the move.
     * @return True if source and destination are on the same diagonal with no pieces between them, false otherwise.
     */
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);


        if (rowDiff == colDiff) { // Check if diagonal

            // Descending bottom left
            if (endRow > startRow && endCol < startCol) {
                for (int i = 1; i < rowDiff; i++) {
                    if (board[startRow + i][startCol - i] != null) {
                        return false;
                    }
                }
            }

            else if (endRow > startRow && endCol > startCol) { // Descending bottom right
                for (int i = 1; i < rowDiff; i++) {
                    if (board[startRow + i][startCol + i] != null) {
                        return false;
                    }
                }
            }

            else if (endRow < startRow && endCol > startCol) { // ascending top right

                for (int i = 1; i < rowDiff; i++) {
                    if (board[startRow - i][startCol + i] != null) {
                        return false;
                    }
                }
            }


            else if (endRow < startRow && endCol < startCol) {  // ascending top left
                for (int i = 1; i < rowDiff; i++) {
                    if (board[startRow - i][startCol - i] != null) {
                        return false;
                    }
                }
            }

            return true;
        }

        return false;
    }


    // Game functionality methods

    /**
     * Moves the piece from startRow, startCol to endRow, endCol if it is legal to do so.
     * IMPORTANT: Make sure to update the internal position of the piece, and the starting position of the piece to null!
     *
     * @param startRow The starting row of the move.
     * @param startCol The starting column of the move.
     * @param endRow   The ending row of the move.
     * @param endCol   The ending column of the move.
     * @return Whether the move was successfully completed or not. (Moves are not completed if they are not legal.)
     */
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (board[startRow][startCol] == null) { //checks if the starting place is empty
            return false;
        }
        else if (board[startRow][startCol].isMoveLegal(this,endRow,endCol)){
            this.setPiece(endRow, endCol, board[startRow][startCol]);
            board[startRow][startCol].setPosition(endRow, endCol); // update the internal position of the piece
            board[startRow][startCol] = null;
            return true;
        }
        return false;
    }



    /**
     * Returns true if there are fewer than TWO kings on the board.
     *
     * @return If the game is in a game over state.
     */
    public boolean isGameOver() {
        boolean whiteKing = false;
        boolean blackKing = false;

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (board[i][j] instanceof King && board[i][j].getIsBlack()) {//Checks if piece is King, and if black
                    blackKing = true;
                } else if (board[i][j] instanceof King && !(board[i][j].getIsBlack())) { //Checks if piece is King, and if white
                    whiteKing = true;
                }

            }
        }
        return !(whiteKing && blackKing); // if both are kings, it returns !(true) which is False. Otherwise, returns !(false). Which is True
    }

    /**
     * Sets all indexes in the board to null
     */
    public void clear() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                board[i][j] = null;
            }
        }
    }

    public void display() {
        System.out.print("\t\t\t");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "\t\t");
        }
        System.out.print("\n");
        for (int i = 0; i < 8; i++) {
            System.out.print("\t" + i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("|\t\t");
                } else {
                    System.out.print("|\t" + board[i][j] + "\t");
                }
            }
            System.out.print("|\n");
        }
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < 8; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }
}



