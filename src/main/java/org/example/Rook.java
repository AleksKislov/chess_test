package org.example;

public class Rook extends ChessPiece{

    Rook(ChessPieceColor color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isNotValidMove(chessBoard, line, column, toLine, toColumn)) return false;
        if (hasObstructions(chessBoard, line, column, toLine, toColumn)) return false;

        int columnChange = getPositionChange(column, toColumn);
        int lineChange = getPositionChange(line, toLine);

        return (lineChange == 0 && columnChange > 0) || (columnChange == 0 && lineChange > 0);
    }

    @Override
    public boolean hasObstructions(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        boolean isLineChange = getPositionChange(line, toLine) > 0; // column is the same then
        int start = isLineChange ? Math.max(line, toLine) : Math.max(column, toColumn);
        int end = isLineChange ? Math.min(line, toLine) : Math.min(column, toColumn);

        for (int i = start - 1; i > end; i--) {
            ChessPiece piece = isLineChange ? chessBoard.board[i][column] : chessBoard.board[line][i];
            if (piece != null) return true;
        }

        return false;
    }

    @Override
    String getSymbol() {
        return "R";
    }
}
