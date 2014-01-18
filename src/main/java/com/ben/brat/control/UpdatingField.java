package main.java.com.ben.brat.control;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * Class description here
 * Package: main.java.com.ben.brat.control
 */
public class UpdatingField extends JTextField {

    static Refresher _refresher;

    public UpdatingField(Refresher refresher) {
        super(30);
        _refresher = refresher;
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateText(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateText(getText());
            }

            public void changedUpdate(DocumentEvent e) {
                System.err.println("What is a change update?");
            }
        });
    }
    public void updateText(String text) {
        _refresher.getRefreshData().setText(getText());
        _refresher.refresh();
    }

//    static class UpdatingDocument extends PlainDocument {
//
//        public void insertString(int offs, String str, AttributeSet a)
//                throws BadLocationException {
//
//            if (str == null) {
//                return;
//            }
//            System.err.println("Doing stuff");
//            _refresher.getRefreshData().setText("HI!");
//            _refresher.refresh();
//            super.insertString(offs, str, a);
//        }
//    }
}
