package com.company;

import javax.swing.*;
import java.sql.*;

public class Billing_Section {
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

    public static int insertBill(int customer_ID, String Details, String Phno,
                                 int Total) {
        // for insert a new candidate
        ResultSet rs = null;
        int candidateId = 0;

        String sql = "INSERT INTO Bill_details(customer_ID , name , PhNo , Address) "
                + "VALUES(?,?,?,?) ;";

        try (Connection conn = MySQLJDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            // set parameters for statement
            pstmt.setString(1, String.valueOf(customer_ID));
            pstmt.setString(2, Details);
            pstmt.setString(3, String.valueOf(Phno));
            pstmt.setString(4, String.valueOf(Total));

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
            query="SELECT * FROM Customer_details WHERE customer_ID = "+ Integer.parseInt(name)+";";
        }
        else if(index==1){
            query="SELECT * FROM Customer_details WHERE name = "+ name+";";
        }
        else if(index==2){
            query="SELECT * FROM Customer_details WHERE PhNo = "+ name+";";
        }
        ResultSet rs;

        try (Connection conn = MySQLJDBCUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.print(" Customer Name is: "+rs.getString("name") + "\n Phone Number is: "+ rs.getInt("PhNo")+"\n Address is: "+ rs.getString("Address"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public Billing_Section()
    {
        insertValueButton.addActionListener(actionEvent -> {
            String Date = BillDate.getText();
            String Detail= Details.getText();
            int c_id = Integer.parseInt(C_ID.getText());

            int Total = Integer.parseInt(TotalAmt.getText());
            int id = insertBill(c_id, Detail,Date, Total);

            System.out.printf("A new candidate with id %d has been inserted.%n",id);
        });

        comboBox1.addItem("Customer ID");
        comboBox1.addItem("Date");
        comboBox1.addItem("Vehicle ID");


        fetchDataButton.addActionListener(actionEvent -> {
            String fVal = fValue.getText();
            fetch(comboBox1.getSelectedIndex(),fVal);
        });
    }
}
