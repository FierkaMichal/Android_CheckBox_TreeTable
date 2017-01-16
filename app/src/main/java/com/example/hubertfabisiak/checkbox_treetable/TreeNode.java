package com.example.hubertfabisiak.checkbox_treetable;

import java.util.ArrayList;


/**
 * Created by Pawel on 15.01.2017.
 */

public class TreeNode<T> {

    private T data;
    private TreeNode<T> parent;
    private ArrayList<TreeNode<T>> children;
    private ArrayList<String> dataToDisplay;
    private boolean visible;
    private int treeLevel;
    private int treeNodeId;
    private int checkboxState;
    private int childVisible;


    public TreeNode(T data, TreeNode<T> parent) {
        this.data = data;
        this.parent = parent;
        children = new ArrayList<>();
        visible = true;
        checkboxState = TristateCheckBox.UNCHECKED;
        treeNodeId = Tree.Id++;
        if(parent != null)
            treeLevel = parent.getTreeLevel() + 1;
        else
            treeLevel = 0;
    }

    public TreeNode(T data, TreeNode<T> parent, T child){
        this(data, parent);
        addChildren(child);
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
    }

    public T getData(){
        return this.data;
    }

    public void setData(T data){
        this.data = data;
    }

    public void childChangeVisible(boolean visible){
    }

    public boolean getVisible(){
        return visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public void setTreeLevel(int treeLevel){
        this.treeLevel = treeLevel;
    }

    public int getChildVisible(){
        return childVisible;
    }

    public int getTreeLevel(){
        return treeLevel;
    }

    public int getTreeNodeId(){
        return treeNodeId;
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
