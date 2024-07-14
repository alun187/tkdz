package com.tkda.dto;

import java.awt.*;

public class Tank {
    private int x, y;
    private int dx, dy;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 40, 40);
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void setDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
