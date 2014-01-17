package main.java.com.ben.brat.control;

import main.java.com.ben.brat.interfaces.Refreshable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Refresher {
    private RefreshData refreshData = new RefreshData();
    private List<Refreshable> listeners = new ArrayList<Refreshable>();

    public Refresher() {
    }

    public Refresher addListener(Refreshable listener) {
        listeners.add(listener);
        return this;
    }

    public void refresh() {
        for (Refreshable listener : listeners) {
            System.err.println("Trying to refresh: " + listener);
            listener.refresh(refreshData);
        }
    }

    public RefreshData getRefreshData() {
        return refreshData;
    }

    public void setRefreshData(RefreshData refreshData) {
        this.refreshData = refreshData;
    }
};