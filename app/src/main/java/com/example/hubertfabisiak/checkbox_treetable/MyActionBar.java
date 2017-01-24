package com.example.hubertfabisiak.checkbox_treetable;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.hubertfabisiak.checkbox_treetable.model.Tree;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by hubertfabisiak on 17.01.2017.
 */

public class MyActionBar {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSION_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    Activity mainActivity;
    Table table;
    ArrayList<EditText> editTexts;
    boolean fieldsFilled;

    public MyActionBar(Activity mainActivity,Table table){
        this.mainActivity = mainActivity;
        this.table = table;
        editTexts = new ArrayList<EditText>();
        fieldsFilled = false;
    }

    public boolean onOptionsSelection(MenuItem item){
        int YOUR_RESULT_CODE = 0;
        int FILE_SELECT_CODE = 0;
        switch (item.getItemId()){
            case R.id.add_action:
                showAddDialog();
                break;
            case R.id.refresh_action:
                Toast.makeText(mainActivity, "Data refreshed.", Toast.LENGTH_SHORT).show();
                table.updateTable();
                break;
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
                                //exportToJsonFile();
                                read();
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

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public void exportToXmlFile() {

        final String xmlFile = "userdata";
        String userName = "username";
        String password = "password";
        System.out.println("EXTERNAL STORAGE is " + isExternalStorageWritable());
        //verifyStoragePermissions(activity);

        if (Build.VERSION.SDK_INT >= 23) {
            if (mainActivity.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                System.out.println("DOSTALISMY POZWOLENIE!!!!");
            } else {
                System.out.println("NIE DOSTALISMY POZWOLENIA!!!!");
            }

        } else {
            System.out.println("NIE JEST 23!");
        }


        try {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/project");
            myDir.mkdirs();

            String fname = "datatest";
            File file = new File(myDir, fname);
            if(file.exists()){
                file.delete();
            } else {
                file.createNewFile();
            }

//            File sdcard = Environment.getExternalStorageDirectory();
//            File file = new File(sdcard.getAbsolutePath() + File.separator + "Projekt" + File.separator + xmlFile);
//            file.mkdirs();
//            file.createNewFile();
              FileOutputStream fos = new FileOutputStream(file);
//            System.out.println("Poszlo");
//            FileOutputStream fileos = mainActivity.openFileOutput(xmlFile,Context.MODE_PRIVATE);
//
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();

            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8",true);
            xmlSerializer.startTag(null,"userData");

            xmlSerializer.startTag(null,"userName");
            xmlSerializer.text("NAAJAAAK");
            xmlSerializer.endTag(null,"userName");

            xmlSerializer.startTag(null,"password");
            xmlSerializer.text("HASLO");
            xmlSerializer.endTag(null,"password");

            xmlSerializer.endTag(null,"userData");
            xmlSerializer.endDocument();
            xmlSerializer.flush();

            String dataWrite = writer.toString();

            fos.write(dataWrite.getBytes());

            fos.close();
            System.out.println("DONE!");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        } catch (IllegalStateException e){
            e.printStackTrace();
        }

    }

    public void read() {
        try{
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myParser = xmlFactoryObject.newPullParser();

            InputStream is;
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/project");

            String fname = "datatest";
            File file = new File(myDir, fname);

            is = new BufferedInputStream(new FileInputStream(file));

            myParser.setInput(is, null);

            String text = "";
            int event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT)  {

                //String name = myParser.getName();

                switch (event){
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        System.out.println(text);
                        break;

                    case XmlPullParser.END_TAG:
                        break;
                }
                event = myParser.next();
            }


        } catch (Exception e){
            System.out.println("BLAD!");
        }

    }


    public void showAddDialog(){

        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity);
        LinearLayout promptView = (LinearLayout)layoutInflater.inflate(R.layout.add_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mainActivity);

        ScrollView addingScroll = (ScrollView) mainActivity.findViewById(R.id.scrollingAdd);
        mainActivity.getLayoutInflater().inflate(R.layout.add_dialog, addingScroll,true);
        alertDialogBuilder.setView(promptView);

        final Field[] fields = Car.class.getDeclaredFields();

        editTexts.clear();
        //Adding EditTexts to the View
        for (int i = 0; i < fields.length; i++) {
            //editTexts[i] = new EditText(mainActivity);
            if(!fields[i].isSynthetic() && fields[i].getName() != "serialVersionUID") {
                editTexts.add(new EditText(mainActivity));
                editTexts.get(i).setHint(fields[i].getName().toString());
                promptView.addView(editTexts.get(i));
            }
        }

        // Setup a dialog window
        alertDialogBuilder.setCancelable(true)
                .setPositiveButton("Add new element", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });

        // create an alert dialog
        final AlertDialog alert = alertDialogBuilder.create();
        alert.show();
        final ArrayList<String> lista = new ArrayList<String>();
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (checkIfFilled(editTexts)) {

                    //Car car = new Car();
                    for (int i = 0; i < editTexts.size(); i++) {

                        lista.add(editTexts.get(i).getText().toString());
                        //Field field = Car.class.getClass().getDeclaredField(editTexts.get(i).getHint().toString());
                        //field.set(car,editTexts.get(i).getText());
                    }
                    Car car = new Car(lista.get(0),lista.get(1),Double.parseDouble(lista.get(2)),Integer.parseInt(lista.get(3)));
                    //car.wypisz();
                    table.addNewElement(car);
                    alert.dismiss();
                } else {
                    Toast.makeText(mainActivity, "Fill all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mainActivity, "Adding cancelled.", Toast.LENGTH_SHORT).show();
                alert.dismiss();
            }
        });
    }

    public boolean checkIfFilled(ArrayList<EditText>editTexts){
        for (int i = 0; i < editTexts.size(); i++) {
            EditText editText = editTexts.get(i);
            if (editText.getText().toString().length() <= 0) {
                return false;
            }
        }
        return true;
    }

//    public static void verifyStoragePermissions(Activity activity){
//        //Check if we have write permission
//        int permission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        if(permission != PackageManager.PERMISSION_GRANTED){
//            //We dont have the permission so prompt the user
//            System.out.println("DOSTALISMY POZWOLENIE!!!!");
//            ActivityCompat.requestPermissions(
//                    activity,
//                    PERMISSION_STORAGE,
//                    REQUEST_EXTERNAL_STORAGE
//            );
//        }
//    }





}
