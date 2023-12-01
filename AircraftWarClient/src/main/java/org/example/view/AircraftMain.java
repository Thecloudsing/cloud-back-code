package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AircraftMain extends JFrame {
    public static Container contentPane;
    public AircraftMain() {
        this.setBounds(400,300,800,600);
        this.setLayout(null);
        this.setTitle("AircraftWar");
        URL AircraftWarIoc = this.getClass().getClassLoader().getResource("AircraftWar.png");
        System.out.println(AircraftWarIoc);
        ImageIcon imageIcon = new ImageIcon(AircraftWarIoc);
        this.setIconImage(imageIcon.getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPane = this.getContentPane();
        AircraftJPanel aircraftJPanel = new AircraftJPanel();
        contentPane.add(aircraftJPanel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new AircraftMain();
    }
}
