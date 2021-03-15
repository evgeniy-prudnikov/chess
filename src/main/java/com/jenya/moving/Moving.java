package com.jenya.moving;

import com.jenya.model.ChessPiece;
import com.jenya.model.Cell;
import com.jenya.model.User;

import java.util.ArrayList;

public interface Moving {
    public void move(ChessPiece piece, int x, int y);
    public ArrayList<Cell> getAvailableMoves(ChessPiece piece, User userWhite, User userBlack, boolean isWhite);
}
