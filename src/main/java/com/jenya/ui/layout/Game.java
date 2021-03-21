package com.jenya.ui.layout;

import com.jenya.Constants;
import com.jenya.model.Cell;
import com.jenya.model.ChessPiece;
import com.jenya.model.User;
import com.jenya.moving.MovingImpl;
import com.jenya.ui.ChessCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import com.jenya.network.BroadcastPocket;
import com.jenya.network.BroadcastReceiver;
import com.jenya.network.BroadcastSender;

import static com.jenya.Constants.*;
import static com.jenya.ui.ChessUtils.drawFigure;
import static com.jenya.ui.ChessUtils.drawText;

public class Game implements Layout {

    public Game(boolean isWhite){
        this.isWhite = isWhite;
        isWhiteCertanlyWhite = isWhite;
    }

    private int callCaunter = 0;
    public ChessPiece selectedPiece = null;
    public final boolean isWhiteCertanlyWhite;
    public boolean isWhite = true;
    public MovingImpl moving = new MovingImpl();
    public User userWhite = new User(true);
    public User userBlack = new User(false);
    public ArrayList<Cell> cells = new ArrayList<>();
    BroadcastPocket pocket = new BroadcastPocket();

    public void handleEvent(MouseEvent event) {
        int x = (int) event.getX() / FIGURE_SIZE;
        int y = (int) event.getY() / FIGURE_SIZE;
        final User currentUser = isWhite?userWhite:userBlack;
        for (ChessPiece piece : currentUser.getFigures()) {
            if (piece.getX() == x && piece.getY() == y) {
                cells = moving.getAvailableMoves(selectedPiece = piece, userWhite, userBlack, isWhite);
            }
        }
        if (selectedPiece != null) {
            Cell chossenCell = null;
            for (Cell move : cells) {
                if (move.getX() == x && move.getY() == y)
                    chossenCell = new Cell(x, y, move.getStatus());
            }
            if (chossenCell == null) {
                System.out.println("Я тут не зря");
                return;
            }
            switch (chossenCell.getStatus())
            {
                case FREE:

                    moving.move(selectedPiece, x, y);
                    selectedPiece = null;
                    cells.clear();
                    isWhite = !isWhite;
                    break;
                case KILL:
                    final User currentuser = isWhite ? userBlack : userWhite;
                    for (ChessPiece piece : currentuser.getFigures()) {
                        if (piece.getX() == x && piece.getY() == y) {

                            if (piece == currentuser.getFigures().get(6)) {
                                currentuser.getFigures().remove(piece);
                                ChessCanvas.currentLayout = new GameOver(isWhite);

                                System.out.println("Game over; should be here");
                                break;
                            } else {
                                currentuser.getFigures().remove(piece);
                                break;
                            }
                        }
                    }
                    moving.move(selectedPiece, x, y);
                    selectedPiece = null;
                    cells.clear();
                    isWhite = !isWhite;
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        drawField(gc);
        for (ChessPiece piece : userWhite.getFigures()) {
            drawFigure(gc, piece.getFigure(), piece.getX(), piece.getY(), isWhiteCertanlyWhite, isWhiteCertanlyWhite);
        }
        for (ChessPiece piece : userBlack.getFigures()) {
            drawFigure(gc, piece.getFigure(), piece.getX(), piece.getY(), !isWhiteCertanlyWhite, isWhiteCertanlyWhite);
        }
        if (callCaunter++ % 2 == 0) return;
        int xyOffset = 3 * FIGURE_SIZE / 8;
        int fsize = FIGURE_SIZE / 4;
        for (Cell cell : cells) {
            gc.setFill(cell.getStatus()== Constants.CELL_STATUS.KILL? Color.ORANGE:Color.GREEN);
            gc.fillRect(cell.getX() * FIGURE_SIZE + xyOffset, cell.getY() * FIGURE_SIZE + xyOffset, fsize, fsize);
        }
    }

    public void drawField(GraphicsContext gc) {
        whoMoves(gc, isWhite);
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if (((i + j) % 2 == 0)) {
                    gc.setFill(Color.WHITE);
                } else {
                    gc.setFill(Color.BROWN);
                }
                gc.fillRect(i * FIGURE_SIZE, j * FIGURE_SIZE, FIGURE_SIZE, FIGURE_SIZE);
            }
        }
    }

    public void whoMoves(GraphicsContext gc, boolean isW) {
        drawText(gc, isWhiteCertanlyWhite == isW);
    }

}
