package UAS.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.*;

import UAS.controller.TransactionDetailController;
import UAS.model.classes.ShipmentDetails;
import UAS.model.enums.Status;

public class TransactionDetailView extends JFrame {

    private JPanel input;
    private JLabel transaction_idLabel, statusLabel, currentPositionLabel, evidenceLabel, updatedByLabel, logoLabel;
    private JTextField transaction_idField, currentPositionField, evidenceField, updatedByField;
    private JComboBox<Status> statusCombo;
    private JButton simpan, back, selectedPicButton;

    public TransactionDetailView() {
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

        transaction_idLabel = new JLabel("Transaction Id");
        transaction_idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        transaction_idLabel.setForeground(Color.white);

        transaction_idField = new JTextField(10);
        transaction_idField.setPreferredSize(new Dimension(300, 40));
        transaction_idField.setFont(new Font("Arial", Font.PLAIN, 16));
        transaction_idField.setBackground(Color.BLUE);
        transaction_idField.setForeground(Color.white);

        statusLabel = new JLabel("Status");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        statusLabel.setForeground(Color.white);

        statusCombo = new JComboBox<>(Status.values());
        statusCombo.setPreferredSize(new Dimension(300, 40));
        statusCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        statusCombo.setBackground(Color.BLUE);
        statusCombo.setForeground(Color.white);

        currentPositionLabel = new JLabel("Current Position");
        currentPositionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        currentPositionLabel.setForeground(Color.white);

        currentPositionField = new JTextField(10);
        currentPositionField.setPreferredSize(new Dimension(300, 40));
        currentPositionField.setFont(new Font("Arial", Font.PLAIN, 16));
        currentPositionField.setBackground(Color.BLUE);
        currentPositionField.setForeground(Color.white);

        evidenceLabel = new JLabel("Evidence");
        evidenceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        evidenceLabel.setForeground(Color.white);

        evidenceField = new JTextField();
        evidenceField.setPreferredSize(new Dimension(300, 40));
        evidenceField.setFont(new Font("Arial", Font.PLAIN, 16));
        evidenceField.setBackground(Color.BLUE);
        evidenceField.setForeground(Color.white);
        
        selectedPicButton = new JButton("Select New Picture");
        selectedPicButton.setFont(new Font("Arial", Font.PLAIN, 16));
        selectedPicButton.setBackground(Color.BLUE);
        selectedPicButton.setForeground(Color.white);
        
        updatedByLabel = new JLabel("Updated By");
        updatedByLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        updatedByLabel.setForeground(Color.white);
        
        updatedByField = new JTextField();
        updatedByField.setPreferredSize(new Dimension(300, 40));
        updatedByField.setFont(new Font("Arial", Font.PLAIN, 16));
        updatedByField.setBackground(Color.BLUE);
        updatedByField.setForeground(Color.white);
        
        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setBackground(Color.BLUE);
        back.setForeground(Color.white);

        simpan = new JButton("Simpan");
        simpan.setFont(new Font("Arial", Font.BOLD, 16));
        simpan.setBackground(Color.BLUE);
        simpan.setForeground(Color.white);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        input.add(logoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        input.add(transaction_idLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        input.add(transaction_idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        input.add(statusLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        input.add(statusCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        input.add(currentPositionLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        input.add(currentPositionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        input.add(evidenceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        input.add(evidenceField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        input.add(selectedPicButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        input.add(updatedByLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        input.add(updatedByField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        input.add(simpan, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        input.add(back, gbc);

        simpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int transaction_id = getTransaction_id();
                String status = getStatus();
                String currentPosition = getCurrentPosition();
                String evidence = getEvidence();
                String updatedBy = getUpdatedBy();

                TransactionDetailController transactionDetailController = new TransactionDetailController();

                if (transactionDetailController.isFilled(transaction_id, status, currentPosition, evidence,
                        updatedBy)) {
                    Date date = new Date(System.currentTimeMillis());
                    ShipmentDetails newShipmentDetails = new ShipmentDetails(transaction_id, Status.valueOf(status),
                            currentPosition, evidence, date, updatedBy);
                    if (transactionDetailController.insertDetail(newShipmentDetails)) {
                        showMessage("Data berhasil disimpan!");
                        new MainMenuView();
                        dispose();
                    } else {
                        showMessage("Data Gagal Disimpan!");
                    }

                } else {
                    showMessage("Mohon isi semua Field!");
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

        selectedPicButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a Picture");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                evidenceField.setText(selectedFilePath);
            }
        });

        add(input);

        setVisible(true);
    }

    public int getTransaction_id() {
        return Integer.parseInt(transaction_idField.getText());
    }

    public String getStatus() {
        return String.valueOf(statusCombo.getSelectedItem());
    }

    public String getCurrentPosition() {
        return currentPositionField.getText();
    }

    public String getEvidence() {
        return evidenceField.getText();
    }

    public String getUpdatedBy() {
        return updatedByField.getText();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
