package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame {
    public static void showingLoginFrame(User user){
        JFrame loginFrame = new JFrame("Enter your informations:");
        loginFrame.setBounds(350 , 150 ,600 ,200);
        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(150,10,100,40);
        loginFrame.add(usernameLabel);
        JTextField usernameTxtField = new JTextField();
        usernameTxtField.setBounds(220,20,200,25);
        loginFrame.add(usernameTxtField);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(150,40,100,40);
        loginFrame.add(passwordLabel);
        JTextField PWTxtField = new JTextField();
        PWTxtField.setBounds(220,50,200,25);
        loginFrame.add(PWTxtField);

        JButton okButton= new JButton("OK");
        okButton.setBounds(250,80,90,30);
        loginFrame.add(okButton);

        JButton cancelButton= new JButton("Cancel");
        cancelButton.setBounds(330,80,90,30);
        loginFrame.add(cancelButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usernameTxtField.getText().equals(user.getUsername() ) &&
                        PWTxtField.getText().equals(user.getPassword())){
                    loginFrame.setVisible(false);
                    JFrame logedIn = new JFrame();
                    logedIn.setBounds(500 , 200 ,300 ,100);
                    logedIn.setVisible(true);
                    logedIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    logedIn.setLayout(null);

                    JLabel logedInLabel = new JLabel("You successfully Loged in...");
                    logedInLabel.setBounds(30,5,200,50);
                    logedIn.add(logedInLabel);
                }

                else{
                    JFrame notLogedIn = new JFrame();
                    notLogedIn.setBounds(500 , 200 ,300 ,100);
                    notLogedIn.setVisible(true);
                    notLogedIn.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    notLogedIn.setLayout(null);

                    JLabel notLogedInLabel = new JLabel("please enter correct info");
                    notLogedInLabel.setBounds(30,5,150,50);
                    notLogedIn.add(notLogedInLabel);

                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(false);
                InitialFrame.showingInitialFrame(user);
            }
        });
    }
}
