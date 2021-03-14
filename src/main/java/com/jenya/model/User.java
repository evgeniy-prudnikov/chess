package com.jenya.model;

import java.util.ArrayList;

public class User {

    private ArrayList<ChessPiece> figures = new ArrayList<>(8);

    public ArrayList<ChessPiece> getFiguresb() {
        return figuresb;
    }

    public void setFiguresb(ArrayList<ChessPiece> figuresb) {
        this.figuresb = figuresb;
    }

    private ArrayList<ChessPiece> figuresb = new ArrayList<>(8);
    private String userName;
    private boolean isWhite = true;

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
