package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Star {
    private JLabel starIcon;
    public JLabel getStarIcon() {
        return starIcon;
    }


    private int starLocationX;
    private int starLocationY;
    public int getStarLocationX() {
        return starLocationX;
    }
    public void setStarLocationX(int starLocationX) {
        this.starLocationX = starLocationX;
    }
    public int getStarLocationY() {
        return starLocationY;
    }
    public void setStarLocationY(int starLocationY) {
        this.starLocationY = starLocationY;
    }



    public JLabel importStarPic(){
        try {
            BufferedImage myPicture;
            myPicture = ImageIO.read(new File("starIcon.png"));
            starIcon = new JLabel(new ImageIcon(myPicture));
        } catch (Exception ex) {
            System.out.println("image of star not found");
            starIcon = null;
        }
        return starIcon;
    }


    public void addStarTo(int x, int y, JButton cell) {
        if(Game.getCellInfos()[x][y].isStarInIt() == false) {
            cell.setLayout(new BorderLayout());
            starLocationX = x;
            starLocationY = y;
            Game.getStars().add(this);
            Game.getCellInfos()[x][y].setStarInIt(true);

            cell.add(BorderLayout.CENTER,importStarPic());
        }
    }
}
