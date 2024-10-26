package CSCI1933P2;

public class Queen extends Piece {
    public Queen(int row, int col, boolean isBlack) {
        super.row = row;
        super.col = col;
        this.isBlack = isBlack;
        if (isBlack) {
            super.representation = '\u265B';
        } else {
            super.representation = '\u2655';
        }
    }

        public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(row,col,endRow,endCol,isBlack)) {
            if (((board.verifyVertical(row, col, endRow, endCol)) || (board.verifyHorizontal(row, col, endRow, endCol)) || (board.verifyDiagonal(row, col, endRow, endCol)))) {// Checks all legality paths
                return true;
            }
        }
        return false;
    }
}


