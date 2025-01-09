package UAS.view;

import javax.swing.*;

import UAS.model.classes.SingletonManager;
import UAS.model.classes.Transaction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenuView extends JFrame {
    private JButton login, register, addTransaction, history;
    private JLabel logoLabel;
    private JPanel pesan;

    public MainMenuView() {
        super("Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color blue = new Color(28, 90, 232);
        Color gold = new Color(232, 170, 28);

        pesan = new JPanel(new GridBagLayout());
        pesan.setBackground(blue);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        ImageIcon logoIcon = new ImageIcon("src/UAS/view/image/logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        pesan.add(logoLabel, gbc);

        JLabel judulAtas = new JLabel("Golden Eagle Company");
        judulAtas.setFont(new Font("Arial", Font.PLAIN, 50));
        judulAtas.setForeground(gold);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 5;
        pesan.add(judulAtas, gbc);

        login = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        login.setFont(new Font("Arial", Font.PLAIN, 16));
        login.setBackground(Color.BLUE);
        login.setForeground(Color.white);

        pesan.add(login, gbc);

        register = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 3;
        pesan.add(register, gbc);
        register.setFont(new Font("Arial", Font.PLAIN, 16));
        register.setBackground(Color.BLUE);
        register.setForeground(Color.white);

        addTransaction = new JButton("Add Transaction");
        gbc.gridx = 2;
        gbc.gridy = 3;
        pesan.add(addTransaction, gbc);
        addTransaction.setFont(new Font("Arial", Font.PLAIN, 16));
        addTransaction.setBackground(Color.BLUE);
        addTransaction.setForeground(Color.white);

        history = new JButton("Transaction History");
        gbc.gridx = 3;
        gbc.gridy = 3;
        pesan.add(history, gbc);
        history.setFont(new Font("Arial", Font.PLAIN, 16));
        history.setBackground(Color.BLUE);
        history.setForeground(Color.white);

        add(pesan);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView();
                dispose();
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterView();
                dispose();
            }
        });
        addTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (SingletonManager.getInstance().getUser() != null) {
                    new AddTransactionView();
                    dispose();
                } else {
                    showMessage("Mohon Login terlebih dahulu!");
                }
            }
        });
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (SingletonManager.getInstance().getUser() != null) {
                    ArrayList<Transaction> show = Transaction.getUserData(SingletonManager.getInstance().getUser().getId());
                    new HistoryView(show);
                    dispose();
                } else {
                    showMessage("Mohon Login terlebih dahulu!");
                }
            }

        });

        setVisible(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
