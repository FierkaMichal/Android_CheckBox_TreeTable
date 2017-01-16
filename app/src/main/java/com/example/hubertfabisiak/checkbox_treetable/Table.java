package com.example.hubertfabisiak.checkbox_treetable;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Damian on 15.01.2017.
 */

public class Table  {

    Activity mainActivity;
    TableLayout tl;

    public Table(Activity mainActivity, TableLayout tableLayout) {
        this.mainActivity = mainActivity;
        this.tl = tableLayout;
    }

    public void init(Object[][] rowData,Object [] columnNames) {
        //LinkedList<LinkedList<TextView>> textList = new LinkedList<>();
        LinkedList<TableRow> tr_heads = new LinkedList<>();
        LinkedList<TristateCheckBox> tscb = new LinkedList<>();

        //Tytuł tabeli
        TableRow title = new TableRow(mainActivity);
        title.setId(0+1);
        title.setBackgroundColor(Color.WHITE);
        title.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        ;

        TristateCheckBox blank = new TristateCheckBox(mainActivity);
        title.addView(blank);
        blank.setFocusable(false);
        blank.setClickable(false);

        for(int i=1;i<columnNames.length;i++) {
            TextView titleV = new TextView(mainActivity);
            titleV.setId(0 + 2);
            titleV.setText(((String) columnNames[i]).toUpperCase());
            titleV.setTextColor(Color.BLACK);
            titleV.setPadding(5, 5, 5, 5);
            title.addView(titleV);
        }

        for (int i = 0; i < rowData.length; i++) {

            //Create the tablerows
            TableRow new_head = new TableRow(mainActivity);
            new_head.setId(i + 1);
            new_head.setBackgroundColor(Color.GRAY);
            new_head.setClickable(true);
            new_head.setFocusable(true);
            new_head.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    onClickMethod(v);
                } });

            new_head.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tr_heads.add(i,new_head);

            TristateCheckBox newtcb = new TristateCheckBox(mainActivity);
            tscb.add(i,newtcb);
            new_head.addView(newtcb);

            TextView tv = null;
            for(int j=1;j<columnNames.length;j++) {
                tv = new TextView(mainActivity);
                tv.setId(i + 111 + j);
                tv.setText((String) rowData[i][j]);
                tv.setTextColor(Color.WHITE);
                tv.setPadding(5, 5, 5, 5);
               // textList.add(i+j, tv);
                new_head.addView(tv);
            }


        }

        tl.addView(title, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        View v;
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

    } // end of for loop

    private void onClickMethod(View v) {

    }

    public void updateTable() {

    }

}


