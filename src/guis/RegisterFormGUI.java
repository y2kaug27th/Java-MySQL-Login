package guis;

import constants.CommonConstants;
import db.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterFormGUI extends Form{
    public RegisterFormGUI() {
        super("Register");
        addGuiComponents();
    }

    private void addGuiComponents() {

        var registerLabel = new JLabel("Register");
        registerLabel.setBounds(0, 25, 520, 100);
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        registerLabel.setFont(new Font("Arial", Font.BOLD, 40));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(registerLabel);

        var usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        var usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setBackground(CommonConstants.SENCONDARY_COLOR);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 24));

        add(usernameLabel);
        add(usernameField);


        var passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30, 255, 400, 25);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));

        var passwordField = new JPasswordField();
        passwordField.setBounds(30, 285, 450, 55);
        passwordField.setBackground(CommonConstants.SENCONDARY_COLOR);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 24));

        add(passwordLabel);
        add(passwordField);

        var rePasswordLabel = new JLabel("Re-enter Password");
        rePasswordLabel.setBounds(30, 365, 400, 25);
        rePasswordLabel.setForeground(CommonConstants.TEXT_COLOR);
        rePasswordLabel.setFont(new Font("Arial", Font.BOLD, 18));

        var rePasswordField = new JPasswordField();
        rePasswordField.setBounds(30, 395, 450, 55);
        rePasswordField.setBackground(CommonConstants.SENCONDARY_COLOR);
        rePasswordField.setForeground(CommonConstants.TEXT_COLOR);
        rePasswordField.setFont(new Font("Arial", Font.PLAIN, 24));

        add(rePasswordLabel);
        add(rePasswordField);

        var registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 18));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(CommonConstants.TEXT_COLOR);
        registerButton.setBounds(125, 520, 250, 50);

        registerButton.addActionListener((ActionEvent _) -> {
                var username = usernameField.getText();
                var password = new String(passwordField.getPassword());
                var rePassword = new String(rePasswordField.getPassword());
                if (validateUserInput(username, password, rePassword)) {
                    if (MyJDBC.register(username, password)) {
                        RegisterFormGUI.this.dispose();
                        var loginFromGUI = new LoginFormGUI();
                        loginFromGUI.setVisible(true);

                        JOptionPane.showMessageDialog(loginFromGUI,
                                "Registered Account Successfully");
                    }else
                        JOptionPane.showMessageDialog(RegisterFormGUI.this,
                                "Error: Username already taken");
                }else
                    JOptionPane.showMessageDialog(RegisterFormGUI.this,
                            "Error: Username must be at least 6 characters \n" +
                                    "and/or Password must match");
        });

        add(registerButton);

        var loginLabel = new JLabel("Have an account? Login Here");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterFormGUI.this.dispose();

                new LoginFormGUI().setVisible(true);
            }
        });

        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.setBounds(125, 600, 250, 30);

        add(loginLabel);
    }

    private boolean validateUserInput(String username, String password, String rePassword) {
        if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty())
            return false;
        if (username.length() < 6)
            return false;
        return password.equals(rePassword);
    }
}
