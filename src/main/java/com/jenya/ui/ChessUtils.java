package com.jenya.ui;

import com.jenya.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;

import static com.jenya.Constants.FIGURE_SIZE;
import static com.jenya.Constants.WIDTH;

public class ChessUtils {

    public static final int reverseMap[] = new int[]{7,6,5,4,3,2,1,0};

    static final Image image = new Image("figures.png");
    static final Image imageTextWhite = new Image("WHITE.png");
    static final Image imageTextBlack = new Image("BLACK.png");
    static final Image imageStartFieldWhite = new Image("whitechossing.jpg");
    static final Image imageStartFieldNone = new Image("none.jpg");
    static final Image imageStartFieldBlack = new Image("blackchossing.jpg");
    static final Image imageGameOverBlack = new Image("blacklose.jpg");
    static final Image imageGameOverWhite = new Image("whitelose.jpg");

    public static void drawFigure(GraphicsContext gc, Constants.FIGURES figure, int X, int Y , boolean isWhite, boolean reverse) {
        //Y = reverse?reverseMap[Y]:Y;
        gc.drawImage(image, 333 * (figure.value - 1) , isWhite?0:333,  333 , 333,
                X * FIGURE_SIZE, Y * FIGURE_SIZE, FIGURE_SIZE, FIGURE_SIZE);
    }

    public static void drawText(GraphicsContext gc, boolean isWhite) {

        if (isWhite) {
            gc.drawImage(imageTextWhite, WIDTH, 0);
        } else {
            gc.drawImage(imageTextBlack, WIDTH, 0);
        }
    }
    public static void drawStartField(GraphicsContext gc) {

        if ((MouseUtils.mouseY > 180 && MouseUtils.mouseX <  420) && (MouseUtils.mouseY > 320 && MouseUtils.mouseY <  490))  {
            gc.drawImage(imageStartFieldWhite, 0, 0);
        } else if ((MouseUtils.mouseX > 520 && MouseUtils.mouseX <  760) && (MouseUtils.mouseY > 320 && MouseUtils.mouseY <  490)) {
            gc.drawImage(imageStartFieldBlack, 0, 0);
        } else {
            gc.drawImage(imageStartFieldNone, 0, 0);

        }
    }
    public static void drawGameOver(GraphicsContext gc, boolean isWhite) {

        if (isWhite) {
            gc.drawImage(imageGameOverWhite, 0, 0);
        } else {
            gc.drawImage(imageGameOverBlack, 0, 0);
        }
    }
}
