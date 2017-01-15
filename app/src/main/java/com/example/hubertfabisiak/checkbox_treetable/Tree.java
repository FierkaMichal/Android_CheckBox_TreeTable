package com.example.hubertfabisiak.checkbox_treetable;

import java.util.ArrayList;

/**
 * Created by Pawel on 15.01.2017.
 */

public class Tree<T> {

    TreeNode<T> root;

    public Tree(TreeNode<T> root){
        this.root = root;
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

    public TreeNode<T> getRoot(){
        return root;
    }

    public boolean isEmpty(){
        if(root == null)
            return true;
        return false;
    }
}
