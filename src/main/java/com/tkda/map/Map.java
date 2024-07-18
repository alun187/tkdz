package com.tkda.map;

import java.awt.*;

public class Map {
    private int[][] map;

    public Map() {
        // 创建一个简单的地图，1代表墙壁，0代表空地
        map = new int[][] {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
    }

    public void draw(Graphics g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    g.setColor(Color.GRAY);
                    g.fillRect(j * 50, i * 50, 50, 50);
                }
            }
        }
    }

    public boolean isWall(int x, int y) {
        int i = y / 50;
        int j = x / 50;
        return map[i][j] == 1;
    }
}
