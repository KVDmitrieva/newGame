package com.example.mygame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Statistic extends AppCompatActivity {

   DBHelper dbHelper;
    SQLiteDatabase database;
    //Cursor cursor;



    @SuppressLint("ResourceType")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            getSupportActionBar().hide();
            setContentView(R.layout.statistic);

            StatClass stat = new StatClass(this);
            int width = stat.width;
            int height = stat.height;
            dbHelper = new DBHelper(this);
            database = dbHelper.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList = dbHelper.selectAll(database);
        database.close();

        ArrayAdapter<String> scorehAdapter =
                new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, arrayList);
        ListView lv = (ListView) findViewById(R.id.List);
        lv.setAdapter(scorehAdapter);

        Button back = findViewById(R.id.back);

            LinearLayout linearLayout = findViewById(R.id.linear);
            RelativeLayout.LayoutParams b1, b2;
            if(stat.height<stat.width){
                b1 = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 3*height/4);
                b1.topMargin = 0;
                b1.leftMargin = 0;
            }
            else {
                b1 = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, height/2);
                b1.topMargin = 0;
                b1.leftMargin = 0;
            }
            b2 = new RelativeLayout.LayoutParams(back.getWidth(), back.getHeight());
            b2.leftMargin = width/2 - back.getWidth()/2;

            back.setOnClickListener(new View.OnClickListener(){
                @Override
                @RequiresApi(api = Build.VERSION_CODES.M)
                public void onClick(View v){
                    Intent c = new Intent(Statistic.this, MainActivity.class);
                    startActivity(c);
                }
            });



        }
    }
