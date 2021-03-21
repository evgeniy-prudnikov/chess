package com.jenya.network;

import java.io.DataOutputStream;

import static com.jenya.Constants.POCKET_SIZE;

public class BroadcastPocket {

    private static final int TYPE_OFFSET = 0;
    private static final int MOVE_STARTX_OFFSET = 4;
    private static final int MOVE_STARTY_OFFSET = 8;
    private static final int MOVE_ENDX_OFFSET = 12;
    private static final int MOVE_ENDY_OFFSET = 16;
    private static final int NAME_OFFSET = 20;

    public byte[] POCKET = new byte[POCKET_SIZE];

    public String getip() throws Throwable {
        throw new NotImlementedYet();
    }

    public void setName(String name) {
        if (name.length() > 128)
            name = name.substring(0, 128);
        int offset = NAME_OFFSET;
        for (char b : name.toCharArray())
            POCKET[offset++] = (byte) b;
        POCKET[offset] = 0;
    }

    public String getName() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = NAME_OFFSET; i < (128 + NAME_OFFSET) && POCKET[i] != 0; i++) {
            stringBuilder.append((char) POCKET[i]);
        }
        return stringBuilder.toString();
    }

    public void setChoosen(int x, int y) {
        int offsetX = MOVE_STARTX_OFFSET;
        int offsetY = MOVE_STARTY_OFFSET;

        POCKET[offsetX] = (byte) (x >>> 0);
        POCKET[offsetX + 1] = (byte) (x >>> 8);
        POCKET[offsetX + 2] = (byte) (x >>> 16);
        POCKET[offsetX + 3] = (byte) (x >>> 24);

        POCKET[offsetY] = (byte) (y >>> 0);
        POCKET[offsetY + 1] = (byte) (y >>> 8);
        POCKET[offsetY + 2] = (byte) (y >>> 16);
        POCKET[offsetY + 3] = (byte) (y >>> 24);

    }

    public int getChoosenX() {

        return POCKET[MOVE_STARTX_OFFSET];
    }

    public int getChoosenY() {

        return POCKET[MOVE_STARTY_OFFSET];
    }

    public void setPlace(int x, int y) {
        int offsetX = MOVE_ENDX_OFFSET;
        int offsetY = MOVE_ENDY_OFFSET;

        POCKET[offsetX] = (byte) (x >>> 0);
        POCKET[offsetX + 1] = (byte) (x >>> 8);
        POCKET[offsetX + 2] = (byte) (x >>> 16);
        POCKET[offsetX + 3] = (byte) (x >>> 24);

        POCKET[offsetY] = (byte) (y >>> 0);
        POCKET[offsetY + 1] = (byte) (y >>> 8);
        POCKET[offsetY + 2] = (byte) (y >>> 16);
        POCKET[offsetY + 3] = (byte) (y >>> 24);

    }

    public int getPlaceX() {

        return POCKET[MOVE_ENDX_OFFSET];
    }

    public int getPlaceY() {

        return POCKET[MOVE_ENDY_OFFSET];
    }

}