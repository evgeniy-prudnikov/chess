package com.jenya.ui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseUtils implements EventHandler<MouseEvent> {

    public static int mouseX;
    public static int mouseY;

    @Override
    public void handle(MouseEvent event) {
        mouseX = (int) event.getX();
        mouseY = (int) event.getY();
    }


}
