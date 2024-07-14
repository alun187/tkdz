package com.tkda.adapter;

import com.tkda.dto.Tank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankKeyAdapter extends KeyAdapter {
    private Tank tank;

    public TankKeyAdapter(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            tank.setDirection(-1, 0);
        }

        if (key == KeyEvent.VK_RIGHT) {
            tank.setDirection(1, 0);
        }

        if (key == KeyEvent.VK_UP) {
            tank.setDirection(0, -1);
        }

        if (key == KeyEvent.VK_DOWN) {
            tank.setDirection(0, 1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            tank.setDirection(0, 0);
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            tank.setDirection(0, 0);
        }
    }
}
