package com.example.hubertfabisiak.checkbox_treetable;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubertfabisiak.checkbox_treetable.model.Tree;
import com.example.hubertfabisiak.checkbox_treetable.model.TreeNode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Created by Damian on 15.01.2017.
 */

public class Table <T> {

    Activity mainActivity;
    TableLayout tl;
    ArrayList<TreeNode<T>> data;
    Tree<T> tree;
    ArrayList<EditText> editTexts;
    boolean fieldsFilled;

    //
    boolean relacatingNode;
    int relocatingId;

    public Table(Activity mainActivity, TableLayout tableLayout) {
        this.mainActivity = mainActivity;
        this.tl = tableLayout;
        relacatingNode = false;
        editTexts = new ArrayList<EditText>();
        fieldsFilled = false;
    }

    public void init(Tree<T> tree,ArrayList<TreeNode<T>> data) {
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
        tr_heads.add(0, title);

        final TristateCheckBox blank = new TristateCheckBox(mainActivity){
            @Override
            public void onChangedToChecked() {
            }

            @Override
            public void onChangedToUnchecked() {
            }

            @Override
            public void onChangedToUnknown() {
            }
        };
        title.addView(blank);
        blank.setFocusable(false);
        blank.setClickable(false);

        TextView titleV = null;
        for (int i = 0; i < Settings.getVariablesToDisplaySize(); i++) {
            titleV = new TextView(mainActivity);
            titleV.setText(Settings.getVariableToDisplay(i));
            titleV.setTextColor(Color.BLACK);
            if (i == 0) {
                TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.1f);
                titleV.setLayoutParams(params);
            }
            titleV.setPadding(0, 0, 100, 0);
            title.addView(titleV);
        }

        for (int i = 1; i < data.size(); i++) {
            final TreeNode<?> node = data.get(i);
            TableRow new_head = new TableRow(mainActivity);
            new_head.setId(data.get(i).getTreeNodeId());
            new_head.setBackgroundColor(Color.GRAY);
            new_head.setClickable(true);
            new_head.setFocusable(true);
            new_head.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    onClickMethod(v);
                }
            });

            new_head.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onLongClickMethod(v);
                    return false;
                }
            });
            new_head.setPadding((data.get(i).getTreeLevel() - 1) * 50, 5, 5, 5);
            new_head.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tr_heads.add(new_head);

            if (data.get(i).getSummaryVisible() && !data.get(i).isLeaf()) {
                tr_heads.add(createSummary(data.get(i)));
            }

            //
//            TextView tv = new TextView(mainActivity);
//            tv.setText("");
//            tv.setWidth((data.get(i).getTreeLevel()-1) * 100);
//            new_head.addView(tv);
            //
            TextView tv;
            if (data.get(i).getNumberOfChildren() != 0) {
                TristateCheckBox newtcb = new TristateCheckBox(mainActivity) {
                    @Override
                    public void onChangedToChecked() {
                        node.setCheckboxToChecked();
                        data = tree.getDataToDisplay();
                        createTableTree();
                    }

                    @Override
                    public void onChangedToUnchecked() {
                        node.setCheckboxToUnchecked();
                        data = tree.getDataToDisplay();
                        createTableTree();
                    }

                    @Override
                    public void onChangedToUnknown() {
                        node.setCheckboxToUnknown();
                        data = tree.getDataToDisplay();
                        createTableTree();
                    }
                };
                newtcb.setState(data.get(i).getCheckboxState());
                new_head.addView(newtcb);
            } else {
                tv = new TextView(mainActivity);
                tv.setText("");
                tv.setWidth(0);
                new_head.addView(tv);
            }

            for (int j = 0; j < data.get(i).getDataToDisplaySize(); j++) {
                tv = new TextView(mainActivity);
                tv.setText(data.get(i).getDataToDisplayIdx(j));
                tv.setTextColor(Color.WHITE);
                if (j == 0) {
                    TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.1f);
                    tv.setLayoutParams(params);
                }
                //test
                if (j == 1) {
                    tv.setText(new_head.getId()+"");
                }
                new_head.addView(tv);
            }
        }

        View v;
        tl.removeAllViews();
        tl.setWeightSum(1);
        tl.setShrinkAllColumns(true);
        for (TableRow tr : tr_heads) {
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
        int i = 0;
        while (data.get(i).getTreeNodeId() != v.getId()) {
            i++;
        }

        if(relacatingNode) {
            Log.d("qqqqq","ten " + relocatingId);
            Log.d("qqqqq","z tym " + v.getId()); // dobrze
            tree.changeParent(relocatingId,v.getId());
            relacatingNode = false;
            Toast.makeText(mainActivity, "Please select new parent for row " + relocatingId + ".", Toast.LENGTH_SHORT).show();
        } else {
            data.get(i).onClick();
        }
//        data = tree.getDataToDisplay();
//        createTableTree();
        updateTable();
    }

    public void updateTable() {
        data = tree.getDataToDisplay();
        createTableTree();
    }

    private TableRow createSummary(TreeNode<?> treeNode) {
        TableRow summary = new TableRow(mainActivity);
        summary.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        summary.setBackgroundColor(Color.WHITE);
        TextView textView = new TextView(mainActivity);
        textView.setText(treeNode.getSummary());
        textView.setTextColor(Color.BLACK);
        summary.addView(textView);
        return summary;
    }

    private void onLongClickMethod(final View v) {
        PopupMenu popupMenu = new PopupMenu(mainActivity, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        showEditDialog();
                        break;
                    case R.id.remove:
                        tree.remove(v.getId()); //pawel popraw
                        Toast.makeText(mainActivity, "You removed row " + v.getId() + ".  Refresh data.", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.summary_visible:
                        TreeNode<?> treeNode = tree.find(v.getId());
                        treeNode.setSummaryVisible(!treeNode.getSummaryVisible());
                        data = tree.getDataToDisplay();
                        createTableTree();
                        break;
                    case R.id.relocate:
                        relacatingNode = true;
                        relocatingId = v.getId();
                        Toast.makeText(mainActivity, "Please select new parent for row " + relocatingId + ".", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    public void showEditDialog(){

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity);
        LinearLayout promptView = (LinearLayout)layoutInflater.inflate(R.layout.add_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mainActivity);

        ScrollView addingScroll = (ScrollView) mainActivity.findViewById(R.id.scrollingAdd);
        mainActivity.getLayoutInflater().inflate(R.layout.add_dialog, addingScroll,true);
        alertDialogBuilder.setView(promptView);

        Field[] fields = Car.class.getDeclaredFields();

        EditText edit;
        for (int i = 0; i < fields.length; i++) {
            edit = new EditText(mainActivity);
            if(!fields[i].isSynthetic() && fields[i].getName() != "serialVersionUID") {
                edit.setHint(fields[i].getName().toString());
                promptView.addView(edit);
            }

        }
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(mainActivity, "Edit done. Refresh data.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(mainActivity, "Edit cancelled.", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }




}



