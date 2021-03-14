package com.jenya.moving;
import com.jenya.model.User;

import com.jenya.Constants;
import com.jenya.model.ChessPiece;

public class MovingImpl implements Moving{


    @Override
    public void move(ChessPiece piece) {
        piece.setX(3);
        piece.setY(3);
    }
}
