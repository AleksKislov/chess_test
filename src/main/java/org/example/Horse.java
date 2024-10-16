package org.example;

public class Horse extends ChessPiece {

    Horse(ChessPieceColor color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isNotValidMove(chessBoard, line, column, toLine, toColumn)) return false;
        if (hasObstructions(chessBoard, line, column, toLine, toColumn)) return false;

        int columnChange = getPositionChange(column, toColumn);
        int lineChange = getPositionChange(line, toLine);

        if (columnChange == 2 && lineChange == 1) {
            return true;
        } else return columnChange == 1 && lineChange == 2;
    }

    @Override
    public boolean hasObstructions(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return false;
    }

    @Override
    String getSymbol() {
        return "H";
    }
}
