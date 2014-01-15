package main.java.com.ben.brat.panels;

import main.java.com.ben.brat.control.FontChooserComboBox;
import main.java.com.ben.brat.control.Refresher;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;

/**
 * Class description here
 * Package: main.java.com.ben.brat.panels
 */
public class SuperPanel extends JPanel {
    private static void create() {

        try {

            JFrame f = new JFrame();
            JColorChooser chooser = new JColorChooser();
            Refresher refresher = new Refresher(chooser);
            chooser.getSelectionModel().addChangeListener(refresher);
            OptionsPanel optionsPanel = new OptionsPanel(refresher);

            for (AbstractColorChooserPanel panel : chooser.getChooserPanels()) {
                if (!panel.getDisplayName().equals("HSL")) {
                    chooser.removeChooserPanel(panel);
                }
            }
            chooser.setPreviewPanel(new JPanel());

            chooser.addChooserPanel(optionsPanel);

            JPanel superPanel = new JPanel(new GridBagLayout());
            GridBagConstraints guiConstraints = new GridBagConstraints();
            guiConstraints.gridheight = 2;
            guiConstraints.gridwidth = 2;

            guiConstraints.gridx = 0;
            guiConstraints.gridy = 0;
            superPanel.add(chooser, guiConstraints);

            guiConstraints.gridx = 1;
            guiConstraints.gridy = 0;
            superPanel.add(new FontChooserComboBox(), guiConstraints);

            guiConstraints.gridx = 0;
            guiConstraints.gridy = 1;
            TextOverlay overlayPanel = new TextOverlay().save("C:\\Users\\ben\\Desktop\\output.png");
            refresher.addListener(overlayPanel);
            superPanel.add(overlayPanel);

            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.add(superPanel);
            f.pack();
            f.setVisible(true);
        } catch (Exception E) {
            System.err.println("OH NO BRO!");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                create();
            }
        });
    }
}
