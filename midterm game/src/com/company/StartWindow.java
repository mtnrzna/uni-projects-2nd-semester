package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow {
    private static int insertedRow;
    private static int InsertedColumn;
    public static int getInsertedColumn() {
        return InsertedColumn;
    }
    public static int getInsertedRow() {
        return insertedRow;
    }

    public static void go(){

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel labelPanel = new JPanel();
        frame.getContentPane().add(BorderLayout.NORTH, labelPanel);
        JPanel middlePanel = new JPanel();
        frame.getContentPane().add(BorderLayout.CENTER, middlePanel);
        JPanel playPanel = new JPanel();
        frame.getContentPane().add(BorderLayout.SOUTH, playPanel);



        JLabel label = new JLabel("Enter the Game's Layout:");
        labelPanel.add(label);
        label.setFont(FontsAndColors.getLabelFont());



        JTextField rowsTxtFld = new JTextField(2);
        rowsTxtFld.setFont(FontsAndColors.getBigFont());
        middlePanel.add(rowsTxtFld);
        JLabel x = new JLabel("X");
        x.setFont(FontsAndColors.getBigFont());
        middlePanel.add(x);
        JTextField columnsTxtFld = new JTextField( 2);
        columnsTxtFld.setFont(FontsAndColors.getBigFont());
        middlePanel.add(columnsTxtFld);




        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.BOLD, 24));
        playButton.setBackground(Color.YELLOW);
        playButton.setForeground(Color.BLACK);
        playPanel.add(playButton);

        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2-150);
        frame.setVisible(true);


        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    int rowUserEntered = Integer.parseInt(rowsTxtFld.getText());
                    int columnUserEntered = Integer.parseInt(columnsTxtFld.getText());

                    if ((rowUserEntered <= 30) && (rowUserEntered >= 4)
                            && (columnUserEntered <= 30) && (columnUserEntered >= 4))
                    {
                        frame.setVisible(false);
                        insertedRow = rowUserEntered;
                        InsertedColumn = columnUserEntered;
                        Game.showPlacementWindow();

                    }
                    else
                        JOptionPane.showMessageDialog(frame,"Please Enter Integers Between 4 And 30 " );

                } catch(NumberFormatException  e){
                    JOptionPane.showMessageDialog(frame,"Please Enter Integers" );
                }

            }
        });


    }
}
