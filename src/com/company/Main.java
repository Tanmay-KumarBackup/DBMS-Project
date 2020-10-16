package com.company;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        UIManager.setInstalledLookAndFeels(UIManager.getInstalledLookAndFeels());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CustomerSection CustomerSection = new CustomerSection();
                CustomerSection.setVisible(true);
            }
        });

    }
}
