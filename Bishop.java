package CSCI1933P2;

public class Bishop extends Piece {
    public Bishop(int row, int col, boolean isBlack) {
        super.row = row;
        super.col = col;
        this.isBlack = isBlack;

        if (isBlack) {
            super.representation = '\u265d';
            // Black
        }
        else{
            super.representation = '\u2657';
            //White
        }
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            if (board.verifyDiagonal(row, col, endRow, endCol)) {
                return true;
            }
        }
        return false;
    }
}
