package com.example.hubertfabisiak.checkbox_treetable;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by hubertfabisiak on 17.01.2017.
 */

public class MyActionBar {

    Activity mainActivity;

    public MyActionBar(Activity mainActivity){
        this.mainActivity = mainActivity;
    }

    public boolean onOptionsSelection(MenuItem item){
        int YOUR_RESULT_CODE = 0;
        int FILE_SELECT_CODE = 0;
        switch (item.getItemId()){
            case R.id.export_xml:
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                builder.setMessage("Do you want to export data to XML file?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                exportToXmlFile();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.export_json:
                builder = new AlertDialog.Builder(mainActivity);
                builder.setMessage("Do you want to export data to JSON file?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                exportToJsonFile();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                alert = builder.create();
                alert.show();
                break;
            case R.id.import_xml:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.setType("*/*");      //all files
                intent.setType("text/xml");   //XML file only
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                try {
                    mainActivity.startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
                    Toast.makeText(mainActivity, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.import_json:
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/octet-stream");   //Closest to JSON
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                try {
                    mainActivity.startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
                    Toast.makeText(mainActivity, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                return true;
        }

        return true;


    }


    public void exportToJsonFile(){

    }

    public void exportToXmlFile(){

    }





}
