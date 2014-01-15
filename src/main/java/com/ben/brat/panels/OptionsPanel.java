package main.java.com.ben.brat.panels;

import main.java.com.ben.brat.control.JFontChooser;
import main.java.com.ben.brat.control.RefreshData;
import main.java.com.ben.brat.control.Refresher;
import main.java.com.ben.brat.interfaces.Refreshable;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Class description here
 * Package: main.java.com.ben.brat.panels
 */
public class OptionsPanel extends AbstractColorChooserPanel{

    Refresher _refresher;

    public OptionsPanel(Refresher refresher) {
        _refresher = refresher;
        JButton fontButton = new JButton("Font");
        this.add(fontButton);
        fontButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                JFontChooser fontChooser = new JFontChooser();
                int result = fontChooser.showDialog(new JFrame());
                if (result == JFontChooser.OK_OPTION) {
                    Font font = fontChooser.getSelectedFont();
                    updateFont(font);
                }
                _refresher.getRefreshData().setLastEvent(e);
                _refresher.refresh(null);
                System.out.println("You clicked the button: " + e.getActionCommand());
            }
        });
    }

    public void updateFont(Font font) {
        _refresher.getRefreshData().setFont(font);
    }

    @Override
    public void updateChooser() {
        //TODO Do nothing
    }

    @Override
    protected void buildChooser() {
        //Do nothing.
    }

    @Override
    public String getDisplayName() {
        return "Options";
    }

    @Override
    public Icon getSmallDisplayIcon() {
        return null;
    }

    @Override
    public Icon getLargeDisplayIcon() {
        return null;
    }
}
