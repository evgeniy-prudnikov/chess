package com.jenya;

public class Constants {

    public static final byte[] BROADCAST = new byte[]{(byte)255,(byte)255,(byte)255,(byte)255};
    public static final int UDP_RECEIVE_PORT = 8080;
    public static final int POCKET_SIZE = 512;
    public static final int HEIGHT = 640;
    public static final int WIDTH = HEIGHT;
    public static final int FIGURE_SIZE = HEIGHT / 8;
    public static enum FIGURES{

        KING(1, 1),
        QUEEN(2, 1),
        BISHOP(3, 2),
        HORSE(4, 2),
        CASTLE(5, 2),
        PAWN(6, 8);

        public int value;
        public int count;
        FIGURES(int value, int count) {
            this.value = value;
            this.count = count;
        }

    }
    public static enum CELL_STATUS{

        FREE(0),
        OUT_OF_FIELD(1),
        OCCUPIED(2),
        KILL(3);


        public int value;

        CELL_STATUS(int value) {
            this.value = value;
        }

    }


}
