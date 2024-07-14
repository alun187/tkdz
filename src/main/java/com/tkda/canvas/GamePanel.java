package com.tkda.canvas;

import com.tkda.adapter.TankKeyAdapter;
import com.tkda.dto.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private Tank playerTank;

    public GamePanel() {
        playerTank = new Tank(100, 100);
        timer = new Timer(16, this);
        timer.start();
        setFocusable(true);
        addKeyListener(new TankKeyAdapter(playerTank));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerTank.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playerTank.update();
        repaint();
    }
}
