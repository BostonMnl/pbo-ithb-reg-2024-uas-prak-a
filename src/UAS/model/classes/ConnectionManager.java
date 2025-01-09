package UAS.model.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;
import javax.swing.JOptionPane;

public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/db_uas_1123039?useSSL=false&serverTimezone=" + TimeZone.getDefault().getID();
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection con = null;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan: " + e.getMessage());
            throw new SQLException("Database driver error.");
        }
    }

    private Connection logOn() {
        try {
            con = getConnection();
            System.out.println("Koneksi berhasil.");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat login: " + ex);
        }
        return con;
    }

    private void logOff() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Koneksi berhasil ditutup.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menutup koneksi: " + ex);
        }
    }

    public void connect() {
        try {
            con = logOn();
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat menghubungkan ke database.");
        }
    }

    public void disconnect() {
        try {
            logOff();
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat memutuskan koneksi.");
        }
    }
}

