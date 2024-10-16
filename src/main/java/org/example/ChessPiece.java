package org.example;

public abstract class ChessPiece {
    ChessPieceColor color;
    Boolean check;

    public ChessPiece(ChessPieceColor color) {
        this.color = color;
        this.check = true;
    }

    public ChessPieceColor getColor() {
        return this.color;
    }

    abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    abstract String getSymbol();

    public boolean isNotValidMove(ChessBoard board, int line, int column, int toLine, int toColumn) {
        boolean isEqualToCurrentPosition = line == toLine && column == toColumn;
        boolean isWithinBoundaries = toLine < 8 && toLine >= 0 && toColumn < 8 && toColumn >= 0;

        ChessPiece newCellPiece = board.board[toLine][toColumn];
        boolean isEmptyCell = newCellPiece == null;
        boolean isAlly = !isEmptyCell && newCellPiece.color == color;
//        System.out.printf("isEqualToCurrentPosition %b, isWithinBoundaries %b, isAlly %b", isEqualToCurrentPosition, isWithinBoundaries, isAlly);
        return isEqualToCurrentPosition || !isWithinBoundaries || isAlly;
    }

    abstract public boolean hasObstructions(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public int getPositionChange(int currentPos, int toPos) {
        return Math.abs(toPos - currentPos);
    }
}
