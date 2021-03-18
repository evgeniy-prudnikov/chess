package com.jenya.ui.layout;

import com.jenya.ui.ChessCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import static com.jenya.Constants.*;
import static com.jenya.ui.ChessUtils.drawStartField;

public class GameStart implements Layout {

    @Override
    public void handleEvent(MouseEvent event) {
        if ((event.getX() > 520 && event.getX() < 760) && (event.getY() < 490 && event.getY() > 320)) {
            ChessCanvas.currentLayout = new Game(false);
        }
        if ((event.getX() > 180 && event.getX() < 420) && (event.getY() < 490 && event.getY() > 320)) {
            ChessCanvas.currentLayout = new Game(true);
        }
    }

    @Override
    public void draw(GraphicsContext gc) {

        drawStartField(gc);
    }

}
