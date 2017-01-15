package com.example.hubertfabisiak.checkbox_treetable;

import android.app.Activity;
import android.graphics.Color;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
        TextView[] textArray = new TextView[rowData.length];
        TableRow[] tr_head = new TableRow[rowData.length];

        for (int i = 0; i < rowData.length; i++) {
//            JSONObject product = productsList.getJSONObject(i);
//            JSONObject productData = product.getJSONObject("Product");
//            String productDescription = productData.getString("description");

            //Create the tablerows
            tr_head[i] = new TableRow(mainActivity);
            tr_head[i].setId(i + 1);
            tr_head[i].setBackgroundColor(Color.GRAY);
            tr_head[i].setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            // Here create the TextView dynamically

            textArray[i] = new TextView(mainActivity);
            textArray[i].setId(i + 111);
            textArray[i].setText((String)rowData[i][1]);
            textArray[i].setTextColor(Color.WHITE);
            textArray[i].setPadding(5, 5, 5, 5);
            tr_head[i].addView(textArray[i]);
        }

        for (int i = 0; i < rowData.length; i++) {
            tl.addView(tr_head[i], new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }

    } // end of for loop

}
