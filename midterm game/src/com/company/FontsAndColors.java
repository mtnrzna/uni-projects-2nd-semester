package com.company;

import java.awt.*;

public class FontsAndColors {
    private static Font labelFont = new Font("Arial" , Font.PLAIN, 20);
    private static Font bigFont = new Font("Arial", Font.PLAIN, 24);
    private static Font normalFont = new Font("Arial", Font.PLAIN, 16);


    public static Font getNormalFont() {
        return normalFont;
    }
    public static Font getLabelFont() {
        return labelFont;
    }

    public static Font getBigFont() {
        return bigFont;
    }



    public static GradientPaint rndmGrdntClr(int startX, int startY, int endX, int endY){
        int red = (int) (Math.random() *255);
        int green = (int) (Math.random() *255);
        int blue = (int) (Math.random() *255);
        Color startColor = new Color(red, green, blue);

        red = (int) (Math.random() *255);
        green= (int) (Math.random() *255);
        blue= (int) (Math.random() *255);
        Color endColor = new Color(red, green, blue);
        GradientPaint gradient = new GradientPaint(startX, startY, startColor,endX, endY, endColor);
        return gradient;

    }
}
