package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame {
    public static void showingRegFrame(User user){
        JFrame registerFrame = new JFrame();
        registerFrame.setBounds(350 , 150 ,700 ,500);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setLayout(null);
        registerFrame.setVisible(true);

        JLabel registerLabel = new JLabel("Register Data");
        registerLabel.setBounds(300, 5, 150, 40);
        registerLabel.setFont(new Font("Serif" , Font.BOLD , 20));
        registerFrame.add(registerLabel);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(180, 60, 150, 40);
        registerFrame.add(usernameLabel);
        JTextField usernameTxtField = new JTextField();
        usernameTxtField.setBounds(300,65 ,150,30);
        registerFrame.add(usernameTxtField);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(180 ,110,150,40);
        registerFrame.add(passwordLabel);
        JTextField PWTxtField = new JTextField();
        PWTxtField.setBounds(300,115,150,30);
        registerFrame.add(PWTxtField);

        JLabel confirmPWLabel = new JLabel("Confirm Password: ");
        confirmPWLabel.setBounds(180 ,160,150,40);
        registerFrame.add(confirmPWLabel);
        JTextField confirmPWTxtField = new JTextField();
        confirmPWTxtField.setBounds(300,165,150,30);
        registerFrame.add(confirmPWTxtField);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(180,210,210,40);
        registerFrame.add(nameLabel);
        JTextField nameTxtField = new JTextField();
        nameTxtField.setBounds(300,215,200,30);
        registerFrame.add(nameTxtField);

        JLabel emailLabel =new JLabel("Email: ");
        emailLabel.setBounds(180 ,260,150,40);
        registerFrame.add(emailLabel);
        JTextField emailTxtField = new JTextField();
        emailTxtField.setBounds(300,265,200,30);
        registerFrame.add(emailTxtField);


        JButton saveButton= new JButton("Save");
        saveButton.setBounds(300,400,80,30);
        registerFrame.add(saveButton);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(PWTxtField.getText().equals(confirmPWTxtField.getText()) &&
                        !PWTxtField.getText().isEmpty() && PWTxtField.getText().length() >= 4) {

                    user.setUsername(usernameTxtField.getText());
                    user.setPassword(PWTxtField.getText());
                    user.setName(nameTxtField.getText());
                    user.setEmail(emailTxtField.getText());
                    registerFrame.setVisible(false);

                    JFrame acountSavedFrame = new JFrame();
                    acountSavedFrame.setBounds(500 , 200 ,500 ,200);
                    acountSavedFrame.setVisible(true);
                    acountSavedFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    acountSavedFrame.setLayout(null);

                    JLabel acountSavedOkLabel = new JLabel("Acount saved!");
                    acountSavedOkLabel.setBounds(200,5,150,50);
                    acountSavedFrame.add(acountSavedOkLabel);

                    JButton acountSavedOk = new JButton("OK");
                    acountSavedOk.setBounds(200,40,80,30);
                    acountSavedFrame.add(acountSavedOk);
                    acountSavedOk.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            acountSavedFrame.setVisible(false);
                            InitialFrame.showingInitialFrame(user);
                        }
                    });



                }
                else {
                    JFrame notLogedIn = new JFrame();
                    notLogedIn.setBounds(500 , 200 ,500 ,200);
                    notLogedIn.setVisible(true);
                    notLogedIn.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    notLogedIn.setLayout(null);

                    JLabel notLogedInLabel = new JLabel("Passwords didn't match!\n" +
                            "or password is less than 4 digits");
                    notLogedInLabel.setBounds(30,5,350,150);
                    notLogedIn.add(notLogedInLabel);
                }
            }
        });


    }
}
