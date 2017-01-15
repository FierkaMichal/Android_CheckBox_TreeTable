package com.example.hubertfabisiak.checkbox_treetable;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private TristateCheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        checkBox = new TristateCheckBox(this);
        relativeLayout.addView(checkBox);
        relativeLayout.setBackgroundColor(Color.BLACK);
        setContentView(relativeLayout);

    }
}
