package main.java.com.ben.brat.panels;

import main.java.com.ben.brat.control.RefreshData;
import main.java.com.ben.brat.control.Refresher;
import main.java.com.ben.brat.interfaces.Refreshable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;

/**
 * This class is a JPanel wrapper for an image, with the capability to overlay text onto the image.
 * This class's methods follow the builder pattern in most cases.  Any exceptions are documented.
 */
public class PreviewPanel extends AbstractColorChooserPanel implements Refreshable {

    private BufferedImage newImage;
    private BufferedImage cleanImage;

    //TODO Submit images through things other than urls.
    public PreviewPanel(Refresher refresher) throws IOException {
        this(refresher,
                ImageIO.read(new URL(
                        "http://d22r54gnmuhwmk.cloudfront.net/photos/3/yi/to/cTyItoocJlulYjJ-556x313-noPad.jpg")),
                refresher.getRefreshData().getText());
    }

    public PreviewPanel (Refresher refresher, BufferedImage image, String text) {
        refresher.addListener(this);
        cleanImage = newImage = image;
        this.setPreferredSize(new Dimension(
                newImage.getWidth(), newImage.getHeight()));
        newImage = overlay(newImage,
                text,
                refresher.getRefreshData().getColor(),
                refresher.getRefreshData().getFont());
    }

    @Override
    public void updateChooser() {
        Color c = getColorSelectionModel().getSelectedColor();
    }

    @Override
    protected void buildChooser() {
        //Do nothing.
    }

    @Override
    public String getDisplayName() {
        return "Font and Other Options";
    }

    @Override
    public Icon getSmallDisplayIcon() {
        return null;
    }

    @Override
    public Icon getLargeDisplayIcon() {
        return null;
    }

    private BufferedImage overlay(BufferedImage old, String text, Color color, Font font) {
        int w = old.getWidth();
        int h = old.getHeight();
        BufferedImage img = new BufferedImage(
                w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(old, 0, 0, null);
        g2d.setPaint(color);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int x = (img.getWidth()/2) - (fm.stringWidth(text)/2) - 5;
        int y = img.getHeight();
        g2d.drawString(text, x, y);
        g2d.dispose();
        newImage = img;
        return img;
    }

    public PreviewPanel stripText () {
        newImage = cleanImage;
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(newImage, 0, 0, null);
    }

    /**
     * //TODO "Your application shouldn't warn users about behaving bizarrly.  It should just not behave bizarrly."
     * Saves the overlayed image.  DO NOT PASS existing file names into the this function if you don't want them to be overridden!
     * @param name This should be the fully qualified path of the saved file's name.  THIS WILL OVERWRITE FILES.
     * @throws IOException
     */
    public PreviewPanel save(String name) throws IOException{
        File outfile = new File(name);
        ImageIO.write(newImage, "png", outfile);
        return this;
    }

    @Override
    public void refresh(RefreshData refreshData) {
        stripText();
        overlay(cleanImage, refreshData.getText(), refreshData.getColor(), refreshData.getFont());
        this.repaint();
    }
}