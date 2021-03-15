package com.jenya.model;

import com.jenya.Constants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String userName;
    private boolean isWhite = true;


    private ArrayList<ChessPiece> figures = new ArrayList<>(8);

    public User(boolean isWhite) {
        int row1 = isWhite?7:0;
        int row2 = isWhite?6:1;

        this.figures.add(new ChessPiece(FIGURES.HORSE, 1, row1));
        this.figures.add(new ChessPiece(FIGURES.HORSE, 6, row1));
        this.figures.add(new ChessPiece(FIGURES.BISHOP, 2, row1));
        this.figures.add(new ChessPiece(FIGURES.BISHOP, 5, row1));
        this.figures.add(new ChessPiece(FIGURES.CASTLE, 0, row1));
        this.figures.add(new ChessPiece(FIGURES.CASTLE, 7, row1));
        this.figures.add(new ChessPiece(FIGURES.KING, 4, row1));
        this.figures.add(new ChessPiece(FIGURES.QUEEN, 3, row1));
        this.figures.add(new ChessPiece(FIGURES.PAWN, 0, row2));
        this.figures.add(new ChessPiece(FIGURES.PAWN, 1, row2));
        this.figures.add(new ChessPiece(FIGURES.PAWN, 2, row2));
        this.figures.add(new ChessPiece(FIGURES.PAWN, 3, row2));
        this.figures.add(new ChessPiece(FIGURES.PAWN, 4, row2));
        this.figures.add(new ChessPiece(FIGURES.PAWN, 5, row2));
        this.figures.add(new ChessPiece(FIGURES.PAWN, 6, row2));
        this.figures.add(new ChessPiece(FIGURES.PAWN, 7, row2));

    }


    public ArrayList<ChessPiece> getFigures() {
        return figures;
    }

    public void setFigures(ArrayList<ChessPiece> figures) {
        this.figures = figures;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }


}
