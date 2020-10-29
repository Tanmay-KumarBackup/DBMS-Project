package com.company;

import javax.swing.*;
import java.sql.*;

public class ServiceSection extends JFrame{
    private JPanel motherPanel;
    private JTextField Dateformat;
    private JTextField repCharges;
    private JTextArea details;
    private JButton insertValueButton;
    private JComboBox comboBox1;
    private JTextField textField3;
    private JButton fetchDataButton;
    private JButton modifyButton;
    private JTextField VehicleID;

    public static int insertService(String vehicleId,Date date,int Charges,String det) {
        ResultSet rs = null;
        int candidateId = 0;

        String sql = "INSERT INTO Service_Details(Vehicle_ID , DateofRepairing , frequency , Repairing_Charges, repairing_details ) "
                + "VALUES(?,?,?,?,?)" ;

        try (Connection conn = MySQLJDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, vehicleId);
            pstmt.setDate(2, date);
            pstmt.setInt(3, 1);
            pstmt.setInt(4,Charges);
            pstmt.setString(5,det);
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
            query="SELECT Service_Details.frequency, Service_Details.DateofRepairing, Vehicle_details.Vehicle_ID, Vehicle_details.Company FROM Service_Details JOIN Vehicle_details ON Service_Details.Vehicle_id= Vehicle_details.Vehicle_ID" +
                    " WHERE Service_Details.customer_ID = "+ name+";";
        }
        else if(index==1){
            query="SELECT Service_Details.frequency, Service_Details.DateofRepairing, Vehicle_details.Vehicle_ID, Vehicle_details.Company FROM Service_Details JOIN Vehicle_details ON Service_Details.Vehicle_id= Vehicle_details.Vehicle_ID" +
                    " WHERE Service_Details.Vehicle_id = "+ name+";";
        }
        else if(index==2){
            query="SELECT Service_Details.frequency, Service_Details.DateofRepairing, Vehicle_details.Vehicle_ID, Vehicle_details.Company FROM Service_Details JOIN Vehicle_details ON Service_Details.Vehicle_id= Vehicle_details.Vehicle_ID" +
                    " WHERE Service_Details.Repairing_Charges = "+ Integer.parseInt(name)+";";
        }
        ResultSet rs;

        try (Connection conn = MySQLJDBCUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {

            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.print(" Vehicle ID is: "+rs.getString("Vehicle_details.Vehicle_ID") +"\n servicing Date is: "
                        + rs.getDate("Service_Details.DateofRepairing") +"\n Service Frequency is: "+rs.getInt("Service_Details.frequency")+
                        "\n Vehicle Company : "+rs.getString("Vehicle_details.Company")+"\n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ServiceSection(){
        add(motherPanel);
        motherPanel.setVisible(true);
        setSize(800, 300);
        setTitle("Service Details");

        comboBox1.addItem("Date");
        comboBox1.addItem("Vehicle_ID");
        comboBox1.addItem("Repairing Charges");

        insertValueButton.addActionListener(e-> {
            String vehicleId = VehicleID.getText();

            int Charges = Integer.parseInt(repCharges.getText());
            String det =details.getText();
            String date = Dateformat.getText();
            Date date1= Date.valueOf(date);
            int id = insertService(vehicleId, date1,Charges,det);
            System.out.printf("A new candidate with id %d has been inserted.%n",id);
        });


    }
}
