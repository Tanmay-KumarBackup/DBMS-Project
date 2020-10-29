package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VehicleSection extends JFrame{

    private JPanel panel1;
    private JButton backToMainMenuButton;
    private JTextField VehicleID;
    private JTextField company;

    private JButton insertValueButton;
    private JButton insertHistoryButton;
    private JButton fetchDataButton;
    private JComboBox<String> comboBox1;
    private JTextField fValue;
    private JButton modifyButton;
    private JRadioButton bike;
    private JTextField quantity;
    private JRadioButton car;
    private JRadioButton scooty;

    public static int insertVehicle(String vehicleId, String comp, int qun, String cat) {
        ResultSet rs = null;
        int candidateId = 0;

        String sql = "INSERT INTO Vehicle_details(Vehicle_ID , Category , Company , Quantity) "
                + "VALUES(?,?,?,?)" ;

        try (Connection conn = MySQLJDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, vehicleId);
            pstmt.setString(2, cat);
            pstmt.setString(3, comp);
            pstmt.setInt(4,qun);
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
            query="SELECT * FROM Vehicle_details WHERE Vehicle_ID = ?;";
        }
        else if(index==1){
            query="SELECT * FROM Vehicle_details WHERE Company = ?;";
        }
        else if(index==2){
            query="SELECT * FROM Vehicle_details WHERE Category = ?;";
        }
        ResultSet rs;

        try (Connection conn = MySQLJDBCUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {
            stmt.setString(1,name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.print(" Vehicle ID is: "+rs.getString("Vehicle_ID") + "\n Company is: "+ rs.getString("Company")+"\n Category is: "+ rs.getString("Category")+
                        "\n Quantity is:" +rs.getInt("Quantity"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }



    public VehicleSection(){
        add(panel1);
        panel1.setVisible(true);
        setSize(750, 300);
        setTitle("Vehicles Details");
        bike.setSelected(false);
        scooty.setSelected(false);
        car.setSelected(false);

        comboBox1.addItem("Vehicle ID");
        comboBox1.addItem("Category");
        comboBox1.addItem("Company");


        insertValueButton.addActionListener(e-> {
            String vehicleId = VehicleID.getText();
            String Comp= company.getText();
            int qun = Integer.parseInt(quantity.getText());
            String cat =null;
            if(bike.isSelected())           { cat= "Bike"; }
            else if(scooty.isSelected())    { cat = "Scooty"; }
            else if(car.isSelected())       { cat = "Car"; }

            int id = insertVehicle(vehicleId, Comp,qun,cat);
            System.out.printf("A new candidate with id %d has been inserted.%n",id);
        });
        fetchDataButton.addActionListener(e -> {
            fetch(comboBox1.getSelectedIndex(), fValue.getText());
        });
        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    Main_Page Main_Page = new Main_Page();
                    Main_Page.setVisible(true);
                });
            }
        });
    }

}
