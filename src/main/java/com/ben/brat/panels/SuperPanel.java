package main.java.com.ben.brat.panels;

import main.java.com.ben.brat.control.Common;
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

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints guiConstraints = new GridBagConstraints();

    public SuperPanel() {
        try {

            guiConstraints.gridheight = 1;
            guiConstraints.gridwidth = 2;

            JTabbedPane tabbedPane = new JTabbedPane();
            Refresher refresher = new Refresher();

            PreviewPanel previewPanel = new PreviewPanel(refresher).save("C:\\Users\\ben\\Desktop\\output.png");
            ColorChooserPanel colorPanel = new ColorChooserPanel(refresher);
            FontPanel fontPanel = new FontPanel(refresher);

            refresher.addListener(previewPanel);

            tabbedPane.addTab("Color", colorPanel);
            tabbedPane.addTab("Font", fontPanel);

            Common.addToGridPanel(this, tabbedPane, 0, 0, guiConstraints);
            Common.addToGridPanel(this, previewPanel, 1, 0, guiConstraints);
        }
        catch (Exception E) {
            System.err.println("OH NO BRO!");
        }
    }
}