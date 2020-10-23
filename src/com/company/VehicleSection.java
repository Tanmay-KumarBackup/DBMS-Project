package com.company;

import javax.swing.*;
import java.sql.*;

public class VehicleSection extends JFrame{

    private JPanel panel1;
    private JButton backToMainMenuButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton insertValueButton;
    private JButton insertHistoryButton;
    private JButton fetchDataButton;
    private JComboBox comboBox1;
    private JTextField textField4;
    private JButton modifyButton;

    public VehicleSection(){
        add(panel1);
        panel1.setVisible(true);
    }
}
