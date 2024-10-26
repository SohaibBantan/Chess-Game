package CSCI1933P2;

public class Knight extends Piece {
    public Knight(int row, int col, boolean isBlack) {
        super.row = row;
        super.col = col;
        this.isBlack = isBlack;

        if (isBlack) {
            super.representation = '\u265e';
            // Black
        }
        else{
            super.representation = '\u2658';
            //White
        }
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            if (Math.abs(row - endRow) == 1 && Math.abs(col - endCol) == 2) { //checks if knight moves L shape
                return true;
            }
            else if (Math.abs(row - endRow) == 2 && Math.abs(col - endCol) == 1){// Also checks same thing
                return true;
            }
        }
        return false;
    }
}

