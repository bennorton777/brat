package main.java.com.ben.brat.panels;

import main.java.com.ben.brat.control.Common;
import main.java.com.ben.brat.control.JFontChooser;
import main.java.com.ben.brat.control.RefreshData;
import main.java.com.ben.brat.control.Refresher;
import main.java.com.ben.brat.interfaces.Refreshable;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class description here
 * Package: main.java.com.ben.brat.panels
 */
public class FontPanel extends AbstractColorChooserPanel implements Refreshable {

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints guiConstraints = new GridBagConstraints();
    private Refresher _refresher;

    public FontPanel(Refresher refresher) {
        _refresher = refresher;
        _refresher.addListener(this);

        guiConstraints.gridheight = 2;
        guiConstraints.gridwidth = 2;

        Font currentFont = _refresher.getRefreshData().getFont();
        JLabel fontDescription = new JLabel("Current font is: " + currentFont.getFontName() + " with font size: " + currentFont.getSize() + ".");

        JButton fontButton = new JButton("Change the Font");

        JTextField sampleText = new JTextField("Placeholder text");

        Common.addToGridPanel(this, fontDescription, 0, 0, guiConstraints);
        Common.addToGridPanel(this, fontButton, 1, 0, guiConstraints);
        Common.addToGridPanel(this, sampleText, 0, 1, guiConstraints);
        fontButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                JFontChooser fontChooser = new JFontChooser();
                int result = fontChooser.showDialog(new JFrame());
                if (result == JFontChooser.OK_OPTION) {
                    Font font = fontChooser.getSelectedFont();
                    updateFont(font);
                }
            }
        });
    }

    public void updateFont(Font font) {
        _refresher.getRefreshData().setFont(font);
        _refresher.refresh();
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

    @Override
    public void refresh(RefreshData refreshData) {

    }
}
