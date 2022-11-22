package com.company;

import javax.swing.*;
import java.awt.*;

class Drawing extends JPanel{
    private GradientPaint gradient = FontsAndColors.rndmGrdntClr(0, 0, 20, 20);
    public void paintComponent (Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gradient);
        g2d.fillOval(0, 0, 20, 20);
    }

}