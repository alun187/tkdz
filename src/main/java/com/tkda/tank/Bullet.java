package com.tkda.tank;

import java.awt.*;

public class Bullet {
    private int x, y;
    private int dx, dy;

    public Bullet(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 5, 5);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 5, 5);
    }
}
