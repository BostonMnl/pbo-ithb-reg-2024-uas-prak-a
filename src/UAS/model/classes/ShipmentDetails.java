package UAS.model.classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import UAS.model.enums.Status;

public class ShipmentDetails {
    private int id;
    private int transaction_id;
    private Status status;
    private String current_position;
    private String evidence;
    private Date date;
    private String updated_by;

    public ShipmentDetails() {
    }

    public ShipmentDetails(int id, int transaction_id, Status status, String current_position, String evidence,
            Date date, String updated_by) {
        this.id = id;
        this.transaction_id = transaction_id;
        this.status = status;
        this.current_position = current_position;
        this.evidence = evidence;
        this.date = date;
        this.updated_by = updated_by;
    }

    public ShipmentDetails(int transaction_id, Status status, String current_position, String evidence,
            Date date, String updated_by) {
        this.transaction_id = transaction_id;
        this.status = status;
        this.current_position = current_position;
        this.evidence = evidence;
        this.date = date;
        this.updated_by = updated_by;
    }

    public ShipmentDetails(int id, int transaction_id, Date date) {
        this.id = id;
        this.transaction_id = transaction_id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCurrent_position() {
        return current_position;
    }

    public void setCurrent_position(String current_position) {
        this.current_position = current_position;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public static boolean insertDetails(ShipmentDetails details) {
        String query = "INSERT INTO shipmentdetails (transaction_id, status, current_position, evidence, date, updated_by) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, details.getTransaction_id());
            st.setString(2, String.valueOf(details.getStatus()));
            st.setString(3, details.getCurrent_position());
            st.setString(4, details.getEvidence());
            st.setDate(5, details.getDate());
            st.setString(6, details.getUpdated_by());

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat registrasi: " + ex.getMessage());
            return false;
        }
    }

    public static ShipmentDetails getNewestDetail(int transaction_id) {
        ShipmentDetails detail = null;
        String query = "SELECT * FROM shipmentdetails WHERE transaction_id = ? ORDER BY date DESC LIMIT 1";
        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, transaction_id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int trans_id = rs.getInt("transaction_id");
                    Date date = rs.getDate("date");
                    detail = new ShipmentDetails(id, trans_id, date);
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
        }
        return detail;
    }

}
