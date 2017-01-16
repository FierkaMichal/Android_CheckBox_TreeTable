package com.example.hubertfabisiak.checkbox_treetable;

/**
 * Created by Damian on 15.01.2017.
 */


import java.util.ArrayList;

public class TreeTableModel {
    private Object[][] rowData;
    private Object columnNames[];
    // private EventListenerList eventListenerList;

    ArrayList<Node> nodes;

    public TreeTableModel() {
        //  eventListenerList = new EventListenerList();
        addData();
    }

    public void addData() {
        Car c3 = new Car(5, "c3", 5.1);
        Car c4 = new Car(5, "c4", 5.1);
        Car c1 = new Car(5, "c1", 5.1);
        Car c2 = new Car(5, "c2", 5.1);

        Car c5 = new Car(5, "c5", 5.1);
        Car c6 = new Car(5, "c6", 5.1);
        Car c7 = new Car(5, "c7", 5.1);
        Tree<Car> tree = new Tree<Car>(c1);

        tree.add(tree.getRoot(), c3);
        tree.add(tree.getRoot(), c4);
        tree.add(tree.find(c3), c5);
        tree.add(tree.find(c3), c6);
        tree.add(tree.find(c4), c7);
        tree.add(tree.find(c5), c1);

        ArrayList<TreeNode<Car>> a = tree.getDataToDisplay();
        
//        int columns;
//        int rows;
//        nodes = new ArrayList<>();
//        nodes.add(new Node<Car>(new Car(5, "Audi", 42.4)));
//        nodes.add(new Node<Car>(new Car(9, "Fiat", 42224)));
//        nodes.add(new Node<Car>(new Car(2, "Skoda", 42123.4)));
//        nodes.add(new Node<Car>(new Car(6, "Mazda", 11164)));
//        nodes.add(new Node<Car>(new Car(1, "Toyota", 7777.4)));
//        nodes.add(new Node<Car>(new Car(3, "Suzuki", 421)));
//        nodes.add(new Node<Car>(new Car(0, "Seat", 866.4)));
//
//        //nodes.get(0).loadValuesToDisplayFromAnnotation();
//
//        nodes.get(0).checkIfNodeContainsValuesToDisplay();
//        Settings.clearVariablesToDisplay();
//
//        for (int i = 0; i < nodes.size(); i++) {
//            nodes.get(i).loadValuesToDisplay();
//        }
//
//        rows = nodes.size();
//        columns = Settings.getVariablesToDisplaySize();
//
//        Object[][] tmp = new Object[rows][columns + 1];
//
//        Node tmpNode;
//        for (int i = 0; i < nodes.size(); i++) {
//            tmpNode = nodes.get(i);
//
//            for (int j = 0; j < tmpNode.getDataToDisplaySize(); j++) {
//                //tmp[i][0] = new CheckBox(0);
//                tmp[i][j + 1] = tmpNode.getData(j);
//            }
//        }
//
//        rowData = tmp;
//
//        Object[] tmp2 = new Object[columns + 1];
//        tmp2[0] = "";
//        for (int i = 0; i < columns; i++) {
//            tmp2[i + 1] = Settings.getVariableToDisplay(i);
//        }
//
//        columnNames = tmp2;

        //fireChange();

    }
    // public void fillWithDataFromList(){
    // List<String> tokens = new ArrayList<String>();
    // rowData = new Object[10][3];
    //
    // for(int i=0;i<20;i++){
    // tokens.add("test");
    // }
    //
    // for(int i=0;i<20;i++){
    // rowData[i%10][i%3] = tokens.get(i);
    // }
    // }

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

    public Object[][] getRowData() {
        return rowData;
    }

    public Object[] getColumnNames() {
        return columnNames;
    }
}
