package com.example.hubertfabisiak.checkbox_treetable;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class MainActivity extends Activity {

    //
    Table table;
    //


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        checkBox = new TristateCheckBox(this);
        checkBox.setWidth(100);
        checkBox.setHeight(100);
        relativeLayout.addView(checkBox);
        relativeLayout.setBackgroundColor(Color.BLACK);
        setContentView(relativeLayout);
    }
}


//Tabela:
//         setContentView(R.layout.table_layout);



//         new Settings();
//         //new XMLReader();
//         TreeTableModel ttm = new TreeTableModel();
//         table = new Table(this,(TableLayout) findViewById(R.id.main_table));

//         table.init(ttm.getRowData(),ttm.getColumnNames());

// //        hTextView = (TextView)findViewById(R.id.idTextView);
// //        hTableRow = (TableRow)findViewById(R.id.idTableRow1);
//     } // end onCreate

//koniec tabeli



//    public void myTableRowClickHandler(View view) {
//        switch (view.getId()) {
//            case R.id.idTableRow1:
//                hTextView = (TextView)findViewById(R.id.idTextView);
//                hTableRow = (TableRow)findViewById(R.id.idTableRow1);
//                if(bColorYellow){
//                    hTableRow.setBackgroundColor(Color.GREEN);
//                    bColorYellow = false;
//                }
//                else{
//                    hTableRow.setBackgroundColor(Color.YELLOW);
//                    bColorYellow = true;
//                }
//            break;
//
//            case R.id.idTableRow2:
//                hTextView = (TextView)findViewById(R.id.idTextView2);
//                hTableRow = (TableRow)findViewById(R.id.idTableRow2);
//                if(bColorYellow){
//                    hTableRow.setBackgroundColor(Color.GREEN);
//                    bColorYellow = false;
//                }
//                else{
//                    hTableRow.setBackgroundColor(Color.YELLOW);
//                    bColorYellow = true;
//                }
//                break;
//        }
//    }
}

