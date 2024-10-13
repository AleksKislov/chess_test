package org.example;

public abstract class ChessPiece {
    String color;
    Boolean check;

    public ChessPiece(String color) {
        this.color = color;
        this.check = true;
    }

    public String getColor() {
        return this.color;
    }

    abstract Boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    abstract String getSymbol();
}
