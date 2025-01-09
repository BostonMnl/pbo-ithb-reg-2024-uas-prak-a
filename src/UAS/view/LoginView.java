package UAS.view;

import javax.swing.*;

import UAS.controller.LoginController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JPanel frame, input;
    private JLabel phone, password, logoLabel;
    private JTextField userValue;
    private JPasswordField passwordValue;
    private JButton login, backButton;

    public LoginView() {
        super("Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color blue = new Color(28, 90, 232);

        frame = new JPanel(new GridBagLayout());
        frame.setBackground(blue);

        input = new JPanel(new GridLayout(3, 2, 10, 10));
        input.setOpaque(false);

        ImageIcon logoIcon = new ImageIcon("src/UAS/view/image/logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon);

        phone = new JLabel("Phone");
        phone.setFont(new Font("Arial", Font.PLAIN, 16));
        phone.setForeground(Color.white);

        
        userValue = new JTextField();
        userValue.setPreferredSize(new Dimension(300, 40));
        userValue.setFont(new Font("Arial", Font.PLAIN, 16));
        userValue.setBackground(Color.BLUE);
        userValue.setForeground(Color.white);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 16));
        password.setForeground(Color.white);
        
        passwordValue = new JPasswordField();
        passwordValue.setPreferredSize(new Dimension(300, 40));
        passwordValue.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordValue.setBackground(Color.BLUE);
        passwordValue.setForeground(Color.white);

        login = new JButton("LOGIN");
        login.setFont(new Font("Arial", Font.BOLD, 16));
        login.setBackground(Color.BLUE);
        login.setForeground(Color.white);

        backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.white);

        input.add(phone);
        input.add(userValue);
        input.add(password);
        input.add(passwordValue);
        input.add(login);
        input.add(backButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(logoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(input, gbc);

        add(frame);

        setVisible(true);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (new LoginController().cekLogin(getPhone(), getPassword())) {
                    showMessage("Login Berhasil!");
                    new MainMenuView();
                    dispose();
                } else {
                    showMessage("Login Gagal, Mohon periksa data Login anda");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuView();
                dispose();
            }
        });
    }

    public String getPhone() {
        return userValue.getText();
    }

    public String getPassword() {
        return new String(passwordValue.getPassword());
    }

    public JButton getRegisterButton() {
        return backButton;
    }

    public JButton getLoginButton() {
        return login;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
