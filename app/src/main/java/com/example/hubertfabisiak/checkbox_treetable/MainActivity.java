package com.example.hubertfabisiak.checkbox_treetable;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TableLayout;

import java.util.jar.Manifest;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        actionBar.onOptionsSelection(item);
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_EXTERNAL_STORAGE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    System.out.println("DOSTALISMY POZWOLENIE!!!!");
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
//    }


}

