package com.tkda.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tank {
    protected int x, y;
    protected int dx, dy;
    private List<Bullet> bullets;
    private int health;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        bullets = new ArrayList<>();
        health = 3;  // 设置坦克的初始生命值
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 40, 40);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        drawHealth(g);
    }

    private void drawHealth(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Health: " + health, x, y - 10);
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

    public void reduceHealth() {
        health--;
    }

    public int getHealth() {
        return health;
    }
}


