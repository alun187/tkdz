package com.tkda;

import com.tkda.canvas.GamePanel;

import javax.swing.*;

public class TankGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tank Battle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new GamePanel());
        frame.setVisible(true);
    }
}
