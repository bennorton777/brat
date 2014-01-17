package main.java.com.ben.brat.control;

import javax.swing.*;
import java.awt.*;

/**
 * Class description here
 * Package: main.java.com.ben.brat.control
 */
public class Common {
    public static void addToGridPanel(JPanel panel, JComponent component, int x, int y, GridBagConstraints constraints) {
        if (y>=constraints.gridheight) {
            System.err.println("Row " + y + " is invalid.  Superpanel has height: " + constraints.gridheight);
            return;
        }
        if (x>=constraints.gridwidth) {
            System.err.println("Column " + x + " is invalid.  Superpanel has width: " + constraints.gridwidth);
            return;
        }
        constraints.gridx = x;
        constraints.gridy = y;
        panel.add(component, constraints);
    }
}
