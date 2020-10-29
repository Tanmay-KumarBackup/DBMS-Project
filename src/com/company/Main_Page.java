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
        add(Main_Page);
        Main_Page.setVisible(true);

        setTitle("Automobile Service Center");
        setSize(800, 300);

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
        serviceSectionButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {

            ServiceSection serviceSection = new ServiceSection();
            serviceSection.setVisible(true);
        }));
        UIManager.setInstalledLookAndFeels(UIManager.getInstalledLookAndFeels());
        vehicleSectionButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {

            VehicleSection Vehicle_section = new VehicleSection();
            Vehicle_section.setVisible(true);
        }));
    }
}
