package com.jenya.model;

import com.jenya.Constants;

public class ChessPiece {

    private Constants.FIGURES figure = Constants.FIGURES.BISHOP;
    private int x = 0;
    private int y = 0;

    public Constants.FIGURES getFigure() {
        return figure;
    }

    public void setFigure(Constants.FIGURES figure) {
        this.figure = figure;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ChessPiece() {
    }

    public ChessPiece(Constants.FIGURES figure, int x, int y) {
        this.figure = figure;
        this.x = x;
        this.y = y;
    }


}
