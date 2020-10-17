package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
class MysqlCon{
    public MysqlCon(String sql) {

    }

    public static void MysqlCon(String args){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Project_DBMS","root","TanuCherry1!");
            //here Project_DBMS is database name, root is username and TanuCherry1! is the password
            System.out.println("Connected database successfully...");
            System.out.println("Inserting records into the table...");
            Statement stmt=null;
            Connection conn=null;
            assert false;
            stmt = conn.createStatement();

            stmt.executeUpdate(args);
            System.out.println("Inserted records into the table...");
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


        insertValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String name=CName.getText();
                String addr=Addr.getText();
                int phno=Integer.parseInt(phNo.getText());
                int c_id=Integer.parseInt(C_ID.getText());
                String sql= "INSERT INTO Customer_details " +"VALUES ( "+c_id+","+CName+" , "+Addr+" , ";
                new MysqlCon(sql);

            }
        });
    }

}
