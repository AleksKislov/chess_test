package org.example;

public enum ChessPieceColor {
    White,
    Black;

    /**
     * returns lower case letter "w" or "b"
     * @return String
     */
    public String getLetter() {
        return this == White ? "w" : "b";
    }
}
