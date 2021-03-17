package com.jenya.ui.layout;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.input.MouseEvent;

public interface Layout {

    public void handleEvent(MouseEvent event);
    public void draw(GraphicsContext graphicsContext);

}
