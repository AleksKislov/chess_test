package org.example;

public class Pawn extends ChessPiece {

    Pawn(ChessPieceColor color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isNotValidMove(chessBoard, line, column, toLine, toColumn)) return false;
        if (hasObstructions(chessBoard, line, column, toLine, toColumn)) return false;

        boolean isFirstMove = this.check;
        int lineChange = this.color == ChessPieceColor.White ? toLine - line : line - toLine;
        boolean isMovingForward1Or2Lines = lineChange > 0 && lineChange < 3;

        if (!isMovingForward1Or2Lines) return false;
        if (!isFirstMove && lineChange > 1) return false;

        int columnChange = getPositionChange(column, toColumn);
        if (columnChange > 1) return false;

        boolean isDiagonalMove = columnChange == 1;

        ChessPiece piece = chessBoard.board[toLine][toColumn];
        boolean isEmptyCell = piece == null;
        boolean isEnemy = !isEmptyCell && piece.color != color;

        if (!isDiagonalMove && isEnemy) return false;
        if (isDiagonalMove && lineChange > 1) return false;
        if (isDiagonalMove && !isEnemy) return false;

        return true;
    }

    @Override
    public boolean hasObstructions(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int nextLine = this.color == ChessPieceColor.White ? line + 1 : line - 1;
        int columnChange = getPositionChange(column, toColumn);
        return columnChange == 0 && chessBoard.board[nextLine][column] != null;
    }

    @Override
    String getSymbol() {
        return "P";
    }
}
