package CSCI1933P2;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Board boardy = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", boardy);
        Scanner input = new Scanner(System.in);

        boolean gameTurn = true;

        while (!(boardy.isGameOver())) {
            boardy.display();

            if (gameTurn) {
                System.out.println("It is White's turn to move, Enter move in the following order: StartRow, Start Column, End Row, End Column");
                int startRow = input.nextInt();
                int startColumn = input.nextInt();
                int endRow = input.nextInt();
                int endColumn = input.nextInt();

                if (boardy.getPiece(startRow, startColumn) != null && !(boardy.getPiece(startRow, startColumn).getIsBlack())) {
                    if (boardy.movePiece(startRow, startColumn, endRow, endColumn)) {
                        gameTurn = false;
                    } else {
                        System.out.println("try again");
                    }
                } else {
                    System.out.println("make sure you're moving white");
                }
            } else {
                System.out.println("It is Black's turn to move, Enter move in the following order: StartRow, Start Column, End Row, End Column");
                int startRow2 = input.nextInt();
                int startColumn2 = input.nextInt();
                int endRow2 = input.nextInt();
                int endColumn2 = input.nextInt();

                if (boardy.getPiece(startRow2, startColumn2) != null && boardy.getPiece(startRow2, startColumn2).getIsBlack()) {
                    if (boardy.movePiece(startRow2, startColumn2, endRow2, endColumn2)) {
                        gameTurn = true;
                    } else {
                        System.out.println("Invalid move, try again.");
                    }
                } else {
                    System.out.println("Make sure you're moving black");
                }
            }
        }

        System.out.println("Game over!");
    }

}
