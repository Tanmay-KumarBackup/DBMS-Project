package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setSize(400,500);


        insertValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

}
