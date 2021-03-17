package com.jenya.ui.layout;

import com.jenya.ui.ChessCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


import static com.jenya.Constants.*;

public class GameStart implements Layout {

    @Override
    public void handleEvent(MouseEvent event) {
        if (event.getX() > 380 && event.getX() < 380 + FIGURE_SIZE*3) {
            ChessCanvas.currentLayout = new Game(false);
        }
        if (event.getX() > 80 && event.getX() < 80 + FIGURE_SIZE*3) {
            ChessCanvas.currentLayout = new Game(true);
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setFill(Color.WHITE);
        gc.fillRect(80, 280, FIGURE_SIZE*3, FIGURE_SIZE*3);
        gc.setFill(Color.BLACK);
        gc.fillRect(380, 280, FIGURE_SIZE*3, FIGURE_SIZE*3);
    }

}
