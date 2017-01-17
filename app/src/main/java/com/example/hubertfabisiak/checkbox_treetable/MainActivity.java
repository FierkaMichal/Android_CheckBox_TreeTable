package com.example.hubertfabisiak.checkbox_treetable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ScrollView;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    Table table;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll);
        getLayoutInflater().inflate(R.layout.table_layout, scrollView,true);

        new Settings();
        new XMLReader(this);
        TreeTableModel ttm = new TreeTableModel();
        table = new Table(this, (TableLayout) findViewById(R.id.main_table));
        table.init(ttm.getTree(), ttm.getData());
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }


    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Menu");
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "Remove");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Edit") {
            //function1(item.getItemId());
        } else if (item.getTitle() == "Remove") {
            //function2(item.getItemId());
        } else {
            return false;
        }
        return true;
    }



}

