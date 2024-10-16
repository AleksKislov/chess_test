package org.example;

public class Queen extends ChessPiece {

    Queen(ChessPieceColor color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isNotValidMove(chessBoard, line, column, toLine, toColumn)) return false;
        if (hasObstructions(chessBoard, line, column, toLine, toColumn)) return false;

        int columnChange = getPositionChange(column, toColumn);
        int lineChange = getPositionChange(line, toLine);

        return columnChange == lineChange || (lineChange == 0 && columnChange > 0) || (columnChange == 0 && lineChange > 0);
    }

    @Override
    public boolean hasObstructions(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        boolean isRookMovement = getPositionChange(line, toLine) == 0 || getPositionChange(column, toColumn) == 0;
        if (isRookMovement) {
//            System.out.println("test for rook movement");
            boolean isLineChange = getPositionChange(line, toLine) > 0; // column is the same then
            int start = isLineChange ? Math.max(line, toLine) : Math.max(column, toColumn);
            int end = isLineChange ? Math.min(line, toLine) : Math.min(column, toColumn);

            for (int i = start - 1; i > end; i--) {
                ChessPiece piece = isLineChange ? chessBoard.board[i][column] : chessBoard.board[line][i];
                if (piece != null) return true;
            }
        } else {
//            System.out.println("test for bishop movement");
            boolean isLineIncrease = toLine > line;
            boolean isColumnIncrease = toColumn > column;

            int lineStart = Math.max(line, toLine);
            int lineEnd = Math.min(line, toLine);
            int columnStart = Math.max(column, toColumn);
            int columnEnd = Math.min(column, toColumn);

            boolean isDescendingDiagonal = isLineIncrease && isColumnIncrease || (!isLineIncrease && !isColumnIncrease);
            if (isDescendingDiagonal) {
//                System.out.println("bishop movement, descending diagonal");
                int clmn = columnStart - 1;
                for (int row = lineStart - 1; row > lineEnd; row--) {
                    if (chessBoard.board[row][clmn] != null) return true;
                    clmn--;
                }
            } else {
//                System.out.println("bishop movement, ascending diagonal");
                int clmn = columnEnd + 1;
                for (int row = lineStart - 1; row > lineEnd; row--) {
                    if (chessBoard.board[row][clmn] != null) return true;
                    clmn++;
                }
            }

        }
        return false;
    }

    @Override
    String getSymbol() {
        return "Q";
    }
}
