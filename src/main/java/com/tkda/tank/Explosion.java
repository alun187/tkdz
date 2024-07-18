package com.tkda.tank;

import java.awt.*;

public class Explosion {
    private int x, y;
    private int timer;

    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
        this.timer = 20; // 爆炸效果持续20帧
    }

    public void update() {
        timer--;
    }

    public void draw(Graphics g) {
        if (timer > 0) {
            g.setColor(Color.ORANGE);
            g.fillOval(x, y, 50, 50);
        }
    }

    public boolean isFinished() {
        return timer <= 0;
    }
}
