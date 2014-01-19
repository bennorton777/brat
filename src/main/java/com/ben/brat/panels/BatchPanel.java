package main.java.com.ben.brat.panels;

import main.java.com.ben.brat.control.Refresher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        _refresher = refresher;
        JButton browseForTextFile = new JButton("Browse Computer for list of names");
        JButton browseForImageFolder = new JButton("Browse Computer for folder of pictures");
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

        browseForImageFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(that);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.err.println("We are in directory: " + fc.getSelectedFile());
                    for (final File fileEntry : fc.getSelectedFile().listFiles()) {
                        String fileName = fileEntry.getName();
                        if (fileEntry.getName().substring(fileName.length() - 4, fileName.length() - 1).equals(".jpg")) {
                            try {
                                int index = Integer.parseInt(fileEntry.getName().substring(0, fileName.length() - 4));
                                _refresher.getRefreshData().getImages().add(index, ImageIO.read(fileEntry));
                            }
                            catch(NumberFormatException ex) {
                                System.err.println("Image: " + fileEntry.getName() + " is not conformable to an integer");
                            }
                            catch(IOException ex1) {
                                //Do nothing yet
                            }
                        }
                    }
                    for (int i = 0; i < _refresher.getRefreshData().getNames().size(); i++) {

                    }
                }
                else {
                    System.err.println("Something went really poorly.");
                }
            }
        });
        add(inputSelect);
        add(browseForTextFile);
        add(browseForImageFolder);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (String s : _refresher.getRefreshData().getNames()) {
            System.out.println(s);
        }
    }


}
