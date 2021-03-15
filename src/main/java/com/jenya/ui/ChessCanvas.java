package com.jenya.ui;

import com.jenya.model.Cell;
import com.jenya.model.ChessPiece;
import com.jenya.model.User;
import com.jenya.moving.MovingImpl;
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

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

import static com.jenya.Constants.*;
import static com.jenya.ui.ChessUtils.drawFigure;

public class ChessCanvas extends Application {

    ChessPiece selectedPiece = null;
    boolean isWhite = false;

    ArrayList<Cell> cells = new ArrayList<>();
        private EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                int x = (int) event.getX() / FIGURE_SIZE;
                int y = (int) event.getY() / FIGURE_SIZE;

                if (selectedPiece == null) {
                    if(isWhite) {
                        for (ChessPiece piece : userWhite.getFigures()) {
                            if (piece.getX() == x && piece.getY() == y)
                                cells = moving.getAvailableMoves(selectedPiece = piece, userWhite, userBlack, true);
                        }

                    } else {
                    for (ChessPiece piece : userBlack.getFigures()) {
                        if (piece.getX() == x && piece.getY() == y) {
                            cells = moving.getAvailableMoves(selectedPiece = piece, userWhite, userBlack, false);
                        }
                    }
                    }

                } else {
                    if (isWhite) {
                        for (ChessPiece piece : userWhite.getFigures()) {
                            if (piece.getX() == x && piece.getY() == y) {
                                cells = moving.getAvailableMoves(selectedPiece = piece, userWhite, userBlack, true);
                            }
                        }

                    } else {
                        for (ChessPiece piece : userBlack.getFigures()) {
                            if (piece.getX() == x && piece.getY() == y) {
                                cells = moving.getAvailableMoves(selectedPiece = piece, userWhite, userBlack, false);

                            }
                        }
                    }


                    if (selectedPiece == null) {

                    } else {
                        Cell chossenCell = null;


                        for (Cell move : cells) {
                            if (move.getX() == x && move.getY() == y)
                                chossenCell = new Cell(x, y, move.getStatus());
                        }
                        if (chossenCell == null) {
                            return;
                        }


                        if (chossenCell.getStatus() == CELL_STATUS.FREE) {

                                moving.move(selectedPiece, x, y);
                                selectedPiece = null;
                                cells.clear();
                                isWhite = !isWhite;
                               // break;
                        } else if (chossenCell.getStatus() == CELL_STATUS.KILL) {
                                if (!isWhite) {
                                    for (ChessPiece piece : userWhite.getFigures()) {
                                        if (piece.getX() == x && piece.getY() == y) {
                                            if (piece == userWhite.getFigures().get(6)) {
                                            userWhite.getFigures().remove(piece);
                                            return;
                                            //break;
                                            } else {
                                                userWhite.getFigures().remove(piece);
                                                break;
                                            }

                                        }
                                    }
                                }
                                if (isWhite) {
                                    for (ChessPiece piece : userBlack.getFigures()) {
                                        if (piece.getX() == x && piece.getY() == y) {
                                            if (piece == userBlack.getFigures().get(6)) {
                                                userBlack.getFigures().remove(piece);
                                                //Thread.currentThread().interrupt();
                                                return;
                                            } else {
                                                userBlack.getFigures().remove(piece);
                                                break;
                                            }
                                        }
                                    }
                                }

                                moving.move(selectedPiece, x, y);
                                selectedPiece = null;
                                cells.clear();
                                isWhite = !isWhite;


                            }
                            //break;



                        }
                    }
                }
            };
        GraphicsContext gcanvas;

        MovingImpl moving = new MovingImpl();
        private User userWhite = new User(true);

    public User getUserWhite() {
        return userWhite;
    }

    public void setUserWhite(User userWhite) {
        this.userWhite = userWhite;
    }

    public User getUserBlack() {
        return userBlack;
    }

    public void setUserBlack(User userBlack) {
        this.userBlack = userBlack;
    }

    private User userBlack = new User(false);

    public static void run() {
            launch();
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Drawing Operations Test");
            Group root = new Group();
            Canvas canvas = new Canvas(WIDTH, HEIGHT);
            GraphicsContext gcanvas = canvas.getGraphicsContext2D();
            root.getChildren().add(canvas);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i=0;
                    while (true)
                    {
                        final int fin = i++;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                reDraw(gcanvas, fin % 2 ==0, root);

                            }
                        });
                        try {
                            Thread.sleep(300L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        }

        final boolean IS_WHITE = isWhite;

        public void reDraw(GraphicsContext gc, boolean dr, Group group) {

            drawField(gc, group);


            for (ChessPiece piece: userWhite.getFigures() ) {
                drawFigure(gc, piece.getFigure(), piece.getX(), piece.getY(), IS_WHITE);
            }
            for (ChessPiece piece: userBlack.getFigures() ) {
                drawFigure(gc, piece.getFigure(), piece.getX(), piece.getY(), !IS_WHITE);
            }
            if(dr)return;


            for (Cell cell: cells) {

                if (cell.getStatus() == CELL_STATUS.FREE) {
                    gc.setFill(Color.GREEN);
                    gc.fillRect(cell.getX() * FIGURE_SIZE + 3 * FIGURE_SIZE / 8, cell.getY() * FIGURE_SIZE + 3 * FIGURE_SIZE / 8, FIGURE_SIZE / 4, FIGURE_SIZE / 4);
                } if (cell.getStatus() == CELL_STATUS.KILL) {
                    gc.setFill(Color.ORANGE);
                    gc.fillRect(cell.getX() * FIGURE_SIZE + 3 * FIGURE_SIZE / 8, cell.getY() * FIGURE_SIZE + 3 * FIGURE_SIZE / 8, FIGURE_SIZE / 4, FIGURE_SIZE / 4);

                }

            }



    }


        public void drawField(GraphicsContext gc, Group group) {
        group.setOnMouseClicked(eventHandler);

            for (int j = 0; j < 8; j++) {
                for (int i = 0; i < 8; i++) {
                    if (((i+j) % 2 == 0)) {
                        gc.setFill(Color.WHITE);
                    } else {
                        gc.setFill(Color.BROWN);
                    }
                    gc.fillRect(i * FIGURE_SIZE, j * FIGURE_SIZE, FIGURE_SIZE, FIGURE_SIZE);
                    //group.getChildren().add(square);


                }
            }
        }




    }


