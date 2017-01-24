package com.example.hubertfabisiak.checkbox_treetable.model;

import com.example.hubertfabisiak.checkbox_treetable.Car;
import com.example.hubertfabisiak.checkbox_treetable.Settings;
import com.example.hubertfabisiak.checkbox_treetable.ToDisplay;
import com.example.hubertfabisiak.checkbox_treetable.TristateCheckBox;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Pawel on 15.01.2017.
 */

public class TreeNode<T> extends Observable implements OnChangeListener {

    private T data;
    private TreeNode<T> parent;
    private ArrayList<TreeNode<T>> children;
    private ArrayList<String> dataToDisplay;
    private String summary;
    private boolean summaryVisible;
    private boolean visible;
    private boolean childVisible;
    private int treeLevel;
    private int treeNodeId;
    private int checkboxState;

    public TreeNode(T data, TreeNode<T> parent) {
        this.data = data;
        this.parent = parent;
        children = new ArrayList<>();
        summary = "dsad";
        summaryVisible = false;
        visible = true;
        childVisible = true;
        checkboxState = TristateCheckBox.CHECKED;
        treeNodeId = Tree.Id++;
        dataToDisplay = new ArrayList<>();
        loadValuesToDisplay();
        loadValuesToDisplayFromAnnotation();
        if(parent != null)
            treeLevel = parent.getTreeLevel() + 1;
        else
            treeLevel = 0;

        if(parent != null)
            this.parent.addListener(this);
    }

    public TreeNode(T data, TreeNode<T> parent, T child){
        this(data, parent);
        addChildren(child);
    }

    @Override
    public void onChange(Object parent) {
        TreeNode parenNode = (TreeNode) parent;

        switch (parenNode.getState()){
            case 0:         //parent zmienia treeLevel
                this.setTreeLevel(parenNode.getTreeLevel()+1);
                break;
            case 1:         //checkbox to unknown
                this.setVisible(true);
                this.setCheckboxToUnchecked();
                break;
            case 2:         //checkbox to unchecked
                this.setVisible(false);
                this.setChildrenCheckboxToUnchecked();
                break;
            case 3:         //checkbox to checked
                this.setVisible(true);
                this.setChildrenCheckboxToChecked();
                break;
            default:
                break;
        }

    }

    public void addChildren(T data){
        TreeNode<T> t = new TreeNode<>(data, this);
        children.add(t);
    }

    public void addChildren(int index, T data){
        TreeNode<T> t = new TreeNode<>(data, this);
        if(index < children.size())
            children.add(index, t);
        else
            children.add(t);
    }

    public void addChildren(ArrayList<TreeNode<T>> children){
        for(TreeNode<T> t: children)
            t.parent = this;
        this.children.addAll(children);
    }

    public void setChildren(ArrayList<TreeNode<T>> children){
        for(TreeNode<T> t: children)
            t.parent = this;
        this.children = children;
    }

    public TreeNode<T> getChildren(int index){
        TreeNode<T> t = null;
        if(index < children.size())
            t = children.get(index);
        return t;
    }

    public void removeChildren(){
        this.children = new ArrayList<TreeNode<T>>();
    }

    public void removeChild(int index){
        if(index < children.size()) {
            this.children.remove(index);
        }
    }

    public void removeChild(TreeNode<T> nodeToRemove){
        int i = 0;
        TreeNode<T> t = null;
        while(t == null && i < children.size()){
            if(children.get(i).equals(nodeToRemove))
                t = nodeToRemove;
            i++;
        }
        if(t!=null) {
            children.remove(t);
        }
    }

    public void onClick(){
        if(childVisible)
            setCheckboxToUnchecked();
        else
            setCheckboxToUnknown();
    }

    public void setCheckboxToUnknown(){
        boolean czyDzieciSaRozwiniete = true;
        setCheckboxState(TristateCheckBox.UNKNOWN);
        setChildVisible(true);
        for(int i = 0; i < children.size(); i++){
            TreeNode<T> child = children.get(i);
            if (child.getCheckboxState() != TristateCheckBox.CHECKED) {
                if(!child.isLeaf())
                    czyDzieciSaRozwiniete = false;
            }
        }
        if(czyDzieciSaRozwiniete)
            setCheckboxToChecked();
        else {
            this.setState(1);
            this.notifyObservers(this);
        }
    }

    public void setCheckboxToUnchecked(){
        setChildVisible(false);
        setCheckboxState(TristateCheckBox.UNCHECKED);

        if(parent != null && parent.getCheckboxState() != TristateCheckBox.UNKNOWN)
            parent.setParentCheckboxToUnknown();

        this.setState(2);
        this.notifyObservers(this);
    }

    private void setChildrenCheckboxToUnchecked(){
        setChildVisible(false);
        setCheckboxState(TristateCheckBox.UNCHECKED);
        this.setState(2);
        this.notifyObservers(this);
    }

