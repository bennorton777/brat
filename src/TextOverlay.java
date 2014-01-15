import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is a JPanel wrapper for an image, with the capability to overlay text onto the image.
 * This class's methods follow the builder pattern in most cases.  Any exceptions are documented.
 */
//TODO JSON config files hydrated to classes, pls
public class TextOverlay extends JPanel {

    private BufferedImage image;

    //TODO Submit images through things other than urls.
    public TextOverlay() {
        try {
            image = ImageIO.read(new URL(
                    "http://d22r54gnmuhwmk.cloudfront.net/photos/3/yi/to/cTyItoocJlulYjJ-556x313-noPad.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setPreferredSize(new Dimension(
                image.getWidth(), image.getHeight()));
        image = overlay(image, "Such Dumb");
    }

    private BufferedImage overlay(BufferedImage old, String text) {
        int w = old.getWidth();
        int h = old.getHeight();
        BufferedImage img = new BufferedImage(
                w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(old, 0, 0, null);
        g2d.setPaint(Color.red);
        g2d.setFont(new Font("Serif", Font.BOLD, 20));
        FontMetrics fm = g2d.getFontMetrics();
        int x = img.getWidth() - fm.stringWidth(text) - 5;
        int y = fm.getHeight();
        g2d.drawString(text, x, y);
        g2d.dispose();
        return img;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    /**
     * //TODO "Your application shouldn't warn users about behaving bizarrly.  It should just not behave bizarrly."
     * Saves the overlayed image.  DO NOT PASS existing file names into the this function if you don't want them to be overridden!
     * @param name This should be the fully qualified path of the saved file's name.  THIS WILL OVERWRITE FILES.
     * @throws IOException
     */
    public TextOverlay save(String name) throws IOException{
        File outfile = new File(name);
        ImageIO.write(image, "png", outfile);
        return this;
    }

    private static void create() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            f.add(new TextOverlay().save("C:\\Users\\ben\\Desktop\\output.png"));
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