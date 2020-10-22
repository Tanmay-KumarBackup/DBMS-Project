package com.company;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.swing.*;
import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;



public class CustomerSection extends JFrame {

    private JButton modifyButton;
    private JTextField C_ID;
    private JTextField CName;
    private JTextField phNo;
    private JTextArea Addr;
    private JButton insertValueButton;
    private JComboBox comboBox1;
    private JTextField textField4;
    private JButton fetchDataButton;
    private JPanel CustomerRoot;



    public static int insertCandidate(int customer_ID, String name, int Phno,
                                      String address) {
        // for insert a new candidate
        ResultSet rs = null;
        int candidateId = 0;

        String sql = "INSERT INTO Customer_details(customer_ID , name , PhNo , Address) "
                + "VALUES(?,?,?,?)";

        try (Connection conn = MySQLJDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            // set parameters for statement
            pstmt.setString(1, String.valueOf(customer_ID));
            pstmt.setString(2, name);
            pstmt.setString(3, String.valueOf(Phno));
            pstmt.setString(4, address);

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

    public CustomerSection() {
        add(CustomerRoot);
        setTitle("Customer Section");
        setSize(750, 300);


        insertValueButton.addActionListener(actionEvent -> {

            String name = CName.getText();
            String addr = Addr.getText();
            int phone = Integer.parseInt(phNo.getText());
            int c_id = Integer.parseInt(C_ID.getText());
            int id = insertCandidate(c_id, name, phone, addr);

            System.out.printf("A new candidate with id %d has been inserted.%n",id);
        });
    }

}
