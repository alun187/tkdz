package com.tkda.tank;

import com.tkda.adapter.TankKeyAdapter;
import com.tkda.tank.Bullet;
import com.tkda.tank.EnemyTank;
import com.tkda.tank.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private Tank playerTank;
    private List<EnemyTank> enemyTanks;

    public GamePanel() {
        playerTank = new Tank(100, 100);
        enemyTanks = new ArrayList<>();
        enemyTanks.add(new EnemyTank(200, 200));
        enemyTanks.add(new EnemyTank(300, 300));

        timer = new Timer(16, this);
        timer.start();
        setFocusable(true);
        addKeyListener(new TankKeyAdapter(playerTank));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerTank.draw(g);
        for (EnemyTank enemyTank : enemyTanks) {
            enemyTank.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playerTank.update();
        checkCollisions();
        repaint();
    }

    private void checkCollisions() {
        List<Bullet> bullets = playerTank.getBullets();
        Iterator<EnemyTank> it = enemyTanks.iterator();

        while (it.hasNext()) {
            EnemyTank enemyTank = it.next();
            Rectangle enemyBounds = new Rectangle(enemyTank.x, enemyTank.y, 40, 40);

            for (Bullet bullet : bullets) {
                if (bullet.getBounds().intersects(enemyBounds)) {
                    it.remove();
                    bullets.remove(bullet);
                    break;
                }
            }
        }
    }
}


