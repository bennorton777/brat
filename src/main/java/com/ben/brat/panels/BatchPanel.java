package main.java.com.ben.brat.panels;

import main.java.com.ben.brat.control.Refresher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class description here
 * Package: main.java.com.ben.brat.panels
 */
public class BatchPanel extends JPanel implements ActionListener {
    String[] inputOptions = {"List of names (textfile)"};
    JComboBox inputSelect = new JComboBox(inputOptions);
    JFileChooser fc = new JFileChooser();
    BatchPanel that = this;
    Refresher _refresher;

    public BatchPanel (Refresher refresher){
        _refresher = refresher;
        JButton browseForTextFile = new JButton("Browse Computer for list of names");
        inputSelect.setSelectedIndex(0);
        inputSelect.addActionListener(this);
        browseForTextFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(that);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        _refresher.getRefreshData().setNames(new ArrayList<String>());
                        BufferedReader br = null;
                        br = new BufferedReader(new FileReader(fc.getSelectedFile()));
                        String line;
                        while ((line = br.readLine()) != null) {
                            _refresher.getRefreshData().getNames().add(line);
                        }
                        br.close();
                    } catch (FileNotFoundException e1) {
                        System.err.println("Dude, I can't find the file you just chose.  I don't know what to do with that.");
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        System.err.println("Problem reading the file.");
                        e1.printStackTrace();
                    }
                }
                else {
                    System.err.println("Something went really poorly.");
                }
            }
        });
        add(inputSelect);
        add(browseForTextFile);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (String s : _refresher.getRefreshData().getNames()) {
            System.out.println(s);
        }
    }


}
