package main.java.com.ben.brat.control;

import main.java.com.ben.brat.interfaces.Refreshable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;
import java.util.List;

public class Refresher implements ChangeListener, Refreshable {

    private RefreshData refreshData;
    private List<Refreshable> listeners;
    private JColorChooser chooser;

    public Refresher(JColorChooser observable) {
        refreshData = new RefreshData();
        listeners = new ArrayList<Refreshable>();
        chooser = observable;
    }

    public Refresher addListener(Refreshable listener) {
        listeners.add(listener);
        return this;
    }

    public void stateChanged(ChangeEvent unsafe) {
        for (Refreshable listener : listeners) {
            refreshData.setColor(chooser.getColor());
            System.out.println("This is the key");
            listener.refresh(refreshData);
        }
    }

    @Override
    public void refresh(RefreshData unsafe) {
        stateChanged(null);
    }

    public RefreshData getRefreshData() {
        return refreshData;
    }

    public void setRefreshData(RefreshData refreshData) {
        this.refreshData = refreshData;
    }
};