package com.jenya.ui;

import com.jenya.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;

import static com.jenya.Constants.FIGURE_SIZE;

public class ChessUtils {

    static final Image image = new Image("figures.png");


    public static void drawFigure(GraphicsContext gc, Constants.FIGURES figure, int X, int Y , boolean isWhite) {
        gc.drawImage(image, 333 * (figure.value - 1) , isWhite?0:333,  333 , 333,
                X * FIGURE_SIZE, Y * FIGURE_SIZE, FIGURE_SIZE, FIGURE_SIZE);

    }
}
