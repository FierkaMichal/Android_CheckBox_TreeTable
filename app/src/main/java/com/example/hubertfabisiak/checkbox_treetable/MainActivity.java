package com.example.hubertfabisiak.checkbox_treetable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;;
import android.view.Menu;
import android.view.MenuInflater;;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    Table table;
    MyActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar = new MyActionBar(this);

        setContentView(R.layout.activity_main);
        ScrollView scrollView = (ScrollView) findViewById(R.id.scroll);
        getLayoutInflater().inflate(R.layout.table_layout, scrollView,true);

        new Settings();
        //new XMLReader(this);
        TreeTableModel ttm = new TreeTableModel();
        table = new Table(this, (TableLayout) findViewById(R.id.main_table));
        table.init(ttm.getTree(), ttm.getData());
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        actionBar.onOptionsSelection(item);
        return super.onOptionsItemSelected(item);
    }

}

