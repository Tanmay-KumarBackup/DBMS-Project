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
        add(MainP);
        Main_Page.setVisible(true);

        setTitle("Automobile Service Center");
        setSize(750, 300);

        UIManager.setInstalledLookAndFeels(UIManager.getInstalledLookAndFeels());
        customerSectionButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {

            CustomerSection customerSection = new CustomerSection();
            customerSection.setVisible(true);
        }));
    }
}
