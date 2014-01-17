package main.java.com.ben.brat.panels;

import main.java.com.ben.brat.control.Refresher;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Class description here
 * Package: main.java.com.ben.brat.panels
 */
public class ColorChooserPanel extends JColorChooser implements ChangeListener {

    Refresher _refresher;

    public ColorChooserPanel(Refresher refresher) {

        _refresher = refresher;

        setPreviewPanel(new JPanel());

        for (AbstractColorChooserPanel panel : getChooserPanels()) {
            if (!panel.getDisplayName().equals("HSL")) {
                System.err.println("Filtering out other panels");
                removeChooserPanel(panel);
            }
        }

        getSelectionModel().addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        System.err.println("Current color is: " + _refresher.getRefreshData().getColor() + " and will now become " + getColor());
        _refresher.getRefreshData().setColor(getColor());
        _refresher.refresh();
        System.err.println("Refreshed");
    }
}
