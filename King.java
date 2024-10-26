package CSCI1933P2;

public class King extends Piece {
    public King(int row, int col, boolean isBlack) {
        super.row = row;
        super.col = col;
        this.isBlack = isBlack;
        if (isBlack) {
            super.representation = '\u265A';
        } else {
            super.representation = '\u2654';
        }

    }


    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            if (board.verifyAdjacent(row, col, endRow, endCol)) {
                return true;
            }
        }
        return false;
    }
}
