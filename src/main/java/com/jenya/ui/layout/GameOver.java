package com.jenya.ui.layout;

import com.jenya.ui.ChessCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import static com.jenya.ui.ChessUtils.drawGameOver;

public class GameOver implements Layout{
    boolean isWhite;

    public GameOver(boolean isWhite) {
        this.isWhite = isWhite;
    }

    @Override
    public void handleEvent(MouseEvent event) {

        if ((event.getX() > 520 && event.getX() < 760) && (event.getY() < 490 && event.getY() > 320)) {
            ChessCanvas.currentLayout = new GameStart();
        }
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        drawGameOver(graphicsContext,isWhite);

    }
}