    private void setParentCheckboxToUnknown(){
        setCheckboxState(TristateCheckBox.UNKNOWN);
        if(parent != null && parent.getCheckboxState() != TristateCheckBox.UNKNOWN)
            parent.setParentCheckboxToUnknown();

    }

    public void setCheckboxToChecked(){
        setChildVisible(true);
        setCheckboxState(TristateCheckBox.CHECKED);

        if(parent != null && parent.getCheckboxState() != TristateCheckBox.CHECKED){
            parent.setParentCheckboxToChecked();
        }

        this.setState(3);
        this.notifyObservers(this);
    }

    private void setChildrenCheckboxToChecked(){
        setChildVisible(true);
        setCheckboxState(TristateCheckBox.CHECKED);
        this.setState(3);
        this.notifyObservers(this);
    }

    private void setParentCheckboxToChecked(){
        ArrayList<TreeNode<T>> childrenList = getChildren();
        int i = 0;
        while(i < childrenList.size() &&
                (childrenList.get(i).isLeaf() || childrenList.get(i).getCheckboxState() == TristateCheckBox.CHECKED))
            i++;
        if(i == childrenList.size())
            setCheckboxState(TristateCheckBox.CHECKED);

        if(parent != null && parent.getCheckboxState() != TristateCheckBox.CHECKED){
            parent.setParentCheckboxToChecked();
        }
    }

    public void checkIfNodeContainsValuesToDisplay() {

        for (String fieldName : Settings.variablesToDisplay) {

            Class<?> someClass = data.getClass();
            Field someField = null;

            while (someClass != null && someField == null) {

                try {
                    someField = someClass.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                someClass = someClass.getSuperclass();
            }

            if (someField == null) {
                Settings.addInvalidVariablesToDisplay(fieldName);
            }
        }
    }

    public void loadValuesToDisplay() {

        for (String fieldName : Settings.variablesToDisplay) {

            Object value = null;
            Class<?> someClass = data.getClass();
            Field someField = null;

            while (someClass != null && someField == null) {

                try {
                    someField = someClass.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                someClass = someClass.getSuperclass();
            }

            if (someField != null) {

                someField.setAccessible(true);

                try {
                    value = someField.get((Object) data);
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                dataToDisplay.add(value.toString());
            }
        }

    }

    public void loadValuesToDisplayFromAnnotation() {
        List<Integer> list = new ArrayList<>();
        Field[] fields = Car.class.getDeclaredFields();
        Map<Integer, String> map = new HashMap<>();

        for(Field field : fields) {
            if(field.isAnnotationPresent(ToDisplay.class)) {
                ToDisplay annotation = field.getAnnotation(ToDisplay.class);
                field.setAccessible(true);
                String fieldName = field.getName();
                int fieldIndex = annotation.index();
                try {
                    map.put(fieldIndex, field.get((Object) data).toString());
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        list.addAll(map.keySet());
        for(int i = 0 ; i < list.size() ; i++) {
            dataToDisplay.add(map.get(list.get(i)));
        }
    }

    public int getDataToDisplaySize() {
        return dataToDisplay.size();
    }

    public String getDataToDisplayIdx(int idx) {
        return dataToDisplay.get(idx);
    }

    public String getData(int idx) {
        if(idx < dataToDisplay.size())
            return dataToDisplay.get(idx);
        return "";
    }

    public boolean isLeaf(){
        if(children.size() == 0)
            return true;
        return false;
    }

    public ArrayList getChildren(){
        return children;
    }

    public int getNumberOfChildren(){
        return children.size();
    }

    public TreeNode getParent(){
        return parent;
    }

    public void setParent(TreeNode<T> parent){
        this.parent = parent;
        this.setTreeLevel(parent.getTreeLevel()+1);
    }

    public void addChild(TreeNode<T> child) {
        children.add(child);
    }

    public T getData(){
        return this.data;
    }

    public void setData(T data){
        this.data = data;
    }

    public boolean getVisible(){
        return visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public void setTreeLevel(int treeLevel){
        this.treeLevel = treeLevel;
        this.setState(0);
        this.notifyObservers(this);
    }

    public void setChildVisible(boolean childVisible){
        this.childVisible = childVisible;
    }

    public boolean getChildVisible(){
        return childVisible;
    }

    public int getTreeLevel(){
        return treeLevel;
    }

    public int getTreeNodeId(){
        return treeNodeId;
    }

    public int getCheckboxState() {
        return checkboxState;
    }

    public void setCheckboxState(int i){
        if(i < 0 || i > 2)
            checkboxState = TristateCheckBox.UNCHECKED;
        else
            checkboxState = i;
    }

    public void setSummaryVisible(boolean summaryVisible){
        this.summaryVisible = summaryVisible;
    }

    public boolean getSummaryVisible(){
        return summaryVisible;
    }

    public void setSummary(String summary){
        this.summary = summary;
    }

    public String getSummary(){
        return summary;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != this.getClass())
            return false;
        TreeNode<T> treeNode = (TreeNode<T>) obj;
        if(!data.equals(this.getData()))
            return false;
        return true;
    }
}
