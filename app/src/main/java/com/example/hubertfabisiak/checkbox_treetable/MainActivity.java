package com.example.hubertfabisiak.checkbox_treetable;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class MainActivity extends AppCompatActivity {

    //
    Table table;
    //


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         setContentView(R.layout.table_layout);
         new Settings();
         //new XMLReader();
         TreeTableModel ttm = new TreeTableModel();
         table = new Table(this,(TableLayout) findViewById(R.id.main_table));

         table.init(ttm.getRowData(),ttm.getColumnNames());

//
     } // end onCreate


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }






}

