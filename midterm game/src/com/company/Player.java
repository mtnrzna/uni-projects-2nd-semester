package com.company;

import javax.swing.*;
import java.awt.*;

public class Player {
    Player(){
        icon = new Drawing();
        icon.revalidate();
        icon.repaint();
    }

    private JPanel icon;
    public JPanel getIcon() {
        return icon;
    }

    private int point = 0;
    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }


    private int playerLocationX= -1;
    private int playerLocationY= -1;
    public int getPlayerLocationX() {
        return playerLocationX;
    }
    public void setPlayerLocationX(int playerLocationX) {
        this.playerLocationX = playerLocationX;
    }
    public int getPlayerLocationY() {
        return playerLocationY;
    }
    public void setPlayerLocationY(int playerLocationY) {
        this.playerLocationY = playerLocationY;
    }



}
