package main.java.com.ben.brat.control;

import main.java.com.ben.brat.panels.SuperPanel;

import javax.swing.*;

/**
 * Class description here
 * Package: main.java.com.ben.brat.control
 */
public class Driver {
    public static void main (String[] args) {
        JFrame f = new JFrame();
        SuperPanel container = new SuperPanel();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(container);
        f.pack();
        f.setVisible(true);
    }
}
