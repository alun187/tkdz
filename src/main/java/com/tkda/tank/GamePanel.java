package com.tkda.tank;

import com.tkda.map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private Tank playerTank;
    private List<EnemyTank> enemyTanks;
    private boolean gameOver;
    private Map map;
    private int score;
    private List<Explosion> explosions;

    public GamePanel() {
        initGame();
    }

    private void initGame() {
        playerTank = new Tank(100, 100);
        enemyTanks = new ArrayList<>();
        enemyTanks.add(new EnemyTank(200, 200, playerTank));
        enemyTanks.add(new EnemyTank(300, 300, playerTank));

        map = new Map();
        score = 0;
        explosions = new ArrayList<>();

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
        drawScore(g);
        if (gameOver) {
            drawGameOver(g);
        } else {
            playerTank.draw(g);
            for (EnemyTank enemyTank : enemyTanks) {
                enemyTank.draw(g);
            }
            for (Explosion explosion : explosions) {
                explosion.draw(g);
            }
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            playerTank.update();
            for (EnemyTank enemyTank : enemyTanks) {
                enemyTank.update();
            }
            for (Explosion explosion : explosions) {
                explosion.update();
            }
            explosions.removeIf(Explosion::isFinished);
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
                    explosions.add(new Explosion(enemyTank.x, enemyTank.y));
                    score += 100;  // 每摧毁一个敌方坦克增加100分
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

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Press R to Restart", getWidth() / 2 - 70, getHeight() / 2 + 40);
    }

    public void restartGame() {
        timer.stop();
        initGame();
    }

    // 键盘适配器类，用于捕获按键事件
    private class TankKeyAdapter extends KeyAdapter {
        private Tank tank;

        public TankKeyAdapter(Tank tank) {
            this.tank = tank;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                tank.setDirection(-1, 0);
            } else if (key == KeyEvent.VK_RIGHT) {
                tank.setDirection(1, 0);
            } else if (key == KeyEvent.VK_UP) {
                tank.setDirection(0, -1);
            } else if (key == KeyEvent.VK_DOWN) {
                tank.setDirection(0, 1);
            } else if (key == KeyEvent.VK_SPACE) {
                tank.shoot();
            } else if (key == KeyEvent.VK_R && gameOver) {
                restartGame();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
                tank.setDirection(0, 0);
            } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
                tank.setDirection(0, 0);
            }
        }
    }
}
