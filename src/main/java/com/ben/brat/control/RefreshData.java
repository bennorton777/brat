package main.java.com.ben.brat.control;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Class description here
 * Package: main.java.com.ben.brat.control
 */
public class RefreshData {
    private Font _font;
    private Color _color;
    private ActionEvent _lastEvent;

    public RefreshData () {
        _font = new Font("Serif", Font.BOLD, 20);
        _color = Color.white;
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

    public ActionEvent getLastEvent() {
        return _lastEvent;
    }

    public void setLastEvent(ActionEvent lastEvent) {
        _lastEvent = lastEvent;
    }
}
