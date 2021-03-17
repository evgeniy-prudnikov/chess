package com.jenya.ui;

import com.jenya.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;

import static com.jenya.Constants.FIGURE_SIZE;

public class ChessUtils {

    public static final int reverseMap[] = new int[]{7,6,5,4,3,2,1,0};

    static final Image image = new Image("figures.png");

    public static void drawFigure(GraphicsContext gc, Constants.FIGURES figure, int X, int Y , boolean isWhite, boolean reverse) {
        //Y = reverse?reverseMap[Y]:Y;
        gc.drawImage(image, 333 * (figure.value - 1) , isWhite?0:333,  333 , 333,
                X * FIGURE_SIZE, Y * FIGURE_SIZE, FIGURE_SIZE, FIGURE_SIZE);
    }
}
