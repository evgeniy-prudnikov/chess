package com.jenya.model;

import com.jenya.Constants;

public class Cell {

    int X;
    int Y;
    private Constants.CELL_STATUS status;

    public Constants.CELL_STATUS getStatus() {
        return status;
    }

    public void setStatus(Constants.CELL_STATUS status) {
        this.status = status;
    }

    public Cell(int x, int y, Constants.CELL_STATUS status) {
        X = x;
        Y = y;
        this.status = status;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }
}
