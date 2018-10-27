package com.example.user.mycoffeeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "CoffeeDatabase.db";
    public static final String TABLE_ORDERS = "Orders";
    public static final String COLUMN_NUMBER = "OrderNumber";
    public static final String COLUMN_NAME = "OrderName";
    public static final String COLUMN_PRICE = "OrderPrice";
    public static final String COLUMN_COUNT = "OrderCount";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Orders(OrderNumber INTEGER PRIMARY KEY AUTOINCREMENT,OrderName VARCHAR,OrderPrice VARCHAR,OrderCount VARCHAR);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Orders");
        onCreate(db);
    }

    public boolean insertOrder(String OrderName,String OrderPrice,String OrderCount){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,OrderName);
        values.put(COLUMN_PRICE,OrderPrice);
        values.put(COLUMN_COUNT,OrderCount);
        database.insert(TABLE_ORDERS,null,values);
        return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from Orders", null );
        return cursor;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,TABLE_ORDERS);
        return numRows;
    }
    public void deleteOrder(int OrderNumber){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_ORDERS,"OrderNumber = ?",new String[]{Integer.toString(OrderNumber)});
        //database.execSQL("DELETE FROM "+TABLE_ORDERS);
    }
}