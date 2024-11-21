package view;

import javax.swing.*;
import java.awt.*;
import interface_adapter.services.ServiceManager;

public class MainPageTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Page Preview");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel parentPanel = new JPanel(cardLayout);

        MainPage mainPage = new MainPage(cardLayout, parentPanel);
        parentPanel.add(mainPage, "main");

        frame.add(parentPanel);

        frame.setVisible(true);
    }
}