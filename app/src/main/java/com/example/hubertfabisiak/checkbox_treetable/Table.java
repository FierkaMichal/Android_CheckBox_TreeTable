package com.example.hubertfabisiak.checkbox_treetable;

import android.app.Activity;
import android.graphics.Color;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Created by Damian on 15.01.2017.
 */

public class Table {

    Activity mainActivity;
    TableLayout tl;
    ArrayList<TreeNode<Car>> data;
    Tree<Car> tree;

    public Table(Activity mainActivity, TableLayout tableLayout) {
        this.mainActivity = mainActivity;
        this.tl = tableLayout;
    }

    public void init( Tree<Car> tree,ArrayList<TreeNode<Car>> data) {
        this.tree = tree;
        this.data = data;
        createTableTree();
    }

    public void createTableTree() {
        LinkedList<TableRow> tr_heads = new LinkedList<>();

        TableRow title = new TableRow(mainActivity);
        title.setBackgroundColor(Color.WHITE);
        title.setId(data.get(0).getTreeNodeId());
        title.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tr_heads.add(0,title);

        TristateCheckBox blank = new TristateCheckBox(mainActivity);
        title.addView(blank);
        blank.setFocusable(false);
        blank.setClickable(false);

        TextView titleV = null;
        for(int i=0;i<Settings.getVariablesToDisplaySize();i++) {
            titleV = new TextView(mainActivity);
            titleV.setText(Settings.getVariableToDisplay(i));
            titleV.setTextColor(Color.BLACK);
            titleV.setPadding(5, 5, 5, 5);
            title.addView(titleV);
        }

        for (int i = 1; i < data.size(); i++) {
            TableRow new_head = new TableRow(mainActivity);
            new_head.setId(data.get(i).getTreeNodeId());
            new_head.setBackgroundColor(Color.GRAY);
            new_head.setClickable(true);
            new_head.setFocusable(true);
            new_head.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    onClickMethod(v);
                } });

            new_head.setOnLongClickListener(new View.OnLongClickListener(){
                                @Override
                                public boolean onLongClick(View v) {
                                        onLongClickMethod(v);
                                        return false;
                                    }
                            });

            new_head.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tr_heads.add(i,new_head);

            TristateCheckBox newtcb = new TristateCheckBox(mainActivity);
            newtcb.setState(data.get(i).getCheckboxState());
            new_head.addView(newtcb);

            TextView tv = null;
            for(int j=0;j<data.get(i).getDataToDisplaySize();j++) {
                tv = new TextView(mainActivity);
                tv.setText(data.get(i).getDataToDisplayIdx(j));
                tv.setTextColor(Color.WHITE);
                if(j==0) {
                    tv.setPadding((data.get(i).getTreeLevel()-1) * 50, 5, 5, 5);
                } else {
                    tv.setPadding(5, 5, 5, 5);
                }

                new_head.addView(tv);
            }
        }

        View v;
        tl.removeAllViews();
        for(TableRow tr : tr_heads) {
            v = new View(mainActivity);
            v.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 15));
            v.setBackgroundColor(Color.BLACK);
            tl.addView(v);

            tl.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }

        v = new View(mainActivity);
        v.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 15));
        v.setBackgroundColor(Color.BLACK);
        tl.addView(v);
    }

    private void onClickMethod(View v) {
        int i=0;
        while(data.get(i).getTreeNodeId()!=v.getId()) {
            i++;
        }
        data.get(i).onClick();
        data = tree.getDataToDisplay();
        createTableTree();
    }

    public void updateTable() {

    }

    private void onLongClickMethod(View v){

        PopupMenu popupMenu = new PopupMenu(mainActivity,v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.edit:
                        Toast.makeText(mainActivity, "You Clicked : " + "Edit", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.remove:
                        Toast.makeText(mainActivity, "You Clicked : " + "Remove", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }
}



