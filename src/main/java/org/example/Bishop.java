package org.example;

public class Bishop extends ChessPiece{

    Bishop(ChessPieceColor color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isNotValidMove(chessBoard, line, column, toLine, toColumn)) return false;
        if (hasObstructions(chessBoard, line, column, toLine, toColumn)) return false;

        int columnChange = getPositionChange(column, toColumn);
        int lineChange = getPositionChange(line, toLine);

        return columnChange == lineChange;
    }

    @Override
    public boolean hasObstructions(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        boolean isLineIncrease = toLine > line;
        boolean isColumnIncrease = toColumn > column;

        int lineStart = Math.max(line, toLine);
        int lineEnd = Math.min(line, toLine);
        int columnStart = Math.max(column, toColumn);
        int columnEnd = Math.min(column, toColumn);

        boolean isDescendingDiagonal = isLineIncrease && isColumnIncrease || (!isLineIncrease && !isColumnIncrease);
        if (isDescendingDiagonal) {
        int clmn =  columnStart - 1;
            for (int row = lineStart - 1; row > lineEnd; row--) {
                if (chessBoard.board[row][clmn] != null) return true;
                clmn--;
            }
        } else {
            int clmn = columnEnd + 1;
            for (int row = lineStart - 1; row > lineEnd; row--) {
                if (chessBoard.board[row][clmn] != null) return true;
                clmn++;
            }
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "B";
    }
}
