package UAS.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import UAS.model.classes.ShipmentDetails;
import UAS.model.classes.Transaction;

public class HistoryView extends JFrame {
    private JPanel mainPanel, dataPanel, buttonPanel;
    private JLabel header;
    private JTextField namaField, alamatField, noTelpField, beratPaketField;
    private JComboBox<String> tipePaketCombo;
    private JButton back;
    private DefaultTableModel tableModel;
    private JTable historyTable;

    public HistoryView(ArrayList<Transaction> transactionsList) {
        super("History");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setFont(new Font("Arial", Font.BOLD, 30));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color blue = new Color(28, 90, 232);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(blue);

        dataPanel = new JPanel(new BorderLayout());
        dataPanel.setOpaque(false);

        header = new JLabel("History", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 28));
        header.setForeground(Color.BLUE);
        dataPanel.add(header, BorderLayout.NORTH);

        String[] columnNames = { "ID", "package_type", "package_weight", "total_cost", "created_at", "updated_at",
                "Detail" };
        tableModel = new DefaultTableModel(columnNames, 0);
        historyTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 6;
            }
        };
        for (Transaction t : transactionsList) {
            int id = t.getId();
            String package_type = t.getPackage_type();
            Double package_weight = t.getPackage_weight();
            int totalCost = t.getTotal_cost();
            Date created_at = t.getCreated_at();
            ShipmentDetails details = ShipmentDetails.getNewestDetail(t.getId());
            Date updated_at = details.getDate();

            tableModel.addRow(new Object[] {
                    id,
                    package_type,
                    package_weight,
                    totalCost,
                    created_at,
                    updated_at,
                    "Detail"
            });
        }

        historyTable.getColumn("Detail").setCellRenderer(new ButtonRenderer());

        historyTable.getColumn("Detail").setCellEditor(new ButtonEditor(new JButton("Detail"), historyTable, e -> {
            int id = Integer.valueOf(e.getActionCommand());
            // 
            dispose();
        }));

        JScrollPane scrollPane = new JScrollPane(historyTable);
        dataPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(dataPanel, BorderLayout.CENTER);

        // Back button
        back = new JButton("Back");

        // Add new Button

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLUE);
        buttonPanel.add(back);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuView();
                dispose();
            }
        });

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
        return Double.parseDouble(beratPaketField.getText());
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

    private static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    private static class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private JTable table;
        private ActionListener actionListener;

        public ButtonEditor(JButton button, JTable table, ActionListener actionListener) {
            super(new JTextField());
            this.button = button;
            this.button.setOpaque(true);
            this.table = table;
            this.actionListener = actionListener;

            this.button.addActionListener(e -> {
                int row = table.getEditingRow();
                if (row >= 0) {
                    Object idObj = table.getValueAt(row, 0);
                    if (idObj != null) {
                        String id = idObj.toString();
                        actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, id));
                    }
                }
                fireEditingStopped();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            button.setText(value != null ? value.toString() : "");
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }
    }

}
