package com.example.hubertfabisiak.checkbox_treetable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;;
import android.view.Menu;
import android.view.MenuInflater;;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    //
    Table table;
    //


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         setContentView(R.layout.table_layout);
         new Settings();
         new XMLReader(this);
         TreeTableModel ttm = new TreeTableModel();
         table = new Table(this,(TableLayout) findViewById(R.id.main_table));

         //table.init(ttm.getRowData(),ttm.getColumnNames());
         table.init(ttm.getData());

//
     } // end onCreate


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }






}

