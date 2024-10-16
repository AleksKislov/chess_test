package org.example;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    ChessPieceColor nowPlayer;

    public ChessBoard(ChessPieceColor nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public ChessPieceColor nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (!checkPos(startLine) || !checkPos(startColumn)) return false;
        if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

        if (!board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
            return false;
        }

        ChessPiece piece = board[startLine][startColumn];
        board[endLine][endColumn] = piece; // if piece can move, we moved a piece
        board[startLine][startColumn] = null; // set null to previous cell
        piece.check = false;
        this.nowPlayer = this.nowPlayerColor().equals(ChessPieceColor.White) ? ChessPieceColor.Black : ChessPieceColor.White;

        return true;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().getLetter() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public Boolean castling0() {
        return castling(true);
    }

    public Boolean castling7() {
        return castling(false);
    }

    private boolean castling(boolean isLongCastling) {
        boolean isWhite = nowPlayer.equals(ChessPieceColor.White);
        int line = isWhite ? 0 : 7;
        ChessPiece[] row = board[line];
        int kingNewColumn = isLongCastling ? 2 : 6;
        int kingInitColumn = 4;
        int rookNewColumn = isLongCastling ? 3 : 5;
        int rookInitColumn = isLongCastling ? 0 : 7;

        ChessPiece rook = row[rookInitColumn];
        ChessPiece king = row[kingInitColumn];
        if (king == null || rook == null) return false;

        boolean isNeverMoved = rook.getSymbol().equals("R") && king.getSymbol().equals("K") && rook.check && king.check;
        if (!isNeverMoved) return false;

        // check if cells between king and rook are empty
        int start = Math.max(kingInitColumn, rookInitColumn);
        int end = Math.min(kingInitColumn, rookInitColumn);
        for (int i = start - 1; i > end; i--) {
            if (row[i] != null) return false;
        }

        boolean isUnderAttack = ((King) king).isUnderAttack(this, line, kingNewColumn)
                && ((King) king).isUnderAttack(this, line, kingInitColumn);
        if (isUnderAttack) return false;

        row[kingNewColumn] = new King(nowPlayer);
        row[kingNewColumn].check = false;
        row[kingInitColumn] = null;
        row[rookNewColumn] = new Rook(nowPlayer);
        row[rookNewColumn].check = false;
        row[rookInitColumn] = null;
        nowPlayer = isWhite ? ChessPieceColor.Black : ChessPieceColor.White;
        return true;
    }
}
