package com.company;

import javafx.util.converter.BigIntegerStringConverter;

import javax.swing.*;
import java.sql.*;
import java.math.BigInteger;
public class CustomerSection extends JFrame {

    private JButton modifyButton;
    private JTextField C_ID;
    private JTextField CName;
    private JTextField phNo;
    private JTextArea Addr;
    private JButton insertValueButton;
    private JComboBox<String> comboBox1;
    private JTextField fValue;
    private JButton fetchDataButton;
    private JPanel CustomerRoot;
    private JButton backToMainMenuButton;

    public static int insertCandidate(int customer_ID, String name, BigInteger Phno,
                                      String address) {
        // for insert a new candidate
        ResultSet rs = null;
        int candidateId = 0;

        String sql = "INSERT INTO Customer_details(customer_ID , name , PhNo , Address) "
                + "VALUES(?,?,?,?) ;";

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


    public CustomerSection() {

        add(CustomerRoot);
        setTitle("Customer Section");
        setSize(750, 300);


        insertValueButton.addActionListener(actionEvent -> {

            String name = CName.getText();
            String addr = Addr.getText();
            BigInteger phone = new BigInteger(phNo.getText());
            int c_id = Integer.parseInt(C_ID.getText());
            int id = insertCandidate(c_id, name, phone, addr);

            System.out.printf("A new candidate with id %d has been inserted.%n",id);
        });

        comboBox1.addItem("Customer_ID");
        comboBox1.addItem("Name");
        comboBox1.addItem("Phone Number");


        fetchDataButton.addActionListener(actionEvent -> {
            String fVal = fValue.getText();
            fetch(comboBox1.getSelectedIndex(),fVal);
        });
    }
}
