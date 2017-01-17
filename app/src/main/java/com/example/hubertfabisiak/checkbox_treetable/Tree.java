package com.example.hubertfabisiak.checkbox_treetable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pawel on 15.01.2017.
 */

public class Tree<T> {

    public static int Id = 0;
    TreeNode<T> root;

    public Tree(T data){
        this.root = new TreeNode<T>(data, null);
        loadVariablesToDisplay();
    }

    public void loadVariablesToDisplay() {
        List<Integer> list = new ArrayList<>();

        Field[] fields = Car.class.getDeclaredFields();
        Map<Integer, String> map = new HashMap<>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ToDisplay.class)) {
                ToDisplay annotation = field.getAnnotation(ToDisplay.class);
                field.setAccessible(true);
                String fieldName = field.getName();
                int fieldIndex = annotation.index();
                map.put(fieldIndex, fieldName);
            }
        }
        list.addAll(map.keySet());
        for (int i = 0; i < list.size(); i++) {
            Settings.addVariableToDisplay(map.get(list.get(i)));
        }
    }

    public ArrayList<TreeNode<T>> getDataToDisplay(){
        ArrayList<TreeNode<T>> treeArrayList = new ArrayList<>();

        if(root != null)
            treeArrayList.addAll(getDataToDisplay(root));

        return treeArrayList;
    }

    private ArrayList<TreeNode<T>> getDataToDisplay(TreeNode<T> currentNode){
        ArrayList<TreeNode<T>> treeArrayList = new ArrayList<>();

        if(currentNode.getVisible()){
            treeArrayList.add(currentNode);
            if(!currentNode.isLeaf()){
                int i = 0;
                while(i < currentNode.getNumberOfChildren()) {
                    treeArrayList.addAll(getDataToDisplay(currentNode.getChildren(i)));
                    i++;
                }
            }
        }

        return treeArrayList;
    }

    public boolean add(TreeNode<T> parent, T toAddData){
        boolean ifAdd = false;

        if(ifContain(parent)){
            parent.addChildren(toAddData);
            ifAdd = true;
        }

        return ifAdd;
    }

    public boolean add(TreeNode<T> parent, T[] toAddData){
        boolean ifAdd = false;

        if(ifContain(parent)){
            for(int i = 0; i< toAddData.length; i++)
                parent.addChildren(toAddData[i]);
            ifAdd = true;
        }

        return ifAdd;
    }

    public boolean remove(int treeNodeID){
        boolean ifRemove = false;
        TreeNode<T> t = find(treeNodeID);
        TreeNode<T> tParent = null;
        ArrayList<TreeNode<T>> children = new ArrayList<>();

        if(t != null){
            tParent = t.getParent();
            children = t.getChildren();
            tParent.removeChild(t);

            tParent.setChildren(children);
            ifRemove = true;
        }

        return ifRemove;
    }

    public boolean remove(T dataToRemove){
        boolean ifRemove = false;
        TreeNode<T> t = find(dataToRemove);
        TreeNode<T> tParent = null;
        ArrayList<TreeNode<T>> children = new ArrayList<>();

        if(t != null){
            tParent = t.getParent();
            children = t.getChildren();
            tParent.removeChild(t);

            tParent.setChildren(children);
            ifRemove = true;
        }

        return ifRemove;
    }

    public boolean remove(TreeNode<T> nodeToRemove){
        boolean ifRemove = false;
        TreeNode<T> tParent = null;
        ArrayList<TreeNode<T>> children = new ArrayList<>();

        if(nodeToRemove != null){
            tParent = nodeToRemove.getParent();
            children = nodeToRemove.getChildren();
            tParent.removeChild(nodeToRemove);

            tParent.setChildren(children);
            ifRemove = true;
        }

        return ifRemove;
    }

    public boolean removeWithChildren(int treeNodeID){
        boolean ifRemove  = false;
        TreeNode<T> t = find(treeNodeID);

        if(t != null){
            t.removeChildren();
            t.getParent().removeChild(t);
            ifRemove = true;
        }

        return ifRemove;
    }

    public boolean removeWithChildren(T dataToRemove){
        boolean ifRemove  = false;
        TreeNode<T> t = find(dataToRemove);

        if(t != null){
            t.removeChildren();
            t.getParent().removeChild(t);
            ifRemove = true;
        }

        return ifRemove;
    }

    public boolean removeWithChildren(TreeNode<T> nodeToRemove){
        boolean ifRemove = false;

        if(nodeToRemove != null){
            nodeToRemove.removeChildren();
            nodeToRemove.getParent().removeChild(nodeToRemove);
            ifRemove = true;
        }

        return ifRemove;
    }

    public boolean ifContain(T data){
        if(find(data) != null)
            return true;
        return false;
    }

    public boolean ifContain(TreeNode<T> node){
        if(find(node.getData()) != null)
            return true;
        return false;
    }

    public TreeNode<T> find(T data){
        TreeNode<T> returnNode = null;

        if(root != null){
            returnNode = find(root, data);
        }

        return returnNode;
    }

    public TreeNode<T> find(int treeNodeID){
        TreeNode<T> returnNode = null;

        if(root != null){
            returnNode = find(root, treeNodeID);
        }

        return returnNode;
    }

    private TreeNode<T> find(TreeNode<T> currentNode, T data){
        TreeNode<T> returnNode = null;

        if(currentNode.getData().equals(data))
            returnNode = currentNode;
        else if(!currentNode.isLeaf()){
            int i = 0;
            while(returnNode == null && i < currentNode.getNumberOfChildren()){
                returnNode = find(currentNode.getChildren(i), data);
                i++;
            }
        }

        return returnNode;
    }

    private TreeNode<T> find(TreeNode<T> currentNode, int treeNodeID){
        TreeNode<T> returnNode = null;

        if(currentNode.getTreeNodeId() == treeNodeID)
            returnNode = currentNode;
        else if(!currentNode.isLeaf()){
            int i = 0;
            while(returnNode == null && i < currentNode.getNumberOfChildren()){
                returnNode = find(currentNode.getChildren(i), treeNodeID);
                i++;
            }
        }

        return returnNode;
    }

    public TreeNode<T> getRoot(){
        return root;
    }

    public boolean isEmpty(){
        if(root == null)
            return true;
        return false;
    }
}
