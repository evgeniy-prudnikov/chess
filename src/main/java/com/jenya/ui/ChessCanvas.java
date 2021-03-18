package com.jenya.ui;

import com.jenya.model.Cell;
import com.jenya.model.ChessPiece;
import com.jenya.model.User;
import com.jenya.moving.MovingImpl;
import com.jenya.ui.layout.Game;
import com.jenya.ui.layout.GameStart;
import com.jenya.ui.layout.Layout;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

import static com.jenya.Constants.*;
import static com.jenya.ui.ChessUtils.drawFigure;

public class ChessCanvas extends Application {

    public static Layout currentLayout = null;
    boolean runGame = true;
    boolean isGpuBusy = false;

    private EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            currentLayout.handleEvent(event);
        }
    };

    public static void run() {
        launch();
    }

    /**
     * Method that fix closing game
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        runGame = false;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        currentLayout = new GameStart();
        Group root = new Group();
        root.setOnMouseClicked(eventHandler);
        root.setOnMouseMoved(new MouseUtils());
        Canvas canvas = new Canvas(WIDTH + 300, HEIGHT);
        GraphicsContext gcanvas = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (runGame) {
                    try { Thread.sleep(300L); } catch (InterruptedException e) { }
                    if(isGpuBusy)
                        continue;
                    isGpuBusy = true;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            currentLayout.draw(gcanvas);
                            isGpuBusy = false;
                        }
                    });

                }
            }
        });
        mainThread.start();
    }


}