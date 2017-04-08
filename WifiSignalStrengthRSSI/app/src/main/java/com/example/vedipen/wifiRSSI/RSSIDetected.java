package com.example.vedipen.wifiRSSI;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Vector;

public class RSSIDetected extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.vedipen.wifiRSSI.R.layout.activity_rssidetected);
        SQLiteDatabase sqlDB=openOrCreateDatabase("db123#4",MODE_PRIVATE,null);
        Vector RSSIDetails=new Vector();
        Cursor getRssis=sqlDB.rawQuery("SELECT * FROM RSSIs ORDER BY EventNo",null);
        int a;
        String b=null;
        getRssis.moveToFirst();
        if(getRssis!=null)
        {
            do {
                a=getRssis.getInt(1);
                b=getRssis.getString(2);
                String n=a+"dbMs@"+b;
                RSSIDetails.add(n);
            }
            while (getRssis.moveToNext());
        }
        final String x[]=new String[RSSIDetails.size()];
        RSSIDetails.toArray(x);
        ListView listView = (ListView) findViewById(com.example.vedipen.wifiRSSI.R.id.RSSIs);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,RSSIDetails);
        listView.setAdapter(arrayAdapter);
    }
}
