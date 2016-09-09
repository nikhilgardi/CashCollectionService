package com.example.nikhilg.cashcollectionservice;

/**
 * Created by nikhilg on 9/9/2016.
 */
import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

class LoginDataBaseAdapter {

    static final String DATABASE_NAME = "CashCollection.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    public static final String RETAILER_TABLE   = "RetailerInformation";

    public static final String SNO_ID                = "_id";
    public static final String RETAILER_ID       = "RETAILER_ID";
    public static final String Name                  = "Name";
    public static final String Latitude               = "Latitude";
    public static final String Longitude                = "Longitude";
    public static final String RetailerPic                = "RetailerPic";

    static final String DATABASE_CREATE = "create table " + RETAILER_TABLE +
            " (" +SNO_ID + " integer primary key autoincrement, " +
            RETAILER_ID + " integer , "+
            Name + " text not null, "+
            Latitude + " text not null, "+
            Longitude + " text not null"+ ");";



    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(int retailer_id,String name,String latitude,String longitude)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("RETAILER_ID",retailer_id);
        newValues.put("Name", name);
        newValues.put("Latitude",latitude);
        newValues.put("Longitude",longitude);
        db.insert("RetailerInformation", null, newValues);
    }
    public String getData(String mpin)
    {

        Cursor cursor=db.query("RetailerInformation",null," PIN=?",new String[]{mpin},null,null,null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PIN"));
        cursor.close();
        return password;
    }

    public  ArrayList<String> getInformation() {

        Cursor cursor= db.rawQuery("SELECT * FROM "+RETAILER_TABLE,null);
        String sName = null,sLatitude=null,sLongitude=null;
        String sInfo[];
        ArrayList<String> ar = new ArrayList<String>();


        if(cursor.moveToNext()){
            sName = cursor.getString(cursor.getColumnIndex("Name"));
            sLatitude = cursor.getString(cursor.getColumnIndex("Latitude"));
            sLongitude = cursor.getString(cursor.getColumnIndex("Longitude"));
            ar.add(sName);
            ar.add(sLatitude);
            ar.add(sLongitude);
        }
        cursor.close();
        return  ar;

    }


}