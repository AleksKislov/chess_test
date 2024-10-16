package org.example;

public class King extends ChessPiece{

    King(ChessPieceColor color) {
        super(color);
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isNotValidMove(chessBoard, line, column, toLine, toColumn)) return false;
        if (hasObstructions(chessBoard, line, column, toLine, toColumn)) return false;

        int columnChange = getPositionChange(column, toColumn);
        int lineChange = getPositionChange(line, toLine);

        return columnChange == 1 || lineChange == 1;
    }

    @Override
    public boolean hasObstructions(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return false;
    }

    @Override
    String getSymbol() {
        return "K";
    }

    boolean isUnderAttack(ChessBoard board, int line, int column) {
        ChessPieceColor enemyColor = this.color == ChessPieceColor.White ? ChessPieceColor.Black : ChessPieceColor.White;

        int rows = board.board.length;
        int columns = board.board.length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                ChessPiece piece = board.board[i][j];
                boolean isEnemy = piece != null && piece.color == enemyColor;
                if (isEnemy && piece.canMoveToPosition(board, i, j, line, column)) {
                    return true;
                }
            }
        }
        return false;
    }
}
