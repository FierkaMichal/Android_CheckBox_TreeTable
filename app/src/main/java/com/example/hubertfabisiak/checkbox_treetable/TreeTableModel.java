package com.example.hubertfabisiak.checkbox_treetable;

/**
 * Created by Damian on 15.01.2017.
 */


import com.example.hubertfabisiak.checkbox_treetable.model.Tree;
import com.example.hubertfabisiak.checkbox_treetable.model.TreeNode;

import java.util.ArrayList;

public class TreeTableModel {
    // private EventListenerList eventListenerList;

    ArrayList<TreeNode<Car>> data;
    Tree<Car> tree;

    public TreeTableModel() {
        //  eventListenerList = new EventListenerList();
        addData();
    }

    public void addData() {
        Car c3 = new Car(5, "c3","a", 5.1);
        Car c4 = new Car(3, "c4","Seat", 5.1);
        Car c1 = new Car(8, "c1","root" ,5.1);
        Car c2 = new Car(9, "c2","Seat", 5.1);

        Car c5 = new Car(4, "c5", "test",5.1);
        Car c6 = new Car(9, "c6","dluuuuuuuuugi", 5.1);
        Car c7 = new Car(3, "c7","dom_domowo", 5.1);
        Car c8 = new Car(3, "c8","dom", 5.1);
        tree = new Tree<Car>(c1);

        tree.add(tree.getRoot(), c3);
        tree.add(tree.getRoot(), c4);
        tree.add(tree.find(c3), c5);
        tree.add(tree.find(c3), c6);
        tree.add(tree.find(c4), c7);
        tree.add(tree.find(c5), c2);
        tree.add(tree.find(c2), c8);
        //tree.add(tree.find(c5), c1);
//        tree.add(tree.getRoot(),new Car(4, "c5", 5.1));
//        tree.add(tree.getRoot(),new Car(4, "c5", 5.1));
//        tree.add(tree.getRoot(),new Car(4, "c5", 5.1));
//        tree.add(tree.getRoot(),new Car(4, "c5", 5.1));
//        tree.add(tree.getRoot(),new Car(4, "c5", 5.1));
//        tree.add(tree.getRoot(),new Car(4, "c5", 5.1));
//        tree.add(tree.getRoot(),new Car(4, "c5", 5.1));
//        tree.add(tree.getRoot(),new Car(4, "c5", 5.1));
//        tree.add(tree.getRoot(),new Car(4, "c5", 5.1));
//        tree.add(tree.getRoot(),new Car(4, "c5", 5.1));
//        tree.add(tree.getRoot(),new Car(4, "c5", 5.1));



        data = tree.getDataToDisplay();
    }


//    public void addChangeListener(ChangeListener listener) {
//        eventListenerList.add(ChangeListener.class, listener);
//    }
//
//    public void removeChangeListener(ChangeListener listener) {
//        if (eventListenerList != null)
//            eventListenerList.remove(ChangeListener.class, listener);
//    }
//
//    public void fireChange() {
//        ChangeListener[] listenerList = eventListenerList.getListeners(ChangeListener.class);
//
//        for (int i = listenerList.length - 1; i >= 0; --i) {
//            listenerList[i].stateChanged(new ChangeEvent(this));
//        }
//    }

    public ArrayList<TreeNode<Car>> getData() {
        return data;
    }

    public Tree<Car> getTree() {
        return tree;
    }
}
