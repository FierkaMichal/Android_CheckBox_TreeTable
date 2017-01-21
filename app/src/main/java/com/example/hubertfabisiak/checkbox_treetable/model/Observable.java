package com.example.hubertfabisiak.checkbox_treetable.model;

import java.util.ArrayList;

/**
 * Created by Pawel on 20.01.2017.
 */

public class Observable {
    private final ArrayList<OnChangeListener> listenersList = new ArrayList<>();
    int state = -1;

    public void setState(int state){
        this.state = state;
    }

    public int getState(){
        return state;
    }

    public void addListener(OnChangeListener listener) {
        listenersList.add(listener);
    }
    public void removeListener(OnChangeListener listener) {
        listenersList.remove(listener);
    }

    protected void notifyObservers(final Object model) {
        synchronized (listenersList) {
            for (OnChangeListener listener : listenersList) {
                listener.onChange(model);
            }
        }
    }
}
