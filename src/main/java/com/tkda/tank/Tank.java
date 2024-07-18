package com.tkda.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tank {
    protected int x, y;
    protected int dx, dy;
    private List<Bullet> bullets;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        bullets = new ArrayList<>();
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 40, 40);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    public void update() {
        x += dx;
        y += dy;
        for (Bullet bullet : bullets) {
            bullet.update();
        }
    }

    public void setDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void shoot() {
        bullets.add(new Bullet(x + 20, y + 20, dx * 2, dy * 2));
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}

