package UAS.model.classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Transaction {
    private int id;
    private int customer_id;
    private String package_type;
    private double package_weight;
    private int total_cost;
    private Date created_at;
    private String receipt_name;
    private String receipt_address;
    private String receipt_phone;

    public Transaction() {
    }

    public Transaction(int id, int customer_id, String package_type, double package_weight, int total_cost,
            Date created_at, String receipt_name, String receipt_address, String receipt_phone) {
        this.id = id;
        this.customer_id = customer_id;
        this.package_type = package_type;
        this.package_weight = package_weight;
        this.total_cost = total_cost;
        this.created_at = created_at;
        this.receipt_name = receipt_name;
        this.receipt_address = receipt_address;
        this.receipt_phone = receipt_phone;
    }

    public Transaction(int customer_id, String package_type, double package_weight, int total_cost,
            Date created_at, String receipt_name, String receipt_address, String receipt_phone) {
        this.customer_id = customer_id;
        this.package_type = package_type;
        this.package_weight = package_weight;
        this.total_cost = total_cost;
        this.created_at = created_at;
        this.receipt_name = receipt_name;
        this.receipt_address = receipt_address;
        this.receipt_phone = receipt_phone;
    }

    public Transaction(int id, String package_type, double package_weight, int total_cost, Date created_at) {
        this.id = id;
        this.package_type = package_type;
        this.package_weight = package_weight;
        this.total_cost = total_cost;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getPackage_type() {
        return package_type;
    }

    public void setPackage_type(String package_type) {
        this.package_type = package_type;
    }

    public double getPackage_weight() {
        return package_weight;
    }

    public void setPackage_weight(double package_weight) {
        this.package_weight = package_weight;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getReceipt_name() {
        return receipt_name;
    }

    public void setReceipt_name(String receipt_name) {
        this.receipt_name = receipt_name;
    }

    public String getReceipt_address() {
        return receipt_address;
    }

    public void setReceipt_address(String receipt_address) {
        this.receipt_address = receipt_address;
    }

    public String getReceipt_phone() {
        return receipt_phone;
    }

    public void setReceipt_phone(String receipt_phone) {
        this.receipt_phone = receipt_phone;
    }

    public static ArrayList<String> getCategoryPackage() {
        ArrayList<String> categoryList = new ArrayList<>();
        String query = "SELECT * FROM category_package";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String category = rs.getString("category");
                    categoryList.add(category);
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return categoryList;
    }

    public static boolean insertTransaction(Transaction transaction) {
        String query = "INSERT INTO Transaction (customer_id, package_type, package_weight, total_cost, create_at, receipt_name, receipt_address, receipt_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, transaction.getCustomer_id());
            st.setString(2, transaction.getPackage_type());
            st.setDouble(3, transaction.getPackage_weight());
            st.setInt(4, transaction.getTotal_cost());
            st.setDate(5, transaction.getCreated_at());
            st.setString(6, transaction.getReceipt_name());
            st.setString(7, transaction.getReceipt_address());
            st.setString(8, transaction.getReceipt_phone());

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat Insert: " + ex.getMessage());
            return false;
        }
    }

    public static ArrayList<Transaction> getUserData(int customer_id) {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        String query = "SELECT * FROM transaction WHERE customer_id = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, customer_id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int transaction_id = rs.getInt("transaction_id");
                    String package_Type = rs.getString("package_type");
                    double package_weight = rs.getDouble("package_weight");
                    int total_cost = rs.getInt("total_cost");
                    Date created_at = rs.getDate("created_at");

                    transactionList.add(new Transaction(transaction_id, package_Type, package_weight, total_cost, created_at));
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return transactionList;
    }

}
