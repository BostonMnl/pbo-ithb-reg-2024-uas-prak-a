package UAS.view;

import javax.swing.*;
import javax.swing.border.Border;

import UAS.controller.RegisterController;
import UAS.model.classes.Customer;
import UAS.model.classes.SingletonManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {
    private JPanel frame, input;
    private JLabel logoLabel, passwordLabel, nameLabel, addressLabel, phoneLabel, emaiLabel;
    private JTextField namaField, addressField, phoneField, emailField;
    private JPasswordField passwordField;
    private JButton backButton, registerButton;
    private Border roundedBorder = BorderFactory.createLineBorder(Color.white, 2, true);

    public RegisterView() {
        super("Register");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color blue = new Color(28, 90, 232);

        frame = new JPanel(new GridBagLayout());
        frame.setBackground(blue);

        input = new JPanel(new GridLayout(11, 2, 15, 10));
        input.setOpaque(false);

        ImageIcon logoIcon = new ImageIcon("src/UAS/view/image/logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon);

        input.add(nameLabel = new JLabel("Nama"));
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setForeground(Color.white);

        namaField = new JTextField(10);
        namaField.setBorder(roundedBorder);
        namaField.setPreferredSize(new Dimension(300, 40));
        namaField.setFont(new Font("Arial", Font.PLAIN, 16));
        namaField.setBackground(Color.BLUE);
        namaField.setForeground(Color.white);
        input.add(namaField);

        input.add(addressLabel = new JLabel("Address"));
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        addressLabel.setForeground(Color.white);

        addressField = new JTextField(10);
        addressField.setBorder(roundedBorder);
        addressField.setPreferredSize(new Dimension(300, 40));
        addressField.setFont(new Font("Arial", Font.PLAIN, 16));
        addressField.setBackground(Color.BLUE);
        addressField.setForeground(Color.white);
        input.add(addressField);

        input.add(emaiLabel = new JLabel("Email"));
        emaiLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emaiLabel.setForeground(Color.white);

        emailField = new JTextField(10);
        emailField.setBorder(roundedBorder);
        emailField.setPreferredSize(new Dimension(300, 40));
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField.setBackground(Color.BLUE);
        emailField.setForeground(Color.white);
        input.add(emailField);

        input.add(passwordLabel = new JLabel("Password"));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.white);

        passwordField = new JPasswordField(10);
        passwordField.setBorder(roundedBorder);
        passwordField.setPreferredSize(new Dimension(300, 40));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBackground(Color.BLUE);
        passwordField.setForeground(Color.white);
        input.add(passwordField);

        input.add(phoneLabel = new JLabel("No. Telepon"));
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        phoneLabel.setForeground(Color.white);

        phoneField = new JTextField(10);
        phoneField.setBorder(roundedBorder);
        phoneField.setPreferredSize(new Dimension(300, 40));
        phoneField.setFont(new Font("Arial", Font.PLAIN, 16));
        phoneField.setBackground(Color.BLUE);
        phoneField.setForeground(Color.white);
        input.add(phoneField);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.white);

        registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(Color.BLUE);
        registerButton.setForeground(Color.white);

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

        input.add(registerButton);
        input.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuView();
                dispose();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = getPassword();
                String name = getName();
                String address = getAddress();
                String phone = getPhone();
                String email = getEmail();

                RegisterController regist = new RegisterController();

                if (regist.isFilled(password, name, address, phone, email)) {
                    Customer customer = new Customer(password, name, address, phone, email);
                    if (Customer.canRegister(phone, email)) {
                        if (Customer.Register(customer)) {
                            showMessage("Register berhasil");
                            SingletonManager.getInstance().setUser(customer);
                            new MainMenuView();
                            dispose();
                        } else {
                            showMessage("Gagal Register");
                        }
                    } else {
                        showMessage(
                                "Nomor Telepon atau Email sudah terdaftar, Mohon gunakan email atau nomor telepon yang lain!");
                    }
                } else {
                    showMessage("Mohon isi semua field");
                }

            }
        });

        add(frame);

        setVisible(true);
    }

    public JButton getLoginButton() {
        return backButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getName() {
        return namaField.getText();
    }

    public String getAddress() {
        return addressField.getText();
    }

    public String getPhone() {
        return phoneField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
