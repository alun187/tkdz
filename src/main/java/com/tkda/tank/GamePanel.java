package com.tkda.tank;

import com.tkda.adapter.TankKeyAdapter;
import com.tkda.map.Map;

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
    private boolean gameOver;
    private Map map;

    public GamePanel() {
        playerTank = new Tank(100, 100);
        enemyTanks = new ArrayList<>();
        enemyTanks.add(new EnemyTank(200, 200, playerTank));
        enemyTanks.add(new EnemyTank(300, 300, playerTank));

        map = new Map();

        timer = new Timer(16, this);
        timer.start();
        setFocusable(true);
        addKeyListener(new TankKeyAdapter(playerTank));
        gameOver = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        map.draw(g);
        if (gameOver) {
            drawGameOver(g);
        } else {
            playerTank.draw(g);
            for (EnemyTank enemyTank : enemyTanks) {
                enemyTank.draw(g);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            playerTank.update();
            for (EnemyTank enemyTank : enemyTanks) {
                enemyTank.update();
            }
            checkCollisions();
            if (playerTank.getHealth() <= 0) {
                gameOver = true;
            }
            repaint();
        }
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

            // 检测敌方坦克的子弹与玩家坦克的碰撞
            for (Bullet enemyBullet : enemyTank.getBullets()) {
                if (enemyBullet.getBounds().intersects(new Rectangle(playerTank.x, playerTank.y, 40, 40))) {
                    playerTank.reduceHealth();
                    enemyTank.getBullets().remove(enemyBullet);
                    break;
                }
            }
        }
    }

    private void drawGameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Game Over", getWidth() / 2 - 100, getHeight() / 2);
    }
}




