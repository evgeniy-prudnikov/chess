package com.jenya.moving;
import com.jenya.Constants.*;
import com.jenya.model.Cell;
import com.jenya.model.User;

import com.jenya.model.ChessPiece;

import java.util.ArrayList;

public class MovingImpl implements Moving{

    @Override
    public void move(ChessPiece piece, int x, int y) {
        piece.setX(x);
        piece.setY(y);
    }

    private CELL_STATUS getCellStatus (User userWhite, User userBlack, int X, int Y, boolean isWhite) {

        if((X < 0) || (Y < 0) || (X > 7) || (Y > 7)) {
            return CELL_STATUS.OUT_OF_FIELD;
        }

        for (ChessPiece piece: userBlack.getFigures()) {

                if (piece.getX() == X && piece.getY() == Y) {
                    return isWhite ? CELL_STATUS.KILL : CELL_STATUS.OCCUPIED;

                }
        }
        for (ChessPiece piece: userWhite.getFigures()) {

                if (piece.getX() == X && piece.getY() == Y) {
                    return isWhite ? CELL_STATUS.OCCUPIED : CELL_STATUS.KILL;

            }
        }
        return CELL_STATUS.FREE;

    }
    private Cell isCellFree(User userWhite, User userBlack, int X, int Y, boolean isWhite) {

        Cell cell = new Cell(X, Y, getCellStatus(userWhite, userBlack, X, Y, isWhite));
        return cell.getStatus()==CELL_STATUS.FREE || cell.getStatus()==CELL_STATUS.KILL?cell:null;

    }
    private Cell isCellFree(User userWhite, User userBlack, int X, int Y) {
        Cell cell = new Cell(X, Y, getCellStatus(userWhite, userBlack, X, Y, true));
        return cell.getStatus()==CELL_STATUS.FREE || cell.getStatus()==CELL_STATUS.KILL?cell:null;
    }

    private void AddIfNotNull(ArrayList<Cell> list, Cell cell) {
        if (cell != null) {
            list.add(cell);
        }
    }




    @Override
    public ArrayList<Cell> getAvailableMoves(ChessPiece piece, User userWhite, User userBlack, boolean isWhite) {

        ArrayList<Cell> available = new ArrayList<>();


        switch (piece.getFigure()) {
            
            case HORSE: {

                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() - 1, piece.getY() + 2, isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() - 2, piece.getY() + 1, isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() - 2, piece.getY() - 1, isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() - 1, piece.getY() - 2, isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() + 1, piece.getY() - 2, isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() + 2, piece.getY() - 1, isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() + 2, piece.getY() + 1, isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() + 1, piece.getY() + 2, isWhite));

                    break;

            }
            case KING: {


                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() + 1, piece.getY() + 1, isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX(), piece.getY() + 1, isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() - 1, piece.getY() + 1, isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() - 1, piece.getY(), isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() - 1, piece.getY() - 1, isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX(), piece.getY() - 1, isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() + 1, piece.getY() - 1, isWhite));
                AddIfNotNull(available, isCellFree(userWhite, userBlack, piece.getX() + 1, piece.getY(), isWhite));


                break;
            }
            case BISHOP: {

                int X = piece.getX() + 1;
                int Y = piece.getY() + 1;
                while (isCellFree(userWhite,userBlack, X, Y, isWhite) != null) {

                    if(isCellFree(userWhite,userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                    AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                    break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));

                        X++;
                        Y++;
                    }
                }
                X = piece.getX() + 1;
                Y = piece.getY() - 1;
                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y,isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        X++;
                        Y--;
                    }
                }
                X = piece.getX() - 1;
                Y = piece.getY() - 1;
                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if(isCellFree(userWhite,userBlack, X, Y,isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                    X--;
                    Y--;
                    }
                }
                X = piece.getX() - 1;
                Y = piece.getY() + 1;
                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        X--;
                        Y++;
                    }
                }
                break;

            }
            case CASTLE: {
                int X = piece.getX();
                int Y = piece.getY() + 1;

                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        Y++;
                    }
                }
                X = piece.getX() ;
                Y = piece.getY() - 1;
                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));

                        Y--;
                    }
                }
                X = piece.getX() + 1;
                Y = piece.getY();
                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));

                        X++;
                    }
                }
                X = piece.getX() - 1;
                Y = piece.getY();
                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));

                        X--;
                    }
                }
                  break;
            }

            case QUEEN: {
                int X = piece.getX();
                int Y = piece.getY() + 1;

                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        Y++;
                    }
                }
                X = piece.getX() ;
                Y = piece.getY() - 1;
                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));

                        Y--;
                    }
                }
                X = piece.getX() + 1;
                Y = piece.getY();
                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));

                        X++;
                    }
                }
                X = piece.getX() - 1;
                Y = piece.getY();
                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));

                        X--;
                    }
                }
                X = piece.getX() + 1;
                Y = piece.getY() + 1;
                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        X++;
                        Y++;
                    }
                }
                X = piece.getX() + 1;
                Y = piece.getY() - 1;
                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        X++;
                        Y--;
                    }
                }
                X = piece.getX() - 1;
                Y = piece.getY() - 1;
                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        X--;
                        Y--;
                    }
                }
                X = piece.getX() - 1;
                Y = piece.getY() + 1;
                while (isCellFree(userWhite,userBlack, X, Y,isWhite) != null) {

                    if (isCellFree(userWhite, userBlack, X, Y, isWhite).getStatus() == CELL_STATUS.KILL) {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        break;
                    } else {
                        AddIfNotNull(available, isCellFree(userWhite, userBlack, X, Y, isWhite));
                        X--;
                        Y++;
                    }
                }
                break;
            }
            case PAWN: {



                    Cell cell = isCellFree(userWhite,userBlack, piece.getX() - 1, piece.getY() + (isWhite?-1:1),isWhite);
                    if (piece.getX() == 0){} else {
                        if (cell.getStatus() == CELL_STATUS.KILL) {
                        available.add(cell);
                    }}
                    cell = isCellFree(userWhite,userBlack, piece.getX() + 1, piece.getY() + (isWhite?-1:1),isWhite);
                    if (piece.getX() == 7){} else {

                    if (cell.getStatus() == CELL_STATUS.KILL) {
                        available.add(cell);
                    }}
                    if (piece.getY() == 6 || piece.getY() == 1) {

                        cell = isCellFree(userWhite,userBlack, piece.getX(), piece.getY() + (isWhite?-1:1),isWhite);
                        if (cell.getStatus() == CELL_STATUS.FREE) {
                            available.add(cell);
                            cell = isCellFree(userWhite,userBlack, piece.getX(), piece.getY() + (isWhite?-2:2),isWhite);
                            if (cell.getStatus() == CELL_STATUS.FREE) {
                                available.add(cell);
                            }
                        }



                    } else {
                        cell = isCellFree(userWhite,userBlack, piece.getX(), piece.getY() + (isWhite?-1:1),isWhite);
                        if (cell.getStatus() == CELL_STATUS.FREE) {
                            available.add(cell);
                        }


                    }

            }

        }


        return available;
    }
}
