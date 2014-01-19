package main.java.com.ben.brat.control;

import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Class description here
 * Package: main.java.com.ben.brat.control
 */
public class RefreshData {
    private Font _font;
    private Color _color;
    private String _text;
    private List<String> _names;
    private List<BufferedImage> _images;

    public RefreshData () {
        _font = new Font("Serif", Font.BOLD, 20);
        _color = Color.white;
        _text = "Placeholder Text";
        _names = new ArrayList<String>();
        _images = new ArrayList<BufferedImage>();
    }

    public Font getFont() {
        return _font;
    }

    public void setFont(Font font) {
        _font = font;
    }

    public Color getColor() {
        return _color;
    }

    public void setColor(Color color) {
        _color = color;
    }

    public String getText() {
        return _text;
    }

    public void setText(String text) {
        _text = text;
    }

    public List<String> getNames() {
        return _names;
    }

    public void setNames(List<String> names) {
        _names = names;
    }

    public List<BufferedImage> getImages() {
        return _images;
    }

    public void setImages(List<BufferedImage> images) {
        _images = images;
    }
}
