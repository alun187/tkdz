package com.tkda.tank;

import java.awt.*;
import java.util.Random;

public class EnemyTank extends Tank {
    private Random random;
    private int moveCounter;

    public EnemyTank(int x, int y) {
        super(x, y);
        random = new Random();
        moveCounter = 0;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 40, 40);
    }

//    public void update() {
//        super.update();
//        moveCounter++;
//        if (moveCounter > 60) {
//            int direction = random.nextInt(4);
//            switch (direction) {
//                case 0 -> setDirection(-1, 0);
//                case 1 -> setDirection(1, 0);
//                case 2 -> setDirection(0, -1);
//                case 3 -> setDirection(0, 1);
//            }
//            moveCounter = 0;
//        }
//    }

    @Override
    public void update() {
        super.update();
        moveCounter++;
        if (moveCounter > 60) {
            int direction = random.nextInt(4);
            switch (direction) {
                case 0:
                    setDirection(-1, 0);
                    break;
                case 1:
                    setDirection(1, 0);
                    break;
                case 2:
                    setDirection(0, -1);
                    break;
                case 3:
                    setDirection(0, 1);
                    break;
            }
            moveCounter = 0;
        }
    }

}

