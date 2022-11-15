package ui;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        ImageIcon image = new ImageIcon(new ImageIcon("src/main/logo.png").getImage().getScaledInstance(
                250, 250, Image.SCALE_DEFAULT));


        JLabel label = new JLabel();
        label.setText("Welcome to the Shelter App");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(new Color(0,102,0));
        label.setFont(new Font("MV Boli", Font.PLAIN, 35));
        label.setIconTextGap(0);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(225,0,500,300);


        JFrame frame = new JFrame(); //creates the frame
        frame.setTitle("Shelter App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,700); //sets dimensioon
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(204,229,250));
        frame.add(label);
        frame.setLayout(null);
        //frame.pack() need to add all components and then pack

//
//        try {
//            new ShelterApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
    }
}