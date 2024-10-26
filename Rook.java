package CSCI1933P2;

public class Rook extends Piece {
    public Rook(int row, int col, boolean isBlack) {
        super.row = row;
        super.col = col;
        this.isBlack = isBlack;

        if (isBlack) {
            super.representation = '\u265c';
            // Black
        } else {
            super.representation = '\u2656';
            //White
        }
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            if ((((board.verifyVertical(row, col, endRow, endCol)) || (board.verifyHorizontal(row, col, endRow, endCol) )))){ // Checks vertical or horizontal legality
                return true;
            }
        }
        return false;
    }
}
