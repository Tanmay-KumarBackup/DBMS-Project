package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Page extends JFrame{
    private JButton customerSectionButton;
    private JPanel Main_Page;
    private JPanel MainP;
    private JPanel MotherPanel;
    private JButton billingSectionButton;
    private JButton vehicleSectionButton;
    private JButton serviceSectionButton;

    public Main_Page(){
        add(MotherPanel);
        Main_Page.setVisible(true);

        setTitle("Automobile Service Center");
        setSize(850, 300);

        UIManager.setInstalledLookAndFeels(UIManager.getInstalledLookAndFeels());
        customerSectionButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {

            CustomerSection customerSection = new CustomerSection();
            customerSection.setVisible(true);
        }));
        UIManager.setInstalledLookAndFeels(UIManager.getInstalledLookAndFeels());
        billingSectionButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {

            Billing_Section Billing_Section = new Billing_Section();
            Billing_Section.setVisible(true);
        }));
        UIManager.setInstalledLookAndFeels(UIManager.getInstalledLookAndFeels());
        vehicleSectionButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {

            VehicleSection VehicleSection = new VehicleSection();
            VehicleSection.setVisible(true);
        }));

    }
}
