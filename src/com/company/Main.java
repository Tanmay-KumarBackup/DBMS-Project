package com.company;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        UIManager.setInstalledLookAndFeels(UIManager.getInstalledLookAndFeels());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main_Page MainPage= new Main_Page();
                MainPage.setVisible(true);

            }
        });

    }
}
