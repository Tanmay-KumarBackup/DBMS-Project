package com.company;

import javax.swing.*;

import java.sql.*;
class MysqlCon{
    public MysqlCon(String sql) {

    }

    public static void mysqlCon(String args){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Project_DBMS","root","0310");
            //here Project_DBMS is database name, root is username and 0310 is the password
            System.out.println("Connected database successfully...\n");
            System.out.println("Inserting records into the table...\n");
            Statement stmt;
            Connection conn=null;
            assert false;
            stmt = conn.createStatement();

            stmt.executeUpdate(args);
            System.out.println("Inserted records into the table...\n\n");
        }catch(Exception se){ System.out.println(se);}

    }
}


public class CustomerSection extends JFrame{
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

    public CustomerSection()
    {
        add(CustomerRoot);
        setTitle("Customer Section");
        setSize(750,300);


        insertValueButton.addActionListener(actionEvent -> {

            String name=CName.getText();
            String addr=Addr.getText();
            int phone=Integer.parseInt(phNo.getText());
            int c_id=Integer.parseInt(C_ID.getText());
            String sql= "INSERT INTO Customer_details(customer_ID , name , PhNo , Address) " +"VALUES ( "+c_id+","+CName+" , "+phone+" , "+Addr+" );";
            MysqlCon.mysqlCon(sql);


        });
    }

}
