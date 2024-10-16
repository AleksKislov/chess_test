package org.example;

import java.util.Scanner;

public class Main {

    public static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard(ChessPieceColor.White);
        
        placeChessPieces(board, ChessPieceColor.White);
        placeChessPieces(board, ChessPieceColor.Black);

        return board;
    }

    public static void placePawns(ChessBoard board, ChessPieceColor color) {
        int row = color == ChessPieceColor.White ? 1 : 6;

        for (int i = 0; i < board.board[row].length; i++) {
            board.board[row][i] = new Pawn(color);
        }
    }

    public static void placeChessPieces(ChessBoard board, ChessPieceColor color) {
        int row = color == ChessPieceColor.White ? 0 : 7;

        board.board[row][0] = new Rook(color);
        board.board[row][1] = new Horse(color);
        board.board[row][2] = new Bishop(color);
        board.board[row][3] = new Queen(color);
        board.board[row][4] = new King(color);
        board.board[row][5] = new Bishop(color);
        board.board[row][6] = new Horse(color);
        board.board[row][7] = new Rook(color);
        placePawns(board, color);
    }

    public static void main(String[] args) {

        ChessBoard board = buildBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
               Чтобы проверить игру надо вводить команды:
               'exit' - для выхода
               'replay' - для перезапуска игры
               'castling0' или 'castling7' - для рокировки по соответствующей линии
               'move 1 1 2 3' - для передвижения фигуры с позиции 1 1 на 2 3(поле это двумерный массив от 0 до 7)
               Проверьте могут ли фигуры ходить друг сквозь друга, корректно ли съедают друг друга, можно ли поставить шах и сделать рокировку?""");
        System.out.println();
        board.printBoard();
        while (true) {
            String s = scanner.nextLine();
            if (s.equals("exit")) break;
            else if (s.equals("replay")) {
                System.out.println("Заново");
                board = buildBoard();
                board.printBoard();
            } else {
                if (s.equals("castling0")) {
                    if (board.castling0()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.equals("castling7")) {
                    if (board.castling7()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.contains("move")) {
                    String[] a = s.split(" ");
                    try {
                        int line = Integer.parseInt(a[1]);
                        int column = Integer.parseInt(a[2]);
                        int toLine = Integer.parseInt(a[3]);
                        int toColumn = Integer.parseInt(a[4]);
                        if (board.moveToPosition(line, column, toLine, toColumn)) {
                            System.out.println("Успешно передвинулись");
                            board.printBoard();
                        } else System.out.println("Передвижение не удалось");
                    } catch (Exception e) {
                        System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
                    }

                }
            }
        }
    }
}