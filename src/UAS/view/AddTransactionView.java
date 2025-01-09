package UAS.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.*;

import UAS.controller.TransactionController;
import UAS.model.classes.Customer;
import UAS.model.classes.Transaction;

public class AddTransactionView extends JFrame {
    private JPanel input;
    private JLabel namaLabel, alamatLabel, noTelpLabel, beratPaketLabel, tipePaketLabel, logoLabel;
    private JTextField namaField, alamatField, noTelpField, beratPaketField;
    private JComboBox<String> tipePaketCombo;
    private JButton back, simpan, addDetail;

    public AddTransactionView() {
        super("Add New Transaction");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color blue = new Color(28, 90, 232);

        input = new JPanel(new GridBagLayout());
        input.setBackground(blue);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        ImageIcon logoIcon = new ImageIcon("src/UAS/view/image/logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);
        logoLabel = new JLabel(logoIcon);

        namaLabel = new JLabel("Nama Penerima");
        namaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        namaLabel.setForeground(Color.white);

        namaField = new JTextField(10);
        namaField.setPreferredSize(new Dimension(300, 40));
        namaField.setFont(new Font("Arial", Font.PLAIN, 16));
        namaField.setBackground(Color.BLUE);
        namaField.setForeground(Color.white);

        alamatLabel = new JLabel("Alamat Penerima");
        alamatLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        alamatLabel.setForeground(Color.white);

        alamatField = new JTextField(10);
        alamatField.setPreferredSize(new Dimension(300, 40));
        alamatField.setFont(new Font("Arial", Font.PLAIN, 16));
        alamatField.setBackground(Color.BLUE);
        alamatField.setForeground(Color.white);

        noTelpLabel = new JLabel("No. Telephone Penerima");
        noTelpLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        noTelpLabel.setForeground(Color.white);

        noTelpField = new JTextField(10);
        noTelpField.setPreferredSize(new Dimension(300, 40));
        noTelpField.setFont(new Font("Arial", Font.PLAIN, 16));
        noTelpField.setBackground(Color.BLUE);
        noTelpField.setForeground(Color.white);

        beratPaketLabel = new JLabel("Berat Paket");
        beratPaketLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        beratPaketLabel.setForeground(Color.white);

        beratPaketField = new JTextField();
        beratPaketField.setPreferredSize(new Dimension(300, 40));
        beratPaketField.setFont(new Font("Arial", Font.PLAIN, 16));
        beratPaketField.setBackground(Color.BLUE);
        beratPaketField.setForeground(Color.white);

        tipePaketLabel = new JLabel("Tipe Paket");
        tipePaketLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        tipePaketLabel.setForeground(Color.white);

        ArrayList<String> tipePaketList = Transaction.getCategoryPackage();
        String[] categoryArr = tipePaketList.toArray(new String[0]);
        tipePaketCombo = new JComboBox<>(categoryArr);
        tipePaketCombo.setPreferredSize(new Dimension(300, 40));
        tipePaketCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        tipePaketCombo.setBackground(Color.BLUE);
        tipePaketCombo.setForeground(Color.white);

        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setBackground(Color.BLUE);
        back.setForeground(Color.white);

        simpan = new JButton("Simpan");
        simpan.setFont(new Font("Arial", Font.BOLD, 16));
        simpan.setBackground(Color.BLUE);
        simpan.setForeground(Color.white);

        addDetail = new JButton("Add Details");
        addDetail.setFont(new Font("Arial", Font.BOLD, 16));
        addDetail.setBackground(Color.BLUE);
        addDetail.setForeground(Color.white);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        input.add(logoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        input.add(namaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        input.add(namaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        input.add(alamatLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        input.add(alamatField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        input.add(noTelpLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        input.add(noTelpField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        input.add(beratPaketLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        input.add(beratPaketField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        input.add(tipePaketLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        input.add(tipePaketCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        input.add(simpan, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        input.add(addDetail, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        input.add(back, gbc);

        simpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = getNama();
                String alamat = getAlamat();
                String noTelp = getNoTelp();
                double beratPaket = getBeratPaket();
                String typePaket = getTipePaket();

                TransactionController transactionController = new TransactionController();

                if (transactionController.isFilled(nama, alamat, noTelp, beratPaket, typePaket)) {
                    int total_cost = 0;
                    if (beratPaket - Math.floor(beratPaket) > 0.5) {
                        total_cost = (int) Math.ceil(beratPaket);
                    } else {
                        total_cost = (int) Math.floor(beratPaket);
                    }

                    Date createdAt = new Date(System.currentTimeMillis());
                    int customer_id = Customer.getCustomer_id(nama, noTelp);
                    if (customer_id == 0) {
                        showMessage("Penerima tidak ditemukan di database!");
                    } else {
                        Transaction newTransaction = new Transaction(customer_id, typePaket, beratPaket, total_cost,
                                createdAt, nama, alamat, noTelp);
                        if (transactionController.insertTransactionData(newTransaction)) {
                            showMessage("Data berhasil disimpan!");
                            new MainMenuView();
                            dispose();
                        } else {
                            showMessage("Data Gagal Disimpan!");
                        }
                    }

                } else {
                    showMessage("Mohon isi semua Field!");
                }
            }
        });

        addDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showConfirmationDialog("Data anda di halaman ini tidak akan tersimpan, Ingin tetap melanjutkan?")) {
                    new TransactionDetailView();
                    dispose();
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuView();
                dispose();
            }
        });

        add(input);

        setVisible(true);
    }

    public String getNama() {
        return namaField.getText();
    }

    public String getAlamat() {
        return alamatField.getText();
    }

    public String getNoTelp() {
        return noTelpField.getText();
    }

    public double getBeratPaket() {
        try {
            return Double.parseDouble(beratPaketField.getText());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public String getTipePaket() {
        return String.valueOf(tipePaketCombo.getSelectedItem());
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public boolean showConfirmationDialog(String message) {
        int option = JOptionPane.showConfirmDialog(this, message, "Leave?", JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

}
