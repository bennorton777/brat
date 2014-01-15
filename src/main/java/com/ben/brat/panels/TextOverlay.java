package main.java.com.ben.brat.panels;

import main.java.com.ben.brat.control.RefreshData;
import main.java.com.ben.brat.control.Refresher;
import main.java.com.ben.brat.control.JFontChooser;
import main.java.com.ben.brat.control.FontChooserComboBox;
import main.java.com.ben.brat.interfaces.Refreshable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;

/**
 * This class is a JPanel wrapper for an image, with the capability to overlay text onto the image.
 * This class's methods follow the builder pattern in most cases.  Any exceptions are documented.
 */
//TODO JSON config files hydrated to classes, pls
public class TextOverlay extends AbstractColorChooserPanel implements Refreshable {

    private BufferedImage newImage;
    private BufferedImage cleanImage;
    private String text = "Such Dumb";

    //TODO Submit images through things other than urls.
    public TextOverlay() {
        try {
            cleanImage = newImage = ImageIO.read(new URL(
                    "http://d22r54gnmuhwmk.cloudfront.net/photos/3/yi/to/cTyItoocJlulYjJ-556x313-noPad.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setPreferredSize(new Dimension(
                newImage.getWidth(), newImage.getHeight()));
        newImage = overlay(newImage, text, Color.red, new Font("Serif", Font.BOLD, 20));
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

    public TextOverlay stripText () {
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
    public TextOverlay save(String name) throws IOException{
        File outfile = new File(name);
        ImageIO.write(newImage, "png", outfile);
        return this;
    }

    @Override
    public void refresh(RefreshData refreshData) {
        stripText();
        overlay(cleanImage, text, refreshData.getColor(), refreshData.getFont());
        this.repaint();
    }
}