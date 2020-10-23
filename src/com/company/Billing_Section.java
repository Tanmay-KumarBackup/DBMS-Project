package com.company;

import javax.swing.*;
import java.sql.*;

public class Billing_Section extends JFrame{
    private JPanel CustomerRoot;
    private JTextField C_ID;
    private JTextField BillDate;
    private JTextField fValue;
    private JComboBox<String> comboBox1;
    private JButton modifyButton;
    private JButton insertValueButton;
    private JButton fetchDataButton;
    private JButton backToMainMenuButton;
    private JTextField TotalAmt;
    private JTextArea Details;
    private JTextField Vehicle_ID;
    private JPanel ParentBill;

    public static int insertBill(int customer_ID, String Details, Date date, String VehicleID,int Total) {
        // for insert a new candidate
        ResultSet rs = null;
        int candidateId = 0;

        String sql = "INSERT INTO Billing_details(customer_ID , Billing_details , Billing_DATE , Vehicle_ID , Total_Charges) "
                + "VALUES(?,?,?,?,?) ;";

        try (Connection conn = MySQLJDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            // set parameters for statement
            pstmt.setString(1, String.valueOf(customer_ID));
            pstmt.setString(2, Details);
            pstmt.setDate(3, date);
            pstmt.setString(4,VehicleID);
            pstmt.setString(5, String.valueOf(Total));

            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
                // get candidate id
                rs = pstmt.getGeneratedKeys();
                if (rs.next())
                    candidateId = rs.getInt(1);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return candidateId;
    }

    public static void fetch(int index, String name) {
        String query = null;
        if(index==0) {
            query="SELECT Billing_details.customer_ID, Customer_details.name, Billing_Date, billing_details FROM Billing_details JOIN Customer_details ON Billing_details.Customer_ID=Customer_details.Customer_ID" +
                    " WHERE Billing_details.customer_ID = "+ Integer.parseInt(name)+";";
        }
        else if(index==1){
            query="SELECT Billing_details.customer_ID, Customer_details.name, Billing_Date, billing_details FROM Billing_details JOIN Customer_details ON Billing_details.Customer_ID=Customer_details.Customer_ID" +
                    " WHERE Billing_details.Billing_DATE = "+ name+";";
        }
        else if(index==2){
            query="SELECT Billing_details.customer_ID, Customer_details.name, Billing_Date, billing_details  FROM Billing_details JOIN Customer_details ON Billing_details.Customer_ID=Customer_details.Customer_ID" +
                    " WHERE Billing_details.Vehicle_ID = "+ name+";";
        }
        ResultSet rs;

        try (Connection conn = MySQLJDBCUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.print(" Customer name is: "+rs.getString("Customer_details.name") +"\n Billing Date is: "
                        + rs.getDate("Billing_details.Billing_DATE") +"\n Billing details: "+rs.getString("billing_details"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public Billing_Section()
    {
        add(ParentBill);
        ParentBill.setVisible(true);
        setSize(750, 300);
        setTitle("Billing Details");
        insertValueButton.addActionListener(actionEvent -> {
            String date = BillDate.getText();
            String Detail= Details.getText();
            int c_id = Integer.parseInt(C_ID.getText());
            String VehicleID=Vehicle_ID.getText();
            int Total = Integer.parseInt(TotalAmt.getText());
            Date date1= Date.valueOf(date);

            int id = insertBill(c_id, Detail,date1,VehicleID,Total);

            System.out.printf("A new candidate with id %d has been inserted.%n",id);
        });

        comboBox1.addItem("Customer ID");
        comboBox1.addItem("Date");
        comboBox1.addItem("Vehicle ID");
;

        fetchDataButton.addActionListener(actionEvent -> {
            String fVal = fValue.getText();
            fetch(comboBox1.getSelectedIndex(),fVal);
        });

        UIManager.setInstalledLookAndFeels(UIManager.getInstalledLookAndFeels());
        backToMainMenuButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                Main_Page Main_Page = new Main_Page();
                Main_Page.setVisible(true);
            });
        });
    }
}
