package com.tkda.tank;

import java.awt.*;
import java.util.Random;

public class EnemyTank extends Tank {
    private Random random;
    private int moveCounter;
    private Tank playerTank;

    public EnemyTank(int x, int y, Tank playerTank) {
        super(x, y);
        this.playerTank = playerTank;
        random = new Random();
        moveCounter = 0;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 40, 40);
        for (Bullet bullet : getBullets()) {
            bullet.draw(g);
        }
    }

    @Override
    public void update() {
        super.update();
        moveCounter++;
        if (moveCounter > 60) {
            int direction = random.nextInt(4);
            int dx = playerTank.x - x;
            int dy = playerTank.y - y;
            if (Math.abs(dx) > Math.abs(dy)) {
                setDirection(dx > 0 ? 1 : -1, 0);
            } else {
                setDirection(0, dy > 0 ? 1 : -1);
            }
            moveCounter = 0;
        }

        if (random.nextInt(100) < 5) {
            shoot();
        }
    }
}


