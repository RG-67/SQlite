package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

//  Creating DBHandler (Database_Handler) class and extend SQLiteOpenHelper class for database creation.
public class DBHandler extends SQLiteOpenHelper {

    //  Creating constant variable for "Database_Name", "Database_Version", "Table_Name", "User_ID", "User_Name", "User_Number" and "User_Email".
    private static final String DB_NAME = "TestingDatabase";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "DataTable";
    private static final String USER_ID = "ID";
    private static final String USER_NAME = "NAME";
    private static final String USER_NUMBER = "CONTACT_NUMBER";
    private static final String USER_EMAIL = "EMAIL_ID";

    //  Creating constructor for DBHandler.
    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override

    //  Creating and execute table through onCreate and SQLiteDatabase.
    public void onCreate(SQLiteDatabase db) {

        //  Writing syntax for creating table through pass the key name of "Table_Name", "User_ID", "User_Name", "User_Number" and "User_Email".
        String query = "CREATE TABLE " + TABLE_NAME + "("
                        + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + USER_NAME + " TEXT, "
                        + USER_NUMBER + " INTEGER, "
                        + USER_EMAIL + " TEXT)";

        //  Calling "db" object of SQLiteDatabase and execute the above query.
        db.execSQL(query);
    }

    //  Creating "addData" method and add new data in SQLiteDatabase.
    public void addData(String personName, String personNumber, String personEmail) {

        //  Creating writable database for creating table.
        SQLiteDatabase db = this.getWritableDatabase();

        //  Creating object of ContentValues.
        ContentValues values = new ContentValues();

        //  Passing all values through it's key name and String name.
        values.put(USER_NAME, personName);
        values.put(USER_NUMBER, personNumber);
        values.put(USER_EMAIL, personEmail);

        //  Inserting all values in the table.
        db.insert(TABLE_NAME, null, values);

        //  After complete the inserting process we have to close database.
        db.close();
    }

    //  Creating "readData" method for reading or viewing data from database.
    public ArrayList<DataModal> readData() {

        //  Creating readable database for reading data.
        SQLiteDatabase db = this.getReadableDatabase();

        //  Creating object of "Cursor" and run the query for reading all data from database.
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        //  Creating new arraylist.
        ArrayList<DataModal> dataModalArrayList = new ArrayList<>();

        // If the cursor in the first position then the loop will execute and run "do-while" loop.
        if (cursor.moveToFirst()) {
            do {

                //  Adding or getting the data from the "cursor" to the arraylist.
                dataModalArrayList.add(new DataModal(cursor.getString(0),
                                        cursor.getString(1),
                                        cursor.getString(2),
                                        cursor.getString(3)));
            }

            //  The loop will end if the "cursor" moving next and can't get any data.
            while (cursor.moveToNext());
        }

        //  After getting all data to the arraylist by the help of the "cursor" we have to close "cursor".
        cursor.close();

        //  After closing the "cursor" we return the values to the arraylist of "dataModel".
        return dataModalArrayList;

    }

    //  Creating method (updateData) for updating or editing data from the database.
    public void updateData(String personId, String personName, String personNumber, String personEmail) {

        //  Creating writable database for editing data.
        SQLiteDatabase db = this.getWritableDatabase();

        //  Creating object of "ContentValues".
        ContentValues values = new ContentValues();

        //  Passing all values through it's key name and String name.
        values.put(USER_ID, personId);
        values.put(USER_NAME, personName);
        values.put(USER_NUMBER, personNumber);
        values.put(USER_EMAIL, personEmail);

        //  By getting the id as a key from the user, using "where" clause and get the argument of id (personId) through String we can update data by using update method.
        db.update(TABLE_NAME, values, "id = ?", new String[]{personId});

        //  After completing the update process we have close database.
        db.close();
    }

    //  Creating method "deleteData" for deleting data of the database by getting id (personId) as a key.
    public void deleteData(String personId) {

        //  Creating writable database for deleting data.
        SQLiteDatabase db = this.getWritableDatabase();

        //  By getting id as a key, using "where" clause and getting the argument id (personId) through String we can delete database by using delete method.
        db.delete(TABLE_NAME, "id = ?", new String[]{personId});

        //  After complete the deleting process we have to close database.
        db.close();
    }

    @Override

    //  Creating a method "onUpgrade" for upgrading a table if the table will exists.
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //  Executing the query if we creating a table which will exists then the below query will drop or delete the table and create a new one.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        //  After executing the above query the "onCreate" method will execute for creating a new table.
        onCreate(db);
    }
}
