package UAS.model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Customer {
    private int id;
    private String password;
    private String name;
    private String address;
    private String phone;
    private String email;

    public Customer() {
    }

    public Customer(int id, String password, String name, String address, String phone, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Customer(String password, String name, String address, String phone, String email) {
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static boolean Login(String phone, String password) {
        String query = "SELECT * FROM customer WHERE phone = ? and password = ?";

        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, phone);
            st.setString(2, password);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return false;
        }
    }

    public static boolean canRegister(String phone, String email) {
        String query = "SELECT * FROM customer WHERE phone = ? AND email = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, phone);
            st.setString(2, email);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return false;
        }
    };

    public static boolean Register(Customer customer) {
        String query = "INSERT INTO customer (password, name, address, phone, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, customer.getPassword());
            st.setString(2, customer.getName());
            st.setString(3, customer.getAddress());
            st.setString(4, customer.getPhone());
            st.setString(5, customer.getEmail());
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat registrasi: " + ex.getMessage());
            return false;
        }
    }

    public static Customer getData(String phone, String password) {
        String query = "SELECT * FROM customer WHERE phone = ? AND password = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, phone);
            st.setString(2, password);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    return new Customer(id, password, name, address, phone, email);
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan getData customer: " + ex.getMessage());
        }
        return null;
    }

    public static int getCustomer_id(String nama, String noTelp) {
        String query = "SELECT * FROM customer WHERE name = ? AND phone = ?";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, nama);
            st.setString(2, noTelp);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    return id;
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return 0;
    }
}
