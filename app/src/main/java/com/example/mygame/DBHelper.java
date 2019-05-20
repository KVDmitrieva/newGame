package com.example.mygame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.mygame.DrawThread.dungeon;
import static com.example.mygame.DrawThread.enemies;
import static com.example.mygame.StatClass.score;

public class DBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "statisticDB";
    public static final String TABLE_STATISTIC = "statistic";

    public static final String KEY_ID = "_id";
    public static final String KEY_SCORE = "score";
    public static final String KEY_LEVEL = "level";
    public static final String KEY_ENEMIES = "enemy";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_SCORE = 1;
    private static final int NUM_COLUMN_LEVEL = 2;
    private static final int NUM_COLUMN_ENEMIES= 3;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
               String query = "CREATE TABLE " + TABLE_STATISTIC + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_SCORE + " INT, " +
                KEY_LEVEL + " INT,"+
                KEY_ENEMIES+" INT);";
        db.execSQL(query);
          }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_STATISTIC);
        onCreate(db);
    }

    public ArrayList<String> selectAll(SQLiteDatabase db) {
        Cursor mCursor = db.query(TABLE_STATISTIC, null, null, null, null, null, "score DESC");

        ArrayList<String> arr = new ArrayList<>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                int finalScore= mCursor.getInt(NUM_COLUMN_SCORE);
                int level = mCursor.getInt(NUM_COLUMN_LEVEL);
                int enemy=mCursor.getInt(NUM_COLUMN_ENEMIES);
               String totalScore = "Score "+finalScore+"   Level "+level+"   Enemies "+enemy;
                arr.add(totalScore);
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    void pushData(SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_SCORE, score);
        contentValues.put(DBHelper.KEY_LEVEL, dungeon);
        contentValues.put(DBHelper.KEY_ENEMIES, enemies);

        db.insert(DBHelper.TABLE_STATISTIC, null, contentValues);
    }
}