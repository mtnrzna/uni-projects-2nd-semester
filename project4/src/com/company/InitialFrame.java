package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialFrame {
    public static void showingInitialFrame(User user){
        JFrame initialFrame = new JFrame();
        initialFrame.setBounds(600 ,200 , 300 , 300);
        initialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialFrame.setVisible(true);
        initialFrame.setLayout(null);

        JLabel chooseLabel = new JLabel("choose one of the following options: ");
        chooseLabel.setBounds(30 ,25 ,300 ,50);
        initialFrame.add(chooseLabel);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(90 ,60 ,100 ,40);
        initialFrame.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(90 ,100 ,100 ,40);
        initialFrame.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialFrame.setVisible(false);
                LoginFrame.showingLoginFrame(user);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialFrame.setVisible(false);
                RegisterFrame.showingRegFrame(user);
            }
        });
    }
}
