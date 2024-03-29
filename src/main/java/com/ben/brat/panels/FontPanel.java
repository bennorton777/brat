package main.java.com.ben.brat.panels;

import main.java.com.ben.brat.control.*;
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

    private GridLayout layout = new GridLayout(2,2);
    private GridBagConstraints guiConstraints = new GridBagConstraints();
    private Refresher _refresher;
    JLabel fontDescription;

    public FontPanel(Refresher refresher) {
        _refresher = refresher;
        _refresher.addListener(this);

        guiConstraints.gridheight = 2;
        guiConstraints.gridwidth = 2;

        JLabel textInst = new JLabel("Change the sample text below");

        Font currentFont = _refresher.getRefreshData().getFont();
        fontDescription = new JLabel("Font is: " + currentFont.getFontName() + " at size " + currentFont.getSize());

        JButton fontButton = new JButton("Change the Font");

        UpdatingField sampleText = new UpdatingField(refresher);

        add(fontDescription);
        add(textInst);
        add(fontButton);
        add(sampleText);
        
//        Common.addToGridPanel(this, fontDescription, 0, 0, guiConstraints);
//        Common.addToGridPanel(this, fontButton, 1, 0, guiConstraints);
//        Common.addToGridPanel(this, sampleText, 0, 1, guiConstraints);
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
        fontDescription.setText("Current font is: " + refreshData.getFont().getFontName() + " with font size: " + refreshData.getFont().getSize() + ".");
    }
}
